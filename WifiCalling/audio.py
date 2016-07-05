from pyaudio import PyAudio,paFloat32
from const import *
from utils import find_input_device
try:
    from Queue import Queue
except:
    from queue import Queue


class AudioCodec(object):

    def __init__(self,read_callback,write_callback):
        self.audio = pyaudio.PyAudio()
        self.input_stream = self.audio.open(format=FORMAT, channels=CHANNELS,
                                            rate=RATE, input=True,
                                            frames_per_buffer=CHUNK,
                                            input_device_index=find_input_device(self.audio),
                                            stream_callback=read_callback)

        self.output_stream = self.audio.open(format = FORMAT, channels = CHANNELS,
                                             rate=RATE,output=True,
                                             frames_per_buffer=CHUNK,
                                             stream_callback=write_callback)

    def start(self):
        self.input_stream.start_stream()
        self.output_stream.start_stream()
