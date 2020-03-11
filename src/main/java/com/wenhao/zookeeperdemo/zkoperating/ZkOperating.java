package com.wenhao.zookeeperdemo.zkoperating;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;

@Slf4j
public class ZkOperating {

    public static void main(String[] args) throws Exception {
        /*ZooKeeper zooKeeper = new ZooKeeper("11.0.0.110:2182", 20000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info(watchedEvent.getState().name());
            }
        });
        String s1 = zooKeeper.create("/mayikt", "mayikt".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        log.info(s1);*/
        //zooKeeper.close();
        BaseZookeeper baseZookeeper = new BaseZookeeper();
        baseZookeeper.connectZookeeper("11.0.0.110");
        //baseZookeeper.createEphemeralNode("/wenhao","12121");
        /*baseZookeeper.createEphemeralSequentialNode("/wenhao","12121");
        baseZookeeper.createEphemeralSequentialNode("/wenhao","12121");
        baseZookeeper.createEphemeralSequentialNode("/wenhao","12121");*/
        //Thread.sleep(10000);
        String test001 = baseZookeeper.createPersistentNode("/wenhao1", "test001");
        String test002 = baseZookeeper.createPersistentSequentialNode("/wenhao1", "test001");
        String test003 = baseZookeeper.createPersistentSequentialNode("/wenhao1", "test001");
        log.info(test001);
    }
}
