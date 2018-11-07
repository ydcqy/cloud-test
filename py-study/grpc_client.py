import logging
import time
from concurrent.futures.thread import ThreadPoolExecutor

import grpc

from grpctest import HelloService_pb2_grpc, HelloService_pb2

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)s %(thread)d --- [%(threadName)s] %(module)s.%(funcName)s : %(message)s'
                    )


def conn():
    channel = grpc.insecure_channel('127.0.0.1:1111')

    stub = HelloService_pb2_grpc.HelloServiceStub(channel)

    response = stub.sayHi1(HelloService_pb2.Params(username="abc", age=123))

    print(response)
    time.sleep(1000)


if __name__ == '__main__':
    # loop = asyncio.get_event_loop()
    # tasks = [asyncio.ensure_future(newInstance(i)) for i in range(10)]
    # loop.run_until_complete(asyncio.wait(tasks))
    concurrency = 1000
    executor = ThreadPoolExecutor(concurrency)
    for i in range(concurrency):
        # t = threading.Thread(target=newInstance, args=(i,))
        # t.start()3
        executor.submit(conn)
