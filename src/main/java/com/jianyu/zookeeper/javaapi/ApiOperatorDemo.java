package com.jianyu.zookeeper.javaapi;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApiOperatorDemo implements Watcher{
    private final static String CONN_STR ="192.168.220.128:2181,192.168.220.129:2181," +
            "192.168.220.130:2181";
    private static ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper(CONN_STR,500,new ApiOperatorDemo());

        //创建节点（持久节点不可以重复创建）
        //String result = zooKeeper.create("/node1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //System.out.println("创建成功："+result);

        String result = zooKeeper.create("/fordelete","嘿嘿嘿".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.delete("/fordelete",-1);

        //获取指定节点下的子节点
       List<String> childrens = zooKeeper.getChildren("/",true);
        System.out.println(childrens);

        //修改子路径
        zooKeeper.setData("/node1","xixihaha".getBytes(),-1);
        TimeUnit.SECONDS.sleep(1);

    }

    public void process(WatchedEvent watchedEvent) {

    }
}
