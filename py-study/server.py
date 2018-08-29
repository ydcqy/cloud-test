# -*- coding: utf-8 -*-
import socket
from time import sleep

sk = socket.socket()
sk.bind(("0.0.0.0",1111))
sk.listen(1)
print("启动")
sleep(100000)
