package com.example.demo.xdsDecoder;

import com.example.demo.State;
import com.example.demo.XdsClient;
import com.example.demo.XdsTypeUrl;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import io.envoyproxy.envoy.config.cluster.v3.Cluster;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;
import io.grpc.ManagedChannelBuilder;

import java.util.*;

public class CdsDecoder implements XdsDecoder {
    @Override
    public String getTypeUrl() {
        return XdsTypeUrl.CDS.getTypeUrl();
    }

    @Override
    public Map<String, Set<String>> decodeDiscoveryResponse (DiscoveryResponse response) {
        Map<String, Set<String>> map = new HashMap<>();
        List<Any> resourcesList = response.getResourcesList();
        if (getTypeUrl().equals(response.getTypeUrl())) {
            for (Any resource : resourcesList) {
                Cluster cluster = unpackClusterConfiguration(resource);

                // 변환에 실패한 경우 무시합니다.
                if (cluster != null) {
//                    System.out.println("cluster = " + cluster);
//                    System.out.println("cluster = " + cluster.getAllFields());
                    System.out.println("cluster = " + cluster.getName());

                    // 이런 내용처럼 EDS의 내용을 처리해주는 내용이 필요하나, sendDiscoveryRequest 에 set 을 넣어야 하는데 ...
                    // 아직 그런 부분이 없어서 처리해줘야함.
                    if(cluster.getName().equals("echo")) {
                        XdsClient xdsClient = new XdsClient(ManagedChannelBuilder.forAddress("localhost", 9002) // 실제 서버 주소와 포트
                                .usePlaintext()
                                .build());
                        Set<String> stringSet = new HashSet<>();
                        stringSet.add(cluster.getName());
                        State state = xdsClient.sendDiscoveryRequest(XdsTypeUrl.EDS.getTypeUrl(), stringSet);
                    } else {
                        System.out.println("CLUSTER");
                        System.out.println("CLUSTER");
                        System.out.println("CLUSTER");
                        System.out.println("CLUSTER");
                        System.out.println("CLUSTER");
                        System.out.println("CLUSTER");
                        // 다른 cluster 정보를 받아오는지 테스트.
                    }
                }
            }
        }
        if (map.isEmpty())
            return new HashMap<String, Set<String>>();
        else
            return map;
    }

    private Cluster unpackClusterConfiguration(Any any) {
        try {
            return any.unpack(Cluster.class);
        } catch (InvalidProtocolBufferException e) {
            System.out.println("Error occur when decode xDS response." + e);// log
            return null;
        }
    }
}
