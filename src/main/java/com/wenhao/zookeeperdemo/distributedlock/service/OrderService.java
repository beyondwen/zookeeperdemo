package com.wenhao.zookeeperdemo.distributedlock.service;

import com.wenhao.zookeeperdemo.distributedlock.OrderNumGenerator;
import com.wenhao.zookeeperdemo.distributedlock.lock.Lock;
import com.wenhao.zookeeperdemo.distributedlock.lock.impl.ZookeeperTemplateImplLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderService {

    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
    Lock lock = new ZookeeperTemplateImplLock();

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
