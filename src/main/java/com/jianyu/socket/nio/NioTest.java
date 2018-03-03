package com.jianyu.socket.nio;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NioTest {
	// 测试主方法
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// 运行服务器
		Server.start();
		// 避免客户端先于服务器启动前执行代码
		TimeUnit.SECONDS.sleep(1);
		// 运行客户端
		Client.start();
		while (Client.sendMsg(new Scanner(System.in).nextLine()))
			;
	}
}
