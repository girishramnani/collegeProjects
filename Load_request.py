import socket
from threading import Thread

__author__ = 'Girish'


class girish(Thread):
    def __int__(self, name):
        super().__init__()
        self.name = name

    def run(self):
        sock = socket.socket()
        addr = ("www.google.com",443)
        sock.connect(addr)
        print("connected")

while True:
    s = girish()
    s.start()
    
x = girish()
x.start()
w = girish()
w.start()