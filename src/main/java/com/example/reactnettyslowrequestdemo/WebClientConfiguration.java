package com.example.reactnettyslowrequestdemo;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import javax.net.ssl.SSLException;
import java.time.Duration;

@Configuration
public class WebClientConfiguration {

    @Bean
    public HttpClient httpClient() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create(
                        ConnectionProvider.builder("req")
                                .maxIdleTime(Duration.ofSeconds(5L))
                                .evictInBackground(Duration.ofSeconds(5L))
                                .build()
                )
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext))
                .wiretap(true)
                .keepAlive(true)
                .responseTimeout(Duration.ofSeconds(10L));

        // warmu
        // https://projectreactor.io/docs/netty/release/reference/index.html#_eager_initialization_4
        System.out.println("warm-up http-client");
        httpClient.warmup().block();
        return httpClient;
    }

    @Bean
    public WebClient webClient(HttpClient httpClient) {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
