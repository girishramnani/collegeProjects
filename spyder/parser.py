__author__ = 'Girish'

import html.parser as parser
import requests

class myparser(parser.HTMLParser):
    def handle_starttag(self, tag, attrs):
        if tag =='a':
            for x in attrs:
                print(x)




x = myparser()
x.feed(requests.get("https://www.google.co.in").content)
