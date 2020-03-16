package com.wenhao.zookeeperdemo.lock.controller;


import com.wenhao.zookeeperdemo.lock.service.OrderServiceDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDemoController {

    @GetMapping("/testorder")
    public void getOrder() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OrderServiceDemo orderService = new OrderServiceDemo();
                    orderService.getNumber();
                    //System.out.println(orderService+"打印");
                }
            }).start();
        }
    }
}
