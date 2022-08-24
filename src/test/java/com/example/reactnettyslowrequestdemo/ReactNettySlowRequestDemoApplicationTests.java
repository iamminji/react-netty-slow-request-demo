package com.example.reactnettyslowrequestdemo;

import com.example.reactnettyslowrequestdemo.controller.SampleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReactNettySlowRequestDemoApplicationTests {

    @Autowired
    private SampleController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}
