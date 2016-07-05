from __future__ import print_function
import socket
import threading
import socketserver as SocketServer
import audio
import pyaudio
import queue
import time

class SoundHandler(SocketServer.StreamRequestHandler):

    def __init__(self,*args):

        print("Starting the audio channel for ",*args)
        self.lock = threading.Condition()
        self.lock.acquire()
        self.codec = audio.AudioCodec(read_callback=self.from_mic,write_callback=self.write_to_speaker)
        super().__init__(*args)

    def from_mic(self,in_data,*args):
        if self.wfile.closed:
            self.lock.release()
        else :
            self.wfile.write(in_data)
            return (None,pyaudio.paContinue)


    def write_to_speaker(self,*args):
        if self.rfile.closed:
            self.lock.release()
        else:
            return (self.rfile.read(),pyaudio.paContinue)

    def handle(self):
        self.codec.start()
        self.lock.wait()

class ThreadedTCPServer(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    pass


HOST, PORT = "localhost", 0

server = ThreadedTCPServer((HOST, PORT), SoundHandler)
ip, port = server.server_address
server_thread = threading.Thread(target=server.serve_forever)
server_thread.daemon = True
server_thread.start()
print("Server started at ",ip,port)
server_thread.join()
