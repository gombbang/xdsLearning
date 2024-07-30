package com.example.demo;
import com.google.protobuf.Any;
import io.envoyproxy.envoy.config.core.v3.Node;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.envoyproxy.envoy.service.discovery.v3.AggregatedDiscoveryServiceGrpc;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryRequest;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;
import io.grpc.stub.StreamObserver;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class XdsClient {
    private final ManagedChannel channel;
    private final AggregatedDiscoveryServiceGrpc.AggregatedDiscoveryServiceStub stub;


    protected final static AtomicLong requestId = new AtomicLong(0);
    /**
     * Store Request Parameter ( resourceNames )
     * K - requestId, V - resourceNames
     */
    protected final Map<Long, Set<String>> requestParam = new ConcurrentHashMap<>();

    /**
     * Store ADS Request Observer ( StreamObserver in Streaming Request )
     * K - requestId, V - StreamObserver
     */
    private final Map<Long, StreamObserver<DiscoveryRequest>> requestObserverMap = new ConcurrentHashMap<>();

    /**
     * Store Delta-ADS Request Observer ( StreamObserver in Streaming Request )
     * K - requestId, V - StreamObserver
     */
    private final Map<Long, ScheduledFuture<?>> observeScheduledMap = new ConcurrentHashMap<>();

    /**
     * Store CompletableFuture for Request ( used to fetch async result in ResponseObserver )
     * K - requestId, V - CompletableFuture
     */
    private final Map<Long, CompletableFuture<?>> streamResult = new ConcurrentHashMap<>();


    public XdsClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = AggregatedDiscoveryServiceGrpc.newStub(channel);
    }

    // 추가된 생성자: ManagedChannel 주입
    public XdsClient(ManagedChannel channel) {
        this.channel = channel;
        this.stub = AggregatedDiscoveryServiceGrpc.newStub(channel);
    }

    public AggregatedDiscoveryServiceGrpc.AggregatedDiscoveryServiceStub getStub() {
        return stub;
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public DiscoveryRequest buildDiscoveryRequest(String typeUrl, Set<String> resourceNames) {
        return DiscoveryRequest.newBuilder()
                .setTypeUrl(typeUrl)
                .addAllResourceNames(resourceNames)
//                .setNode()        // node 선정을 못한다, 규봉님에게 물어볼 것.
                .build();
    }

    // 요청
    // getResource, resourceNames를 받아서 Set으로 정리.
    // resourceNames로 buildDiscoveryRequest를 요청 -> 그 내용을 onNext에 넣는다.
    // 내 sendDiscoveryRequest와는 조금 다르다.

    public void sendDiscoveryRequest(String typeUrl) {
        DiscoveryRequest request = buildDiscoveryRequest(typeUrl, Collections.emptySet());
        System.out.println("typeUrl = " + typeUrl);
        stub.streamAggregatedResources(new StreamObserver<DiscoveryResponse>() {
            @Override
            public void onNext(DiscoveryResponse response) {
                System.out.println("Received response: " + response); // 응답을 수신했을 때 출력
                handleDiscoveryResponse(response); // 응답 처리
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error receiving response: " + t.getMessage());
                t.printStackTrace(); // 스택 트레이스를 출력하여 상세한 오류를 확인
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream completed");
            }


        }).onNext(request);
        // 매번 인자에 무명객체를 넣어야하는가?
        // 그게 오히려 메모리상으로는 이득? 새로 만드는 오버헤드는?
        // 그냥 남기는게 낫지 않을까?
    }

    // 응답
    private void handleDiscoveryResponse(DiscoveryResponse response) {
        processResponseData(response);
        sendAck(response);
    }
    private void processResponseData(DiscoveryResponse response) {
        for (Any resource : response.getResourcesList()) {
            // Process each resource (e.g., parse and store in cache)
            System.out.println("resource.getTypeUrl() = " + resource.getTypeUrl());
            System.out.println("response = " + resource.toString());
            // test
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void sendAck(DiscoveryResponse response) {
        DiscoveryRequest ack = DiscoveryRequest.newBuilder()
                .setVersionInfo(response.getVersionInfo())
                .setResponseNonce(response.getNonce())
                .setTypeUrl(response.getTypeUrl())
                .build();
        stub.streamAggregatedResources(new StreamObserver<DiscoveryResponse>() {
            @Override
            public void onNext(DiscoveryResponse response) {
                handleDiscoveryResponse(response);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error sending ACK: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("ACK stream completed");
            }
        }).onNext(ack);
    }

}