from functools import reduce
import itertools
from tkinter import messagebox
from googleTranslate.Translator import Translator
import re

__author__ = 'Girish'

class Subtitle_translator(Translator):

    def __init__(self,to):
        Translator.__init__(self,to)

    def set_progress_bar(self,progress_bar):
        self.progress_bar = progress_bar

    def translate_file(self,from_,to):
        try:
            with open(to,'w') as out:
                with open(from_) as file:
                    self.li= [ list(g) for f,g in itertools.groupby(file,key=lambda x:bool(x.strip())) if f]
                for block in self.li:

                    line,time ,*content = block
                    out.writelines([line,time])
                    t2 =""
                    for line in content:
                        t2+=line.strip()+" "

                    t2=self.translate(t2)
                    t2+="\r\n\n"
                    out.writelines(t2)
        except KeyboardInterrupt:
            out.flush()

        # self.progress_bar.destroy()
        messagebox.showinfo("File Done","The file has been stored at "+to)




