package com.example.reactnettyslowrequestdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SampleRequestClient {

    private final WebClient webClient;

    public Mono<String> test() {
        return this.webClient.get().uri("http://daum.net")
                .retrieve()
                .bodyToMono(String.class);
    }
}
