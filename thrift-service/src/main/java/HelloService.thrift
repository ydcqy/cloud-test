namespace c_glib com.ydcqy.thrift.service
namespace java com.ydcqy.thrift.service
namespace cpp com.ydcqy.thrift.service
//namespace js thriftService
//namespace py ThriftTest
namespace go thrifttest
namespace php ThriftTest

service HelloService{

    string sayHi(1:string username,2:i32 age),

}