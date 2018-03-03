package com.jianyu.socket.bio;

import java.util.concurrent.TimeUnit;

public class BioTest {
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ServerNormal.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		// 等待服务端启动完毕后再启动客户端
		TimeUnit.SECONDS.sleep(1);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						Client.send();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
