package com.wenhao.zookeeperdemo.lock.lock.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.wenhao.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class ZookeeperTemplateImplLockDemo extends ZookeeperAbstractTemplateLockDemo {

    @Override
    protected boolean tryLock() {
        try {
            if (!zkClient.exists(lockPath)) {
                log.info("创建节点");
                zkClient.create(lockPath, lockPath, CreateMode.EPHEMERAL);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);
        if (zkClient.exists(lockPath)) {
            if (countDownLatch == null) {
                countDownLatch = new CountDownLatch(1);
            }
            try {
                countDownLatch.await();
            } catch (Exception e) {

            }
        }
        zkClient.unsubscribeDataChanges(lockPath, iZkDataListener);
    }
}
