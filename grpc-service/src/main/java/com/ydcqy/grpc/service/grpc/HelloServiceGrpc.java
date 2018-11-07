package com.ydcqy.grpc.service.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.12.0)",
    comments = "Source: HelloService.proto")
public final class HelloServiceGrpc {

  private HelloServiceGrpc() {}

  public static final String SERVICE_NAME = "grpctest.HelloService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getSayHi1Method()} instead.
  public static final io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> METHOD_SAY_HI1 = getSayHi1MethodHelper();

  private static volatile io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi1Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi1Method() {
    return getSayHi1MethodHelper();
  }

  private static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi1MethodHelper() {
    io.grpc.MethodDescriptor<HelloServiceProtos.Params, HelloServiceProtos.Result> getSayHi1Method;
    if ((getSayHi1Method = HelloServiceGrpc.getSayHi1Method) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getSayHi1Method = HelloServiceGrpc.getSayHi1Method) == null) {
          HelloServiceGrpc.getSayHi1Method = getSayHi1Method = 
              io.grpc.MethodDescriptor.<HelloServiceProtos.Params, HelloServiceProtos.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpctest.HelloService", "sayHi1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Params.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("sayHi1"))
                  .build();
          }
        }
     }
     return getSayHi1Method;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getSayHi2Method()} instead.
  public static final io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> METHOD_SAY_HI2 = getSayHi2MethodHelper();

  private static volatile io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi2Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi2Method() {
    return getSayHi2MethodHelper();
  }

  private static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi2MethodHelper() {
    io.grpc.MethodDescriptor<HelloServiceProtos.Params, HelloServiceProtos.Result> getSayHi2Method;
    if ((getSayHi2Method = HelloServiceGrpc.getSayHi2Method) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getSayHi2Method = HelloServiceGrpc.getSayHi2Method) == null) {
          HelloServiceGrpc.getSayHi2Method = getSayHi2Method = 
              io.grpc.MethodDescriptor.<HelloServiceProtos.Params, HelloServiceProtos.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpctest.HelloService", "sayHi2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Params.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("sayHi2"))
                  .build();
          }
        }
     }
     return getSayHi2Method;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getSayHi3Method()} instead.
  public static final io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> METHOD_SAY_HI3 = getSayHi3MethodHelper();

  private static volatile io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi3Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi3Method() {
    return getSayHi3MethodHelper();
  }

  private static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi3MethodHelper() {
    io.grpc.MethodDescriptor<HelloServiceProtos.Params, HelloServiceProtos.Result> getSayHi3Method;
    if ((getSayHi3Method = HelloServiceGrpc.getSayHi3Method) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getSayHi3Method = HelloServiceGrpc.getSayHi3Method) == null) {
          HelloServiceGrpc.getSayHi3Method = getSayHi3Method = 
              io.grpc.MethodDescriptor.<HelloServiceProtos.Params, HelloServiceProtos.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpctest.HelloService", "sayHi3"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Params.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("sayHi3"))
                  .build();
          }
        }
     }
     return getSayHi3Method;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @Deprecated // Use {@link #getSayHi4Method()} instead.
  public static final io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> METHOD_SAY_HI4 = getSayHi4MethodHelper();

  private static volatile io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi4Method;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi4Method() {
    return getSayHi4MethodHelper();
  }

  private static io.grpc.MethodDescriptor<HelloServiceProtos.Params,
      HelloServiceProtos.Result> getSayHi4MethodHelper() {
    io.grpc.MethodDescriptor<HelloServiceProtos.Params, HelloServiceProtos.Result> getSayHi4Method;
    if ((getSayHi4Method = HelloServiceGrpc.getSayHi4Method) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getSayHi4Method = HelloServiceGrpc.getSayHi4Method) == null) {
          HelloServiceGrpc.getSayHi4Method = getSayHi4Method = 
              io.grpc.MethodDescriptor.<HelloServiceProtos.Params, HelloServiceProtos.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpctest.HelloService", "sayHi4"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Params.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  HelloServiceProtos.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("sayHi4"))
                  .build();
          }
        }
     }
     return getSayHi4Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloServiceStub newStub(io.grpc.Channel channel) {
    return new HelloServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HelloServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HelloServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class HelloServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sayHi1(HelloServiceProtos.Params request,
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHi1MethodHelper(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<HelloServiceProtos.Params> sayHi2(
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      return asyncUnimplementedStreamingCall(getSayHi2MethodHelper(), responseObserver);
    }

    /**
     */
    public void sayHi3(HelloServiceProtos.Params request,
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHi3MethodHelper(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<HelloServiceProtos.Params> sayHi4(
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      return asyncUnimplementedStreamingCall(getSayHi4MethodHelper(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHi1MethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                HelloServiceProtos.Params,
                HelloServiceProtos.Result>(
                  this, METHODID_SAY_HI1)))
          .addMethod(
            getSayHi2MethodHelper(),
            asyncClientStreamingCall(
              new MethodHandlers<
                HelloServiceProtos.Params,
                HelloServiceProtos.Result>(
                  this, METHODID_SAY_HI2)))
          .addMethod(
            getSayHi3MethodHelper(),
            asyncServerStreamingCall(
              new MethodHandlers<
                HelloServiceProtos.Params,
                HelloServiceProtos.Result>(
                  this, METHODID_SAY_HI3)))
          .addMethod(
            getSayHi4MethodHelper(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                HelloServiceProtos.Params,
                HelloServiceProtos.Result>(
                  this, METHODID_SAY_HI4)))
          .build();
    }
  }

  /**
   */
  public static final class HelloServiceStub extends io.grpc.stub.AbstractStub<HelloServiceStub> {
    private HelloServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloServiceStub(channel, callOptions);
    }

    /**
     */
    public void sayHi1(HelloServiceProtos.Params request,
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHi1MethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<HelloServiceProtos.Params> sayHi2(
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSayHi2MethodHelper(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void sayHi3(HelloServiceProtos.Params request,
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSayHi3MethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<HelloServiceProtos.Params> sayHi4(
        io.grpc.stub.StreamObserver<HelloServiceProtos.Result> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSayHi4MethodHelper(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class HelloServiceBlockingStub extends io.grpc.stub.AbstractStub<HelloServiceBlockingStub> {
    private HelloServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public HelloServiceProtos.Result sayHi1(HelloServiceProtos.Params request) {
      return blockingUnaryCall(
          getChannel(), getSayHi1MethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<HelloServiceProtos.Result> sayHi3(
        HelloServiceProtos.Params request) {
      return blockingServerStreamingCall(
          getChannel(), getSayHi3MethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HelloServiceFutureStub extends io.grpc.stub.AbstractStub<HelloServiceFutureStub> {
    private HelloServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected HelloServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<HelloServiceProtos.Result> sayHi1(
        HelloServiceProtos.Params request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHi1MethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HI1 = 0;
  private static final int METHODID_SAY_HI3 = 1;
  private static final int METHODID_SAY_HI2 = 2;
  private static final int METHODID_SAY_HI4 = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HI1:
          serviceImpl.sayHi1((HelloServiceProtos.Params) request,
              (io.grpc.stub.StreamObserver<HelloServiceProtos.Result>) responseObserver);
          break;
        case METHODID_SAY_HI3:
          serviceImpl.sayHi3((HelloServiceProtos.Params) request,
              (io.grpc.stub.StreamObserver<HelloServiceProtos.Result>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HI2:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHi2(
              (io.grpc.stub.StreamObserver<HelloServiceProtos.Result>) responseObserver);
        case METHODID_SAY_HI4:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHi4(
              (io.grpc.stub.StreamObserver<HelloServiceProtos.Result>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return HelloServiceProtos.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloService");
    }
  }

  private static final class HelloServiceFileDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier {
    HelloServiceFileDescriptorSupplier() {}
  }

  private static final class HelloServiceMethodDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HelloServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloServiceFileDescriptorSupplier())
              .addMethod(getSayHi1MethodHelper())
              .addMethod(getSayHi2MethodHelper())
              .addMethod(getSayHi3MethodHelper())
              .addMethod(getSayHi4MethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
