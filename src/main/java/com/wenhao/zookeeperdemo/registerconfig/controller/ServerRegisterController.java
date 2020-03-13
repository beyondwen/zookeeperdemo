package com.wenhao.zookeeperdemo.registerconfig.controller;

import com.wenhao.zookeeperdemo.registerconfig.MyZkSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhao.zkclient.ZkClient;

import java.io.IOException;

@RestController
@Slf4j
public class ServerRegisterController {

    @GetMapping("/serverRegister")
    public void serverRegister(String host, String port) throws IOException, KeeperException, InterruptedException {
        /*ZooKeeper zooKeeper = new ZooKeeper("192.168.47.128", 1000000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info(watchedEvent.getState().name());
            }
        });*/
        ZkClient zkClient = new ZkClient("192.168.47.128", 500000);
        zkClient.setZkSerializer(new MyZkSerializer());
        String path = "/mykt";
        boolean exists = zkClient.exists(path);
        if (!exists) {
            zkClient.create(path,"", ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        boolean childExists = zkClient.exists(path + "/" + port);
        if (!childExists) {
            String server = "http://" + host + ":" + port;
            zkClient.create(path + "/" + port, server, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }
}
