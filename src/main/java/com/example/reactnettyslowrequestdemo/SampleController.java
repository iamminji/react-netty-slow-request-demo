package com.example.reactnettyslowrequestdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {

    private final SampleRequestClient sampleRequestClient;

    public SampleController(SampleRequestClient sampleRequestClient) {
        this.sampleRequestClient = sampleRequestClient;
    }

    @GetMapping(value = "/test")
    public Mono<String> test() {
        System.out.println("web client 요청");
        long startTime = System.nanoTime();
        Mono<String> result = sampleRequestClient.test();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration / 1000);
        return result;
    }

    @GetMapping(value = "/test2")
    public Mono<String> test2() {
        System.out.println("http client 요청");
        long startTime = System.nanoTime();
        Mono<String> result = sampleRequestClient.test2();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration / 1000);
        return result;
    }


}
