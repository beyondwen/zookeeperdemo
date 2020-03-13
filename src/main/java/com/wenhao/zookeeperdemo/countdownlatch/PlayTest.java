package com.wenhao.zookeeperdemo.countdownlatch;

import lombok.extern.slf4j.Slf4j;
import org.wenhao.zkclient.ZkClient;

@Slf4j
public class PlayTest {
    //zk 服务端ip
    public static final String host = "11.0.0.110";
    //超时时间
    public static final int timeout = 500000;

    public static ZkClient zkClient = new ZkClient(host, timeout);

    public static final String lockPath = "/play";

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PlayService playService = new PlayService();
                    playService.start();
                    //playService.rest();
                    /*if (start){
                        log.info("玩家"+Thread.currentThread().getName()+"结束玩耍");
                    }*/
                    //playService.reste();
                }
            }).start();
        }
    }
}
