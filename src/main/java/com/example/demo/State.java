package com.example.demo;

import io.envoyproxy.envoy.service.discovery.v3.DiscoveryRequest;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;
import io.grpc.stub.StreamObserver;


public class State {
    private StreamObserver<DiscoveryRequest> streamObserverRequest;
    private StreamObserver<DiscoveryResponse> streamObserverResponse;
    private DiscoveryRequest request;
    private String typeUrl;

    public State () {
    }

    public State(String typeUrl, StreamObserver<DiscoveryRequest> streamObserverRequest,StreamObserver<DiscoveryResponse> streamObserverResponse, DiscoveryRequest request) {
        this.typeUrl = typeUrl;
        this.streamObserverRequest = streamObserverRequest;
        this.streamObserverResponse = streamObserverResponse;
        this.request = request;
    }

    public DiscoveryRequest getRequest() {
        return request;
    }

    public StreamObserver<DiscoveryRequest> getStreamObserverRequest() {
        return streamObserverRequest;
    }

    public StreamObserver<DiscoveryResponse> getStreamObserverResponse() {
        return streamObserverResponse;
    }

    public String getTypeUrl() {
        return typeUrl;
    }
}
