from thrift.protocol import TJSONProtocol
from thrift.transport import TSocket, TTransport

from HelloService import HelloService

transport = TTransport.TFramedTransport(TSocket.TSocket('127.0.0.1', 1111))
protocol = TJSONProtocol.TJSONProtocolFactory().getProtocol(transport)
transport.open()

client = HelloService.Client(protocol)

print(client.sayHi("张三11", 123))
