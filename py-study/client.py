import socket

connection = socket.create_connection(address=("localhost", 1111))
connection.recv(1024)
