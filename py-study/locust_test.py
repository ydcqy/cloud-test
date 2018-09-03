from locust import HttpLocust, TaskSet


def index(l):
    l.client.get("")


class UserBehavior(TaskSet):
    def on_start(self):
        index(self)


class Test(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
