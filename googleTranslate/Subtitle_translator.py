from googleTranslate.Translator import Translator

__author__ = 'Girish'

class Subtitle_translator(Translator):
    def __init__(self,to,file):
        Translator.__init__(self,to)

