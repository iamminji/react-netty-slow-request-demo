package com.example.reactnettyslowrequestdemo.example;

import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

public class TCPClientExample {

    public static void main(String[] args) {
        Connection connection =
                TcpClient.create()
                        .host("localhost.com")
                        .port(8000)
                        .connectNow();

        connection.onDispose()
                .block();
    }
}
