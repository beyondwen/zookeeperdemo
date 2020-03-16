package com.wenhao.zookeeperdemo.lock.service;

import com.wenhao.zookeeperdemo.lock.OrderNumGeneratorDemo;
import com.wenhao.zookeeperdemo.lock.lock.LockDemo;
import com.wenhao.zookeeperdemo.lock.lock.impl.ZookeeperTemplateImplLockDemo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceDemo {

    LockDemo lock = new ZookeeperTemplateImplLockDemo();
    OrderNumGeneratorDemo orderNumGenerator = new OrderNumGeneratorDemo();

    public void getNumber() {
        try {
            lock.lock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + "number:" + number);
        } catch (Exception e) {
            log.info("获取锁失败");
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                log.info("释放锁失败");
            }

        }
    }
}
