package com.wenhao.zookeeperdemo.lock.lock.impl;

import com.wenhao.zookeeperdemo.lock.lock.LockDemo;
import lombok.extern.slf4j.Slf4j;
import org.wenhao.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

@Slf4j
public abstract class ZookeeperAbstractTemplateLockDemo implements LockDemo {

    private static final String host = "11.0.0.110";

    private static final int timeOut = 500000;

    protected abstract boolean tryLock();

    protected abstract void waitLock();

    //线程控制
    protected CountDownLatch countDownLatch = null;

    protected final ZkClient zkClient = new ZkClient(host, timeOut);

    protected final static String lockPath = "/lockPath";


    @Override
    public void lock() {
        if (tryLock()) {
            log.info("获取锁成功");
        } else {
            waitLock();
            lock();
        }
    }

    @Override
    public void unlock() {
        if (zkClient != null) {
            zkClient.close();
            log.info("释放锁成功");
        }
    }
}
