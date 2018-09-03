import socket

connection = socket.create_connection(address=("192.168.11.104", 8888))
connection.recv(1024)
print("哈哈哈")