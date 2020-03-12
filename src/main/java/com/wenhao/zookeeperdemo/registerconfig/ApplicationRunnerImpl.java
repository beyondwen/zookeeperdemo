package com.wenhao.zookeeperdemo.registerconfig;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    /*@Value("${server.port}")
    private String serverPort;*/

    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }

    public void start() throws IOException, KeeperException, InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.47.128", 500000);
        String path = "/mykt";
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                log.info(dataPath + ">>>>>>>>>>>>>" + data + ">>>>" + "数据改变了");
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                log.info(dataPath + ">>>>>>>>>>>>>" + "数据删除");
            }
        });
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                log.info("节点状态被改变");
            }

            @Override
            public void handleNewSession() throws Exception {
                log.info("节点NewSession");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable error) throws Exception {
                log.info("异常");
            }
        });
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                for (String currentChild : currentChilds) {
                    log.info("父节点" + parentPath + ">>>" + "子节点" + currentChild);
                }
            }
        });
        /*ZooKeeper zooKeeper = new ZooKeeper("192.168.47.128", 100000, new Watcher() {
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
        zooKeeper.create(path + "/" + serverPort, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);*/

    }
}
