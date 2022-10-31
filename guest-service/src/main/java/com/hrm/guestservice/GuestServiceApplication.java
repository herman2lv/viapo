package com.hrm.guestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GuestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestServiceApplication.class, args);
    }

}
