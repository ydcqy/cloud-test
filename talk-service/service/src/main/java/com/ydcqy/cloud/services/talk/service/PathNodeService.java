package com.ydcqy.cloud.services.talk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaoyu
 */
public class PathNodeService {
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
                    collection.insertOne(document);

                    parentId = document.getObjectId("_id").toString();
                } else {
                    if (d.getBoolean("leaf")) {
                        if (i == nodes.length - 1) {
                            throw new RuntimeException("添加失败，节点已经存在");
                        }
                        throw new RuntimeException("路径中存在叶子节点");
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
                collection.insertOne(document);
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
                Object key = entry.getKey();
                Object value = entry.getValue();

                JSONObject obj = new JSONObject();
                obj.put("name", key);
                obj.put("content", null);
                obj.put("parent", parentId);
                obj.put("leaf", false);
                if (value instanceof JSON) {
                    Document document = Document.parse(obj.toString());
                    collection.insertOne(document);
                    handleJsonToTreeNode((JSON) value, document.getObjectId("_id").toString());
                } else {
                    obj.put("content", value);
                    obj.put("leaf", true);
                    Document document = Document.parse(obj.toString());
                    collection.insertOne(document);
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
                    collection.insertOne(document);
                    handleJsonToTreeNode((JSON) objects[i], document.getObjectId("_id").toString());
                } else {
                    obj.put("content", objects[i]);
                    obj.put("leaf", true);
                    Document document = Document.parse(obj.toString());
                    collection.insertOne(document);
                }
            }
        }
    }

    private boolean isJson(String text) {
        try {
            JSON.parse(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 递归查询指定路径下的所有子节点内容
     *
     * @param path 路径
     * @return
     */
    public String findContentByPath(String path, boolean isAllLevels) {
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
        Object jsonObject = findTreeNodes(c, isAllLevels);
        return JSON.toJSONString(jsonObject);
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

    }

    private Document findPathNode(String name, String parent) {
        if (parent == null) {
            return collection.find(BsonDocument.parse("{'name':'" + name + "','parent':" + null + "}")).first();
        }
        return collection.find(BsonDocument.parse("{'name':'" + name + "','parent':'" + parent + "'}")).first();
    }

    private Object findTreeNodes(Document document, boolean isAllLevels) {
        if (document == null) {
            return null;
        }

        if (!document.getBoolean("leaf")) {
            FindIterable<Document> documents = collection
                    .find(BsonDocument.parse("{'parent':'" + document.getObjectId("_id") + "'}"));
            JSONObject rs = new JSONObject();
            for (Document d : documents) {
                if (isAllLevels) {
                    Object obj = findTreeNodes(d, isAllLevels);
                    rs.put(d.getString("name"), obj);
                } else {
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
            collection.insertOne(Document.parse(jsonObject.toString()));
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
                collection.insertOne(document);
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
            collection.insertOne(document);

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
                    collection.insertOne(tmp);
                    handleJsonToTreeNode((JSON) objects[i], tmp.getObjectId("_id").toString());
                } else {
                    JSONObject obj = new JSONObject();
                    obj.put("name", String.valueOf(i));
                    obj.put("content", objects[i]);
                    obj.put("parent", document.getObjectId("_id").toString());
                    obj.put("leaf", true);
                    Document tmp = Document.parse(obj.toString());
                    collection.insertOne(tmp);
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
    public Set<String> findParentNode(String path) {
        Set<String> rs = new HashSet<>();
        String[] split = path.split("/");
        String[] nodes = Arrays.copyOfRange(split, 1, split.length);
        rs.addAll(Arrays.asList(nodes));
        return rs;
    }


}
