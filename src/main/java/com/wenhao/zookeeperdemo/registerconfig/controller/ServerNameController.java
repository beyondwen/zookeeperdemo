package com.wenhao.zookeeperdemo.registerconfig.controller;

import com.wenhao.zookeeperdemo.registerconfig.MyZkSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhao.zkclient.ZkClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RestController
@Slf4j
public class ServerNameController {

    private final static CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    @GetMapping("/serverName")
    public List<String> getServerName() throws IOException, KeeperException, InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.47.128", 500000);
        /*zkClient.connect(10000L, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info(watchedEvent.getState().name());
            }
        });*/
        /*ZooKeeper zooKeeper = new ZooKeeper("192.168.47.128", 500000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info(watchedEvent.getState().name());
            }
        });*/
        zkClient.setZkSerializer(new MyZkSerializer());
        //获取根节点
        String path = "/mykt";
        boolean exists = zkClient.exists(path);
        //读取根节点数据
        //byte[] data = zooKeeper.getData(path, false, new Stat());
        //判空
        if (!exists) {
            return null;
        }
        //初始化list 用来返回 服务名
        List<String> serverNames = new ArrayList<>();
        //查询子节点
        List<String> children = zkClient.getChildren(path);
        if (children != null && children.size() > 0) {
            for (String child : children) {
                //获取子节点内的数据
                String childrenData = zkClient.readData(path + "/" + child);
                //Object o = zkClient.readData(path + "/" + child);
                //System.out.println(o);
                //byte[] childrenData = zooKeeper.getData(path + "/" + child, false, new Stat());
                //log.info(new String(childrenData));
                serverNames.add(childrenData);
            }
        }
        return serverNames;
    }
}
