package com.wenhao.zookeeperdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ZookeeperdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperdemoApplication.class, args);
    }

}
