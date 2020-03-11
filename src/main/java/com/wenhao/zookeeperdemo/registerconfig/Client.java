package com.wenhao.zookeeperdemo.registerconfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Client {

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.47.128", 500000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                Event.KeeperState state = watchedEvent.getState();
                if (state == Event.KeeperState.SyncConnected) {
                    log.info("zk连接成功");
                    COUNT_DOWN_LATCH.countDown();
                }
            }
        });
        COUNT_DOWN_LATCH.await();
        String path = "/mykt";
        List<String> children = zooKeeper.getChildren(path, null, new Stat());
        for (String child : children) {
            String pathChildren = path + "/" + child;
            byte[] data = zooKeeper.getData(pathChildren, null, new Stat());
            log.info("服务接口地址:" + new String(data));
        }
    }
}
