package com.jianyu.api.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class SessionDemo {
    private final static String CONN_STR ="192.168.220.128:2181,192.168.220.129:2181," +
            "192.168.220.130:2181";
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(CONN_STR,4000);
        System.out.println(zkClient+" - > success");
    }
}
