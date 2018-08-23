import json

import websocket


def on_message(ws, message):
    msg = json.loads(message)
    data_ = msg["data"]
    print(msg)
    if msg["command"] == 6:
        user_ = data_['user']
        user_['groups'] = []
        user_['groups'].append({"group_id": "yuanda/node/a"});
        msg = {"cmd": 7, "user": user_, "path": "yuanda/node/a"};
        ws.send(json.dumps(msg));


def on_error(ws, error):
    print(error)


def on_close(ws):
    print("### closed ###")


def newInstance():
    # websocket.enableTrace(True)
    ws = websocket.WebSocketApp("ws://localhost:8888?username=abc&password=123")

    ws.on_message = on_message
    ws.on_close = on_close
    ws.on_error = on_error

    ws.run_forever(ping_interval=60, ping_timeout=5)
