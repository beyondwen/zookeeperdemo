package com.wenhao.zookeeperdemo.countdownlatch;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.wenhao.zkclient.IZkDataListener;
import org.wenhao.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

import static com.wenhao.zookeeperdemo.countdownlatch.PlayTest.*;

@Slf4j
public class PlayService {


    public static CountDownLatch countDownLatch = new CountDownLatch(1);


    public boolean start() {
        if (zkClient == null) {
            zkClient = new ZkClient(PlayTest.host, timeout);
        }
        try {
            boolean exists = zkClient.exists(PlayTest.lockPath);
            if (!exists) {
                zkClient.create(PlayTest.lockPath, PlayTest.lockPath, CreateMode.EPHEMERAL);
                log.info("玩家" + Thread.currentThread().getName() + "开始玩耍");
                Thread.sleep(1000);
                return true;
            }else {
                IZkDataListener iZkDataListener = new IZkDataListener() {
                    @Override
                    public void handleDataChange(String s, Object o) throws Exception {

                    }

                    @Override
                    public void handleDataDeleted(String s) throws Exception {
                        log.info("玩家" + Thread.currentThread().getName() + "结束玩耍");
                        countDownLatch.countDown();
                        countDownLatch = new CountDownLatch(1);
                    }
                };
                if (zkClient == null) {
                    zkClient = new ZkClient(PlayTest.host, timeout);
                }
                zkClient.subscribeDataChanges(lockPath, iZkDataListener);
                try {
                    log.info("休息一下");
                    countDownLatch.await();
                } catch (Exception e) {
                    log.info("出现异常");
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            zkClient.close();
        }

    }

    public void rest() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                log.info("玩家" + Thread.currentThread().getName() + "结束玩耍");
                countDownLatch.countDown();
                countDownLatch = new CountDownLatch(1);
            }
        };
        if (zkClient == null) {
            zkClient = new ZkClient(PlayTest.host, timeout);
        }
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);
        try {
            log.info("休息一下");
            countDownLatch.await();
        } catch (Exception e) {
            log.info("出现异常");
        }

    }
}
