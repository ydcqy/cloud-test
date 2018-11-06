package com.ydcqy.cloud.services.talk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.apache.commons.lang.StringUtils;
import org.bson.BsonDocument;
import org.bson.Document;

import javax.naming.SizeLimitExceededException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
public class PathNodeService {
    private static Cache<String, String> guavaCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.SECONDS)
            .expireAfterWrite(60, TimeUnit.SECONDS).maximumSize(10000).build();
    private static final int NODE_CONTENT_LIMIT_SIZE = 100 * 1024;
    private static final int QUERY_LIMIT_COUNT = 1000;
    private MongoCollection<Document> collection;

    public PathNodeService(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void setCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void updateContentByPath(String path, String json) {
        String[] split = path.split("/");
        String[] nodes = Arrays.copyOfRange(split, 1, split.length);
        Document c = null;
        for (int i = 0; i < nodes.length; i++) {
            if (i == 0) {
                c = findPathNode(nodes[i], null);
                continue;
            }
            if (c != null) {
                c = findPathNode(nodes[i], c.getObjectId("_id").toString());
            }
        }
        if (c == null) {
            throw new RuntimeException("修改失败，节点不存在");
        }
        updateTreeNodes(c, json);
        delCache(path);
    }

    /**
     * 新增
     *
     * @param path 路径
     * @param json 内容
     */
    public void insertContentToPath(String path, String json) {
        String[] split = path.split("/");
        String[] nodes = Arrays.copyOfRange(split, 1, split.length);
        String parentId = null;
        for (int i = 0; i < nodes.length; i++) {
            if (i == 0) {
                Document d = findPathNode(nodes[i], null);
                if (null == d) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", nodes[i]);
                    jsonObject.put("parent", null);
                    if (isJson(json) || nodes.length > 1) {
                        jsonObject.put("content", null);
                        jsonObject.put("leaf", false);
                    } else {
                        jsonObject.put("content", json);
                        jsonObject.put("leaf", true);
                    }
                    Document document = Document.parse(jsonObject.toString());
                    // collection.insertOne(document);
                    insertOneWithLock(document);

                    parentId = document.getObjectId("_id").toString();
                } else {
                    if (d.getBoolean("leaf")) {
                        if (i == nodes.length - 1) {
                            throw new RuntimeException("添加失败，节点已经存在");
                        }
                        throw new RuntimeException("路径中存在叶子节点");
                    } else if (i == nodes.length - 1) {
                        throw new RuntimeException("添加失败，节点已经存在");
                    }
                    parentId = d.getObjectId("_id").toString();
                }
                continue;
            }
            Document d = findPathNode(nodes[i], parentId);
            if (null == d) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", nodes[i]);
                jsonObject.put("content", null);
                jsonObject.put("parent", parentId);
                jsonObject.put("leaf", false);
                if (i == nodes.length - 1 && !isJson(json)) {
                    jsonObject.put("content", json);
                    jsonObject.put("leaf", true);
                }
                Document document = Document.parse(jsonObject.toString());
                // collection.insertOne(document);
                insertOneWithLock(document);
                parentId = document.getObjectId("_id").toString();
            } else if (i == nodes.length - 1) {
                throw new RuntimeException("添加失败，节点已经存在");
            } else {
                if (d.getBoolean("leaf")) {
                    throw new RuntimeException("路径中存在叶子节点");
                }
                parentId = d.getObjectId("_id").toString();
            }
        }
        if (isJson(json)) {
            handleJsonToTreeNode((JSON) JSON.parse(json), parentId);
        }
        delCache(path);
    }

    /**
     * 所传节点内容是json对象时，递归处理节点
     *
     * @param json
     * @param parentId
     */
    private void handleJsonToTreeNode(JSON json, String parentId) {
        if (json instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) json;
            Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object value = entry.getValue();

                JSONObject obj = new JSONObject();
                obj.put("name", key);
                obj.put("content", null);
                obj.put("parent", parentId);
                obj.put("leaf", false);
                if (value instanceof JSON) {
                    Document document = Document.parse(obj.toString());
                    // collection.insertOne(document);
                    insertOneWithLock(document);
                    handleJsonToTreeNode((JSON) value, document.getObjectId("_id").toString());
                } else {
                    obj.put("content", value);
                    obj.put("leaf", true);
                    Document document = Document.parse(obj.toString());
                    // collection.insertOne(document);
                    insertOneWithLock(document);
                }
            }
        } else if (json instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) json;
            Object[] objects = jsonArray.toArray();
            for (int i = 0; i < objects.length; i++) {
                JSONObject obj = new JSONObject();
                obj.put("name", String.valueOf(i));
                obj.put("content", null);
                obj.put("parent", parentId);
                obj.put("leaf", false);
                if (objects[i] instanceof JSON) {
                    Document document = Document.parse(obj.toString());
                    // collection.insertOne(document);
                    insertOneWithLock(document);
                    handleJsonToTreeNode((JSON) objects[i], document.getObjectId("_id").toString());
                } else {
                    obj.put("content", objects[i]);
                    obj.put("leaf", true);
                    Document document = Document.parse(obj.toString());
                    // collection.insertOne(document);
                    insertOneWithLock(document);
                }
            }
        }
    }

    private static final ConcurrentMap<String, Object> LOCK_MAP = new ConcurrentHashMap();

    private void insertOneWithLock(Document document) {
        String name = document.getString("name");
        String parent = document.getString("parent");
        String lockKey = name + "#" + parent;
        LOCK_MAP.putIfAbsent(lockKey, new Object());
        synchronized (LOCK_MAP.get(lockKey)) {
            try {
                Document d = findPathNode(name, parent);
                if (d == null) {
                    collection.insertOne(document);
                } else {
                    document.put("_id", d.getObjectId("_id"));
                }
            } finally {
                LOCK_MAP.remove(lockKey);
            }
        }
    }

    private boolean isJson(String text) {
        try {
            JSON json = (JSON) JSON.parse(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 本地缓存处理
     *
     * @param nodePath
     * @param levelNum
     * @return
     */
    private String findContentByPathCacheProcess(String nodePath, int levelNum, Map<String, Object> params) {
        try {
            String key = nodePath + "#" + levelNum + JSON.toJSONString(params);
            String rs = guavaCache.get(key, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("===查询MongoDB===，缓存key：" + key);
                    return findContentByPath2(nodePath, levelNum, params);
                }
            });
            if (rs.getBytes().length > NODE_CONTENT_LIMIT_SIZE) {
                delCache(nodePath);
                throw new SizeLimitExceededException("内容大小超出限制");
            }
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(substringAfterLast(e.getMessage(), "Exception: "), e);
        }
    }

    private static String substringAfterLast(String str, String separator) {
        if (StringUtils.isEmpty(str)) {
            return str;
        } else if (StringUtils.isEmpty(separator)) {
            return "";
        } else {
            int pos = str.lastIndexOf(separator);
            if (pos == -1) {
                return str;
            }
            return pos != -1 && pos != str.length() - separator.length() ? str.substring(pos + separator.length()) : "";
        }
    }

    /**
     * 递归查询指定路径下的所有子节点内容
     *
     * @param path 路径
     * @return
     */
    public String findContentByPath(String path, boolean isAllLevels, Map<String, Object> queryFilterParams) {
        return findContentByPath(path, isAllLevels ? -1 : 1, queryFilterParams);
    }

    /**
     * 根据层级数查询节点
     *
     * @param path
     * @param levelNum
     * @return
     */
    private String findContentByPath(String path, int levelNum, Map<String, Object> params) {
        return findContentByPathCacheProcess(path, levelNum, params);
    }

    private static final ThreadLocal<Object> countThreadLoacl = new ThreadLocal<>();

    private String findContentByPath2(String path, int levelNum, Map<String, Object> params) {

        String[] split = path.split("/");
        String[] nodes = Arrays.copyOfRange(split, 1, split.length);
        Document c = null;
        for (int i = 0; i < nodes.length; i++) {
            if (i == 0) {
                c = findPathNode(nodes[i], null);
                continue;
            }
            if (c != null) {
                c = findPathNode(nodes[i], c.getObjectId("_id").toString());
            }
        }
        if (c == null) {
            throw new RuntimeException("查询失败，节点不存在");
        }
        Object jsonObject = null;

        checkQueryParams(params);

        AtomicInteger queryCount = new AtomicInteger(0);

        try {
            if (params != null && params.get("count") != null) {
                countThreadLoacl.set(params.get("count"));
            }

            if (params != null && "$key".equals(params.get("orderBy"))) {
                jsonObject = findTreeNodes(c, levelNum, true, params, queryCount);
            } else if (params != null && params.get("orderBy") != null
                    && String.valueOf(params.get("orderBy")).contains("$child-")) {
                jsonObject = findTreeNodes(c, levelNum, false, params, queryCount);
            } else {
                jsonObject = findTreeNodes(c, levelNum, false, null, queryCount);
            }
        } finally {
            countThreadLoacl.remove();
        }
        return JSON.toJSONString(jsonObject);
    }

    /**
     * 检查查询过滤条件参数
     *
     * @param params
     */
    private void checkQueryParams(Map<String, Object> params) {
        if (params != null && "$key".equals(params.get("orderBy"))
                || params != null && String.valueOf(params.get("orderBy")).contains("$child-")) {
            if (params.get("limitToFirst") != null && params.get("limitToLast") != null) {
                throw new RuntimeException("limitToFirst 和 limitToLast 不能同时作为参数");
            }
        }
    }

    /**
     * 删除指定路径节点（包括该节点和其下所有子节点）
     *
     * @param path
     * @return
     */
    public void deleteContentByPath(String path) {
        String[] split = path.split("/");
        String[] nodes = Arrays.copyOfRange(split, 1, split.length);
        Document c = null;
        for (int i = 0; i < nodes.length; i++) {
            if (i == 0) {
                c = findPathNode(nodes[i], null);
                continue;
            }
            if (c != null) {
                c = findPathNode(nodes[i], c.getObjectId("_id").toString());
            }
        }
        if (c == null) {
            throw new RuntimeException("删除失败，节点不存在");
        }
        deleteTreeNodes(c);
        delCache(path);
    }

    /**
     * 清除缓存
     *
     * @param path
     */
    public void delCache(String path) {
        Set<String> rmPaths = findParentNode(path);
        Set<String> strings = guavaCache.asMap().keySet();
        for (String key : strings) {
            String rmPath = key.split("#")[0];
            if (rmPaths.contains(rmPath)) {
                guavaCache.asMap().remove(key);
                System.out.println("清除缓存，path：" + path + "，key：" + key);
            } else if (rmPath.startsWith(path)) {
                guavaCache.asMap().remove(key);
                System.out.println("清除缓存，path：" + path + "，key：" + key);
            }
        }
    }

    private Document findPathNode(String name, String parent) {
        if (parent == null) {
            return collection.find(BsonDocument.parse("{'name':'" + name + "','parent':" + null + "}")).first();
        }
        return collection.find(BsonDocument.parse("{'name':'" + name + "','parent':'" + parent + "'}")).first();
    }

    private Object findTreeNodes(Document document, int levelNum, boolean isParams, Map<String, Object> params,
                                 AtomicInteger queryCount) {

        if (document == null) {
            return null;
        }

        if (!document.getBoolean("leaf")) {
            long currCount = 0;
            FindIterable<Document> documents = null;

            boolean isQueryAgain = false;

            if (null != params && isParams) {
                String filter = "{'parent':'" + document.getObjectId("_id") + "'";
                if ("$key".equals(params.get("orderBy"))) {
                    if (params.get("equalTo") != null) {
                        Object equalTo = params.get("equalTo");
                        if (equalTo instanceof String) {
                            filter += ",'name':'" + equalTo + "'";
                        } else {
                            filter += ",'name':" + equalTo;
                        }
                    } else if (params.get("startAt") != null && params.get("endAt") != null) {
                        Object startAt = params.get("startAt");
                        Object endAt = params.get("endAt");
                        if (startAt instanceof String || endAt instanceof String) {
                            filter += ",'name':{$gte:'" + startAt + "',$lte:'" + endAt + "'}";
                        } else {
                            filter += ",'name':{$gte:" + startAt + ",$lte:" + endAt + "}";
                        }
                    } else if (params.get("startAt") != null) {
                        Object startAt = params.get("startAt");
                        if (startAt instanceof String) {
                            filter += ",'name':{$gte:'" + startAt + "'}";
                        } else {
                            filter += ",'name':{$gte:" + startAt + "}";
                        }
                    } else if (params.get("endAt") != null) {
                        Object endAt = params.get("endAt");
                        if (endAt instanceof String) {
                            filter += ",'name':{$lte:'" + endAt + "'}";
                        } else {
                            filter += ",'name':{$lte:" + endAt + "}";
                        }
                    }
                    filter += "}";
                    documents = collection.find(BsonDocument.parse(filter));

                    if (params.get("limitToFirst") != null) {
                        documents.limit((Integer) params.get("limitToFirst"));
                        documents.sort(BsonDocument.parse("{'name':1}"));

                    } else if (params.get("limitToLast") != null) {
                        documents.limit((Integer) params.get("limitToLast"));
                        documents.sort(BsonDocument.parse("{'name':-1}"));
                    }
                } else {
                    String orderBy = String.valueOf(params.get("orderBy"));
                    String childKey = orderBy.split("\\$child-")[1];
                    filter += ",'name':'" + childKey + "'";
                    if (params.get("equalTo") != null) {
                        Object equalTo = params.get("equalTo");
                        if (equalTo instanceof String) {
                            filter += ",'content':'" + equalTo + "'";
                        } else {
                            filter += ",'content':" + equalTo;
                        }
                    } else if (params.get("startAt") != null && params.get("endAt") != null) {
                        Object startAt = params.get("startAt");
                        Object endAt = params.get("endAt");
                        if (startAt instanceof String || endAt instanceof String) {
                            filter += ",'content':{$gte:'" + startAt + "',$lte:'" + endAt + "'}";
                        } else {
                            filter += ",'content':{$gte:" + startAt + ",$lte:" + endAt + "}";
                        }
                    } else if (params.get("startAt") != null) {
                        Object startAt = params.get("startAt");
                        if (startAt instanceof String) {
                            filter += ",'content':{$gte:'" + startAt + "'}";
                        } else {
                            filter += ",'content':{$gte:" + startAt + "}";
                        }
                    } else if (params.get("endAt") != null) {
                        Object endAt = params.get("endAt");
                        if (endAt instanceof String) {
                            filter += ",'content':{$lte:'" + endAt + "'}";
                        } else {
                            filter += ",'content':{$lte:" + endAt + "}";
                        }
                    }
                    filter += "}";
                    documents = collection.find(BsonDocument.parse(filter));
                    if (params.get("limitToFirst") != null) {
                        documents.limit((Integer) params.get("limitToFirst"));
                        documents.sort(BsonDocument.parse("{'content':1}"));

                    } else if (params.get("limitToLast") != null) {
                        documents.limit((Integer) params.get("limitToLast"));
                        documents.sort(BsonDocument.parse("{'content':-1}"));
                    }
                    isQueryAgain = true;
                }
                currCount = collection.count(BsonDocument.parse(filter));
                isParams = false;
                params = null;
            } else if (null != params && !isParams) {
                BsonDocument bd = BsonDocument.parse("{'parent':'" + document.getObjectId("_id") + "'}");
                documents = collection.find(bd);
                currCount = collection.count(bd);
                isParams = true;
            } else {
                BsonDocument bd = BsonDocument.parse("{'parent':'" + document.getObjectId("_id") + "'}");
                documents = collection.find(bd);
                currCount = collection.count(bd);
            }

            if (isQueryAgain && documents.iterator().hasNext()) {
                documents = collection.find(BsonDocument.parse("{'parent':'" + document.getObjectId("_id") + "'}"));
            }

            if (countThreadLoacl.get() != null && (Boolean) countThreadLoacl.get() == true) {
                return currCount;
            }

            if (queryCount.addAndGet((int) currCount) > QUERY_LIMIT_COUNT) {
                throw new RuntimeException("数据量过大，请优化查询条件或JSON数据结构");
            }

            JSONObject rs = new JSONObject(new TreeMap<>());
            for (Document d : documents) {

                if (levelNum == -1) {
                    Object obj = findTreeNodes(d, levelNum, isParams, params, queryCount);
                    if (obj instanceof JSONObject) {
                        if (((JSONObject) obj).size() == 0) {
                            continue;
                        }
                    }
                    rs.put(d.getString("name"), obj);
                } else if (levelNum > 1) {
                    Object obj = findTreeNodes(d, levelNum - 1, isParams, params, queryCount);
                    if (obj instanceof JSONObject) {
                        if (((JSONObject) obj).size() == 0) {
                            continue;
                        }
                    }
                    rs.put(d.getString("name"), obj);
                } else if (levelNum == 1) {
                    rs.put(d.getString("name"), "");
                }
            }
            return rs;
        }
        return document.get("content");
    }

    private void deleteTreeNodes(Document document) {
        if (document == null) {
            return;
        }
        collection.deleteOne(document);
        if (!document.getBoolean("leaf")) {
            FindIterable<Document> documents = collection
                    .find(BsonDocument.parse("{'parent':'" + document.getObjectId("_id") + "'}"));
            for (Document d : documents) {
                deleteTreeNodes(d);
            }
        }
    }

    private void updateTreeNodes(Document document, String jsonStr) {
        if (!isJson(jsonStr)) {
            deleteTreeNodes(document);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", document.getString("name"));
            jsonObject.put("parent", document.getString("parent"));
            jsonObject.put("content", jsonStr);
            jsonObject.put("leaf", true);
            // collection.insertOne(Document.parse(jsonObject.toString()));
            insertOneWithLock(Document.parse(jsonObject.toString()));
            return;
        }
        Object json = JSON.parse(jsonStr);
        if (json instanceof JSONObject) {
            if (document.getBoolean("leaf")) {
                deleteTreeNodes(document);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", document.getString("name"));
                jsonObject.put("parent", document.getString("parent"));
                jsonObject.put("content", null);
                jsonObject.put("leaf", false);
                document = Document.parse(jsonObject.toString());
                // collection.insertOne(document);
                insertOneWithLock(document);
            }
            JSONObject jsonObject = (JSONObject) json;
            Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Document d = findPathNode(key, document.getObjectId("_id").toString());
                if (d == null) {
                    JSONObject obj = new JSONObject();
                    obj.put(key, value);
                    handleJsonToTreeNode(obj, document.getObjectId("_id").toString());
                    continue;
                }
                updateTreeNodes(d, value.toString());
            }
        } else if (json instanceof JSONArray) {
            deleteTreeNodes(document);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", document.getString("name"));
            jsonObject.put("parent", document.getString("parent"));
            jsonObject.put("content", null);
            jsonObject.put("leaf", false);
            document = Document.parse(jsonObject.toString());
            // collection.insertOne(document);
            insertOneWithLock(document);

            JSONArray jsonArray = (JSONArray) json;
            Object[] objects = jsonArray.toArray();
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] instanceof JSON) {
                    JSONObject obj = new JSONObject();
                    obj.put("name", String.valueOf(i));
                    obj.put("content", null);
                    obj.put("parent", document.getObjectId("_id").toString());
                    obj.put("leaf", false);
                    Document tmp = Document.parse(obj.toString());
                    // collection.insertOne(tmp);
                    insertOneWithLock(tmp);
                    handleJsonToTreeNode((JSON) objects[i], tmp.getObjectId("_id").toString());
                } else {
                    JSONObject obj = new JSONObject();
                    obj.put("name", String.valueOf(i));
                    obj.put("content", objects[i]);
                    obj.put("parent", document.getObjectId("_id").toString());
                    obj.put("leaf", true);
                    Document tmp = Document.parse(obj.toString());
                    // collection.insertOne(tmp);
                    insertOneWithLock(tmp);
                }
            }
        }
    }

    /**
     * 向mongodb注册用户信息
     *
     * @param username 用户名
     * @param password 密码
     */
    public void registerUser(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        Document document = Document.parse(jsonObject.toString());
        collection.insertOne(document);

    }

    /**
     * 查询需要去通知的所有节点
     *
     * @param path
     * @return
     */
    private Set<String> findParentNode(String path) {
        Set<String> rs = new HashSet<>();
        String[] split = path.split("/");
        String[] nodes = Arrays.copyOfRange(split, 1, split.length);

        for (int i = 0; i < nodes.length; i++) {
            rs.add("/" + StringUtils.join(Arrays.copyOfRange(nodes, 0, i + 1), "/"));
        }
        return rs;
    }

//    /**
//     * 推送节点内容到节点订阅者
//     */
//    public void pushToNodeSubscriber(String nodePath, String nodeContent, ImStatus NodePathStatus) {
//        JSONObject result = new JSONObject();
//        result.put("success", true);
//        result.put("code", "0");
//        result.put("msg", "操作成功");
//        result.put("nodePath", nodePath);
//
//        result.put("nodeContent", nodeContent);
//
//        RespBody respBody = new RespBody(Command.COMMAND_CHAT_REQ, NodePathStatus).setData(result);
//        ImPacket chatPacket = new ImPacket(Command.COMMAND_CHAT_REQ, respBody.toByte());
//
//        Set<String> nodePaths = findParentNode(nodePath);
//        for (String path : nodePaths) {
//            ImAio.sendToGroup("yuanda/node" + path, chatPacket);
//        }
//    }
}
