import logging
from time import sleep

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


def get():
    request = NodeGetRequest()
    request.set_node_path("/a")
    print(client.send(request))


def add():
    request = NodeAddRequest()
    request.set_node_path("/a")
    request.set_node_content("aabc")
    print(client.send(request))


def update():
    request = NodeUpdateRequest()
    request.set_node_path("/a")
    request.set_node_content("哈哈哈哈")
    print(client.send(request))


def delete():
    request = NodeDeleteRequest()
    request.set_node_path("/a")
    print(client.send(request))


if __name__ == '__main__':
    # add()
    update()
    # delete()
    # get()
