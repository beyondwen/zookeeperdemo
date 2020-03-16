package com.wenhao.zookeeperdemo.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumGeneratorDemo {

    private static int count = 0;

    public String getNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        try {
            Thread.sleep(30);
        } catch (Exception e) {

        }
        return sdf.format(new Date()) + "-" + ++count;
    }
}
