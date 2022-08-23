package com.example.reactnettyslowrequestdemo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SampleRequestClient {

    private final WebClient webClient;

    public SampleRequestClient() {
        this.webClient = WebClient.builder()
                .build();
    }

    public Mono<String> test() {
        return this.webClient.get().uri("https://daum.net")
                .retrieve()
                .bodyToMono(String.class);
    }
}
