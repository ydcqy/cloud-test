namespace c_glib com.ydcqy.ynet.rpc.thrift
namespace java com.ydcqy.ynet.rpc.thrift
namespace cpp com.ydcqy.ynet.rpc.thrift
namespace js ThriftTest
namespace py ThriftTest
namespace go thrifttest
namespace php ThriftTest
//namespace lua ThriftTest

struct YrpcRequest {
   1: string request_id,
   2: string group,
   3: string version,
   4: string interface_name,
   5: string method_name,
   6: list<binary> params,
}

struct YrpcResponse {
    1: string request_id,
    2: binary result,
    3: string err_msg,
}