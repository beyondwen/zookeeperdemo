package com.wenhao.zookeeperdemo.distributedlock.lock.impl;


import org.apache.zookeeper.CreateMode;
import org.wenhao.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperTemplateImplLock extends ZookeeperAbstractTemplateLock {
    /**
     * 创建zk锁
     *
     * @return boolean
     */
    @Override
    protected boolean tryLock() {
        try {
            if (zkClient != null) {
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
            public void handleDataChange(String dataPath, Object data) {

            }

            @Override
            public void handleDataDeleted(String dataPath) {
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
