package com.wenhao.zookeeperdemo.lock.lock;

public interface LockDemo {

    /**
     * 获取锁
     */
    void lock();

    /**
     * 释放锁
     */
    void unlock();

}
