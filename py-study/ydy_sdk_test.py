import json
import logging

from ydysdk.client import YdyClient
from ydysdk.request import NodeGetRequest, NodeAddRequest, NodeUpdateRequest, NodeDeleteRequest

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)s %(thread)d --- [%(threadName)s] %(module)s.%(funcName)s : %(message)s'
                    )

# client = http.client.HTTPConnection("192.168.11.104", 8888, 30)
# headers = {
#     "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"
#     , "Content-Type": "application/json"
#     , "Accept-Encoding": "gzip, deflate"
# }
# body = {
#     "api": "/get",
#     "nodePath": "/a",
# }
# client.request("POST", "/node/sdkapi?token=dsgdsgdsikhuewikdnjlkgh", body=str(body), headers=headers)
# logging.info(client.getresponse().read().decode("utf-8"))

url = "http://192.168.11.104:8888/node/sdkapi"
client = YdyClient(url, "dsgdsgdsikhuewikdnjlkgh")

nodePath = "/wechat"


def get():
    request = NodeGetRequest()
    request.set_node_path("/CaiYuanGuPiao/szkt")
    print(client.send(request))


def add():
    request = NodeAddRequest()
    request.set_node_path(nodePath)
    request.set_node_content("妖孽，哪里跑！！！")
    print(client.send(request))


def update():
    request = NodeUpdateRequest()
    request.set_node_path(nodePath)
    a = {"content": "在吗？给您发消息您一直也没有回。您现在拿着哪只股票呢？我可以通过我们的蓝粉特色指标帮您看一下您手里的股票是最佳的买点还是最佳的卖点？[疑问]",
         "createTime": "1536217150000", "dataType": "text", "isSend": "1", "msgId": "3471", "nickName": "李闯",
         "talker": "wxid_kvd8aabqhmuj22", "userName": "wxid_651zse2bix5912"}
    request.set_node_content(json.dumps(a))
    print(client.send(request))


def delete():
    request = NodeDeleteRequest()
    request.set_node_path(nodePath)
    print(client.send(request))


if __name__ == '__main__':
    delete()
    add()
    update()
    get()
