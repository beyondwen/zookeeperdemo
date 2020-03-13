package com.wenhao.zookeeperdemo.distributedlock.lock.impl;

import com.wenhao.zookeeperdemo.distributedlock.lock.Lock;
import lombok.extern.slf4j.Slf4j;
import org.wenhao.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

@Slf4j
public abstract class ZookeeperAbstractTemplateLock implements Lock {
    //zk 服务端ip
    public static final String host = "11.0.0.110";
    //超时时间
    public static final int timeout = 500000;
    //线程控制
    protected CountDownLatch countDownLatch = null;
    //锁名称
    protected String lockPath = "/lockPath";
    //zk客户端
    protected ZkClient zkClient = new ZkClient(host, timeout);
    //定义抽象获取锁 子类实现
    protected abstract boolean tryLock();
    //定义抽象方法等待锁 子类实现
    protected abstract void waitLock();

    /**
     * 获取锁
     */
    @Override
    public void lock() {
        if (tryLock()) {
            log.info(Thread.currentThread().getName() + "获取锁成功");
        } else {
            waitLock();
            lock();
        }
    }

    @Override
    public void unlock() {
        if (zkClient != null) {
            log.info(Thread.currentThread().getName() + "释放锁成功");
            try {
                zkClient.close();
            } catch (Exception e) {

            }

        }
    }
}
