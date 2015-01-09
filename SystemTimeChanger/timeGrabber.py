__author__ = 'Girish'

import requests
import bs4
def grab_time_and_date():
    bs = bs4.BeautifulSoup(requests.get("http://www.worldtimeserver.com/current_time_in_IN.aspx").content)
    date =bs.find("span",{'class':'font6'}).text.strip()
    time = bs.find("span",{'class':'font7'}).text.strip()
    return time,date
sdfs
