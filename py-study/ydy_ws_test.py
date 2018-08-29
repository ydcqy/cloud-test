#!/usr/local/bin/python3
import json
import logging
import threading
from concurrent.futures.thread import ThreadPoolExecutor

import websocket

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)s %(thread)d --- [%(threadName)s] %(module)s.%(funcName)s : %(message)s'
                    )
threading_local = threading.local()


def on_message(ws, message):
    msg = json.loads(message)
    data_ = msg["data"]
    # if threading_local.i == 500:
    logging.info("接收到消息：" + str(msg)[100:500])
    if msg["command"] == 6:
        user_ = data_['user']
        user_['groups'] = []
        user_['groups'].append({"group_id": "yuanda/node/a"})
        msg = {"cmd": 7, "user": user_, "path": "yuanda/node/a"}
        ws.send(json.dumps(msg))


def on_error(ws, error):
    logging.error(error)


def on_close(ws):
    logging.info("### closed ###")


def newInstance(i):
    # websocket.enableTrace(True)

    threading_local.i = i
    ws = websocket.WebSocketApp("ws://localhost:1111?username=abc&password=123")
    # ws = websocket.WebSocketApp("ws://192.168.1.189:8888?username=abc&password=123")

    ws.on_message = on_message
    ws.on_close = on_close
    ws.on_error = on_error
    ws.run_forever(ping_interval=0, ping_timeout=5)
    # print(i)
    # await time.sleep(1)


if __name__ == '__main__':
    # loop = asyncio.get_event_loop()
    # tasks = [asyncio.ensure_future(newInstance(i)) for i in range(10)]
    # loop.run_until_complete(asyncio.wait(tasks))
    executor = ThreadPoolExecutor(100)
    for i in range(100):
        # t = threading.Thread(target=newInstance, args=(i,))
        # t.start()
        executor.submit(newInstance, i)
