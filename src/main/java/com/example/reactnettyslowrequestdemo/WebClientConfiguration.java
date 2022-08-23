package com.example.reactnettyslowrequestdemo;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import javax.net.ssl.SSLException;
import java.time.Duration;

@Configuration
public class WebClientConfiguration {

    @Bean
    public HttpClient httpClient() throws SSLException {

        HttpClient httpClient = HttpClient.create()
                // https://github.com/reactor/reactor-netty/issues/1563#issuecomment-805918236
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                // https://projectreactor.io/docs/netty/release/reference/index.html#_host_name_resolution_2
                .resolver(spec -> spec.queryTimeout(Duration.ofMillis(500)))
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
