package io.grpc.examples.manualflowcontrol;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.65.0)",
    comments = "Source: hello_streaming.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StreamingGreeterGrpc {

  private StreamingGreeterGrpc() {}

  public static final java.lang.String SERVICE_NAME = "manualflowcontrol.StreamingGreeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.manualflowcontrol.HelloRequest,
      io.grpc.examples.manualflowcontrol.HelloReply> getSayHelloStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHelloStreaming",
      requestType = io.grpc.examples.manualflowcontrol.HelloRequest.class,
      responseType = io.grpc.examples.manualflowcontrol.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<io.grpc.examples.manualflowcontrol.HelloRequest,
      io.grpc.examples.manualflowcontrol.HelloReply> getSayHelloStreamingMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.manualflowcontrol.HelloRequest, io.grpc.examples.manualflowcontrol.HelloReply> getSayHelloStreamingMethod;
    if ((getSayHelloStreamingMethod = StreamingGreeterGrpc.getSayHelloStreamingMethod) == null) {
      synchronized (StreamingGreeterGrpc.class) {
        if ((getSayHelloStreamingMethod = StreamingGreeterGrpc.getSayHelloStreamingMethod) == null) {
          StreamingGreeterGrpc.getSayHelloStreamingMethod = getSayHelloStreamingMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.manualflowcontrol.HelloRequest, io.grpc.examples.manualflowcontrol.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHelloStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.manualflowcontrol.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.manualflowcontrol.HelloReply.getDefaultInstance()))
              .setSchemaDescriptor(new StreamingGreeterMethodDescriptorSupplier("SayHelloStreaming"))
              .build();
        }
      }
    }
    return getSayHelloStreamingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StreamingGreeterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingGreeterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingGreeterStub>() {
        @java.lang.Override
        public StreamingGreeterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingGreeterStub(channel, callOptions);
        }
      };
    return StreamingGreeterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StreamingGreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingGreeterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingGreeterBlockingStub>() {
        @java.lang.Override
        public StreamingGreeterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingGreeterBlockingStub(channel, callOptions);
        }
      };
    return StreamingGreeterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StreamingGreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingGreeterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingGreeterFutureStub>() {
        @java.lang.Override
        public StreamingGreeterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingGreeterFutureStub(channel, callOptions);
        }
      };
    return StreamingGreeterFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Streams a many greetings
     * </pre>
     */
    default io.grpc.stub.StreamObserver<io.grpc.examples.manualflowcontrol.HelloRequest> sayHelloStreaming(
        io.grpc.stub.StreamObserver<io.grpc.examples.manualflowcontrol.HelloReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSayHelloStreamingMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StreamingGreeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class StreamingGreeterImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StreamingGreeterGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StreamingGreeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class StreamingGreeterStub
      extends io.grpc.stub.AbstractAsyncStub<StreamingGreeterStub> {
    private StreamingGreeterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingGreeterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingGreeterStub(channel, callOptions);
    }

    /**
     * <pre>
     * Streams a many greetings
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.grpc.examples.manualflowcontrol.HelloRequest> sayHelloStreaming(
        io.grpc.stub.StreamObserver<io.grpc.examples.manualflowcontrol.HelloReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getSayHelloStreamingMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StreamingGreeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class StreamingGreeterBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StreamingGreeterBlockingStub> {
    private StreamingGreeterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingGreeterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingGreeterBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StreamingGreeter.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class StreamingGreeterFutureStub
      extends io.grpc.stub.AbstractFutureStub<StreamingGreeterFutureStub> {
    private StreamingGreeterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingGreeterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingGreeterFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SAY_HELLO_STREAMING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO_STREAMING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHelloStreaming(
              (io.grpc.stub.StreamObserver<io.grpc.examples.manualflowcontrol.HelloReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSayHelloStreamingMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              io.grpc.examples.manualflowcontrol.HelloRequest,
              io.grpc.examples.manualflowcontrol.HelloReply>(
                service, METHODID_SAY_HELLO_STREAMING)))
        .build();
  }

  private static abstract class StreamingGreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StreamingGreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.manualflowcontrol.HelloStreamingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StreamingGreeter");
    }
  }

  private static final class StreamingGreeterFileDescriptorSupplier
      extends StreamingGreeterBaseDescriptorSupplier {
    StreamingGreeterFileDescriptorSupplier() {}
  }

  private static final class StreamingGreeterMethodDescriptorSupplier
      extends StreamingGreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    StreamingGreeterMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StreamingGreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StreamingGreeterFileDescriptorSupplier())
              .addMethod(getSayHelloStreamingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
