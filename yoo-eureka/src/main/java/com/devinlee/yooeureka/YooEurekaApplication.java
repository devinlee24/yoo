package com.devinlee.yooeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class YooEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YooEurekaApplication.class, args);
    }

}
