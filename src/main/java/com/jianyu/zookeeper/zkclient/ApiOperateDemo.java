package com.jianyu.zookeeper.zkclient;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ApiOperateDemo implements Watcher {
    private final static String CONN_STR ="192.168.220.128:2181,192.168.220.129:2181," +
            "192.168.220.130:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper ;
    private static Stat stat= new Stat();


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(CONN_STR,5000,new ApiOperateDemo());

        // 创建节点
        String result = zooKeeper.create("/node1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);



    }

    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
            if(Event.EventType.None==watchedEvent.getType() && watchedEvent.getPath()==null){
                System.out.println(watchedEvent.getState()+"-->"+watchedEvent.getType());
            }
        }
    }
}
