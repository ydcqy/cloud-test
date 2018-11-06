const transport = new Thrift.TWebSocketTransport("ws://127.0.0.1:1111");
const protocol = new Thrift.Protocol(transport);
transport.open();