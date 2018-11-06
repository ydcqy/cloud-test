import logging
from concurrent.futures.thread import ThreadPoolExecutor

from thrift.protocol import TJSONProtocol
from thrift.transport import TSocket, TTransport

from HelloService import HelloService

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)s %(thread)d --- [%(threadName)s] %(module)s.%(funcName)s : %(message)s'
                    )


def conn():
    transport = TTransport.TFramedTransport(TSocket.TSocket('127.0.0.1', 1111))

    protocol = TJSONProtocol.TJSONProtocolFactory().getProtocol(transport)
    transport.open()

    client = HelloService.Client(protocol)

    logging.info(client.sayHi("张三", 123))


if __name__ == '__main__':
    # loop = asyncio.get_event_loop()
    # tasks = [asyncio.ensure_future(newInstance(i)) for i in range(10)]
    # loop.run_until_complete(asyncio.wait(tasks))
    concurrency = 10000
    executor = ThreadPoolExecutor(concurrency)
    for i in range(concurrency):
        # t = threading.Thread(target=newInstance, args=(i,))
        # t.start()3

        executor.submit(conn)
