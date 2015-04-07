from googleTranslate.Translator import Translator
import re

__author__ = 'Girish'

class Subtitle_translator(Translator):

    def __init__(self,to,file):
        Translator.__init__(self,to)
        self.re = re.compile(r' .... (?P<text>.*?)(\n\n|$)')




