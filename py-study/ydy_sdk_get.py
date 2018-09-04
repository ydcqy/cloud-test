import logging

from ydysdk.client import YdyClient
from ydysdk.request import NodeGetRequest

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

if __name__ == '__main__':
    client = YdyClient("http://localhost:8888/node/sdkapi", "dsgdsgdsikhuewikdnjlkgh")
    print(client.send(NodeGetRequest()))
