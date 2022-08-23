package com.example.reactnettyslowrequestdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

@Component
@RequiredArgsConstructor
public class SampleRequestClient {

    private final WebClient webClient;
    private final HttpClient httpClient;

    public Mono<String> test() {
        return this.webClient.get().uri("http://localhost:8000/")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> test2() {
        return httpClient.post()
                .uri("https://example.com/")
                .send(ByteBufFlux.fromString(Mono.just("hello")))
                .response()
                .then(Mono.just("success"));
    }
}
