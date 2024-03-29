package com.example.reactnettyslowrequestdemo;

import com.example.reactnettyslowrequestdemo.controller.SampleController;
import com.example.reactnettyslowrequestdemo.service.SampleRequestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = SampleController.class)
@Import({SampleRequestClient.class, WebClientConfiguration.class})
class SampleControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("시간 측정")
    public void test() throws Exception {
        webClient.get().uri("/test")
                .exchange()
                .expectStatus().is2xxSuccessful();

        webClient.get().uri("/test")
                .exchange()
                .expectStatus().is2xxSuccessful();

        webClient.get().uri("/test")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @DisplayName("시간 측정2")
    public void test2() throws Exception {
        webClient.get().uri("/test2")
                .exchange();

        webClient.get().uri("/test2")
                .exchange();

        webClient.get().uri("/test2")
                .exchange();
    }

}