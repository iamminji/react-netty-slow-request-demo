package com.example.reactnettyslowrequestdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class SampleRequestClient {

    private final WebClient webClient;

    public SampleRequestClient(HttpClient httpClient) {
        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public Mono<String> test() {
        return this.webClient.get().uri("http://daum.net")
                .retrieve()
                .bodyToMono(String.class);
    }
}
