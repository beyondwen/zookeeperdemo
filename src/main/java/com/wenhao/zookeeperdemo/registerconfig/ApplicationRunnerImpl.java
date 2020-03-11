package com.wenhao.zookeeperdemo.registerconfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }

    public void start() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.47.128", 100000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info(watchedEvent.getState().name());
            }
        });
        String path = "/mykt";
        Stat exists = zooKeeper.exists(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info(watchedEvent.getState().name());
            }
        });
        if (exists == null) {
            zooKeeper.create(path, "mykt".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        String data = "http://127.0.0.1:" + serverPort;
        zooKeeper.create(path + "/" + serverPort, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
}
