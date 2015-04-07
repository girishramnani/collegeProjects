from functools import reduce
import itertools
from googleTranslate.Translator import Translator
import re

__author__ = 'Girish'

class Subtitle_translator(Translator):

    def __init__(self,to,file):
        Translator.__init__(self,to)


    def set_progress_bar(self,progress_bar):
        self.progress_bar = progress_bar

    def translate_file(self,from_,to):
        with open(to,'w') as out:
            with open(from_) as file:
                self.li= [ list(g) for f,g in itertools.groupby(file,key=lambda x:bool(x.strip())) if f]
            for block in self.li:

                line,time ,*content = block
                out.writelines([line,time])
                content =reduce(lambda x,y:" ".list(x.strip(),y.strip()),content,"")
                data = Translator.translate(content)
                out.write(content)



