import socket
import os
from _thread import *

ServerSocket = socket.socket()
host = '127.0.0.1'
port = 2004
ThreadCount = 0
try:
    ServerSocket.bind((host, port))
except socket.error as e:
    print(str(e))

print('Waiting for a Connection..')
ServerSocket.listen(5)


def threaded_client(connection,clients):
    connection.send(str.encode('Welcome to the Server\n'))
    while True:
        data = connection.recv(2048)
        reply = '' + data.decode('utf-8')
        if not data:
            break
        

        for c in clients:
            c.sendall(str.encode(reply))
        
    connection.close()

clients = []

while True:
    Client, address = ServerSocket.accept()
    print('Connected to: ' + address[0] + ':' + str(address[1]))
    
    clients.append(Client)
    start_new_thread(threaded_client, (Client, clients ))
    ThreadCount += 1
    print('Thread Number: ' + str(ThreadCount))
ServerSocket.close()