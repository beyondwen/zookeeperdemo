package com.wenhao.zookeeperdemo.distributedlock.lock;

public interface Lock {

    /**
     * 获取锁
     */
    void lock();

    /**
     * 释放锁
     */
    void unlock();
}
