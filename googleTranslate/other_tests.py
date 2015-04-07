from functools import reduce
from googleTranslate import Translator

__author__ = 'Girish'

import unittest
import re
import itertools
class MyTestCase(unittest.TestCase):
    def test_something(self):
        self.assertEqual(True, False)

    def test_re(self):
        self.li =[]
        translator = Translator.Translator("fr")
        re1 = re.compile(r' .... (?P<text>.*?)(\n\n|$)')
        file2=open("test2.srt","w")
        with open("test.srt") as file:
            self.li= [ list(g) for f,g in itertools.groupby(file,key=lambda x:bool(x.strip())) if f]
        for block in self.li:

            line,time ,*content = block
            file2.writelines([line,time])
            content =reduce(lambda x,y:x.strip()+" "+y.strip(),content,"")
            data = translator.translate(content)
            print(data)

            






if __name__ == '__main__':
    unittest.main()
