__author__ = 'Girish'

import re
import json
from textwrap import wrap
try:
    import urllib2 as request
    from urllib import quote
except:
    from urllib import request
    from urllib.parse import quote

class Translator:
    def __init__(self, to_lang, from_lang='auto'):
        self.from_lang = from_lang
        self.to_lang = to_lang


    def translate(self, source):
        self.source_list = wrap(source, 1000, replace_whitespace=False)
        return ' '.join(self._get_translation_from_google(s) for s in self.source_list)

    def _get_translation_from_google(self, source):
        json5 = self._get_json5_from_google(source)
        translation = ''
        for sentence in json.loads(json5)['sentences']:
            translation += sentence['trans']
        return translation

    def _get_json5_from_google(self, source):
        escaped_source = quote(source, '')
        headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19'}
        req = request.Request(
             url="http://translate.google.com/translate_a/t?client=p&ie=UTF-8&oe=UTF-8"
                 +"&sl=%s&tl=%s&text=%s" % (self.from_lang, self.to_lang, escaped_source)
                 , headers = headers)
        r = request.urlopen(req)
        return r.read().decode('utf-8')

