import gzip
import http.client
import json
import urllib.parse

from ydysdk.exceptions import YdyException
from ydysdk.request import Request


class YdyClient:

    def __init__(self, url, token):
        self.url = url
        self.token = token

    def send(self, request):
        if not isinstance(request, Request):
            raise YdyException("请求类错误")

        url_str = self.url + "?" + "toekn=" + self.token
        if "GET" == str(request.method).upper():
            url_str += "&json=" + urllib.parse.quote(request.get_params(), encoding="utf-8")
            pass
        headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"
            , "Content-Type": "application/json"
            , "Accept-Encoding": "gzip, deflate"
            , "Cache-Control": "no-cache"
            , 'Connection': 'keep-alive'
        }
        urlsplit = urllib.parse.urlsplit(url_str)
        hostport = urllib.parse.splitport(urlsplit.netloc)
        queryStr = urlsplit.path + '?' + urlsplit.query
        host = hostport[0]
        port = hostport[1]
        conn = http.client.HTTPConnection(host, port, 30)
        if "GET" == str(request.method).upper():
            conn.request("GET", queryStr, headers=headers)
        elif "POST" == str(request.method).upper():
            conn.request("POST", queryStr, body=request.get_params(),
                         headers=headers)
        resp = conn.getresponse()
        data = resp.read()
        if "gzip" == str(resp.headers['content-encoding']).lower():
            data = gzip.decompress(data)
        return json.loads(data)
