package com.wenhao.zookeeperdemo.distributedlock.controller;

import com.wenhao.zookeeperdemo.distributedlock.service.OrderService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/order")
    @Async
    public void getOrder() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OrderService orderService = new OrderService();
                    orderService.getNumber();
                    System.out.println(orderService+"打印");
                }
            }).start();
        }
    }
}
