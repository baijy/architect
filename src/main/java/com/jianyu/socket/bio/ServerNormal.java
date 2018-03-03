package com.jianyu.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO测试服务端
 * @author BaiJianyu
 *
 */
public class ServerNormal {
	private static int DEAFAULT_PORT = 12345;
	private static ServerSocket server;

	public static void start() {
		start(DEAFAULT_PORT);
	}

	public synchronized static void start(int port) {
		if (server != null) {
			return;
		}

		try {
			server = new ServerSocket(port);
			System.out.println("服务已启动，端口号：" + port);

			while (true) {
				Socket socket = server.accept();
				new Thread(new ServerHandler(socket)).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				System.out.println("服务器已关闭。");
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				server = null;
			}
		}

	}
}
