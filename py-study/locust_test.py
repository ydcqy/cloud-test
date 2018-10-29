from locust import HttpLocust, TaskSet


def index(l):
    l.client.get("/")


class UserBehavior(TaskSet):
    tasks = {index: 1}


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 100
    max_wait = 1000

# nohup locust -f locust_test.py  --web-host=0.0.0.0 --port=8089  --host=http://192.168.11.104:8888/node/get?path=/wechat/2018/09/29/源达秦桢齐/137762128 --master > /dev/null 2>&1 &
# nohup locust -f locust_test.py --host=http://192.168.11.104:8888/node/get?path=/wechat/2018/09/29/源达秦桢齐/137762128 --slave --master-host=192.168.11.104 > /dev/null 2>&1 &

# nohup locust -f locust_test.py  --web-host=0.0.0.0 --port=8089  --host=http://192.168.11.104 --master > /dev/null 2>&1 &
# nohup locust -f locust_test.py --host=http://192.168.11.104 --slave --master-host=192.168.11.104 > /dev/null 2>&1 &