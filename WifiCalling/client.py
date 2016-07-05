import pyaudio
from const import *
from utils import find_input_device
from audio import AudioCodec
import socket

class Client(object):

    def __init__(self):
        self.codec = AudioCodec(write_callback=self.write_wrapper, read_callback=self.read_wrapper)

    def connect(self, addr):
        pass

    def read(self, *args):
        pass

    def write(self, *args):
        pass

    def write_wrapper(self, in_data, frame_count, time_info, status):
        return self.write(in_data,frame_count,time_info,status)

    def read_wrapper(self, in_data, frame_count, time_info, status):
        return self.read(in_data, frame_count, time_info, status)


if __name__ =="__main__":
    pass
