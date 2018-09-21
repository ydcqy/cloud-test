from locust import HttpLocust, TaskSet


def index(l):
    l.client.get("/")


class UserBehavior(TaskSet):
    tasks = {index: 1}


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000

# nohup locust -f locust_test.py  --web-host=0.0.0.0 --port=8089  --host=http://192.168.11.104:8888/node/get?path=/TradeStock --master > /dev/null 2>&1 &
# nohup locust -f locust_test.py --host=http://192.168.11.104:8888/node/get?path=/TradeStock --slave --master-host=127.0.0.1 > /dev/null 2>&1 &
