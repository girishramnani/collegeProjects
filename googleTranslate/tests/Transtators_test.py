from googleTranslate.Subtitle_translator import Subtitle_translator

__author__ = 'Girish'

import unittest


class MyTestCase(unittest.TestCase):
    def test_something(self):
        self.assertEqual(True, False)
    def test_translator(self):
        Subtitle = Subtitle_translator("fr")
        Subtitle.translate_file("test.srt","test2.srt")



if __name__ == '__main__':
    unittest.main()
