package com.wenhao.zookeeperdemo.distributedlock.util;

/*public class ZkUtils {

    public static final String host = "127.0.0.1";
    public static final int timeout = 10000;
    public ZooKeeper zooKeeper = new ZooKeeper(host,timeout,null);

    *//*public static void openZk() throws IOException {
        zooKeeper = new ZooKeeper(host, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getState().name());
            }
        });
    }

    public static void createNode(String path, byte[] data, CreateMode createMode) throws KeeperException, InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
        }
    }

    public static void closeZk() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
            } catch (Exception e) {

            }

        }
    }*//*


}*/
