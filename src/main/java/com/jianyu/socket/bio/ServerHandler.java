package com.jianyu.socket.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * 服务端消息处理
 * @author BaiJianyu
 *
 */
public class ServerHandler implements Runnable {

	private Socket socket;

	// 构造函数
	public ServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;

		try {

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String message;
			while (true) {
				if ((message = in.readLine()) == null) {
					break;
				} else {
					System.out.println("服务端收到客户端发来的消息：" + message);
					// 发送回复
					out.println("我是小明，我很好，"+System.currentTimeMillis());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				in = null;
			}

			if (out != null) {
				out.close();
				out = null;
			}

			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				socket = null;
			}
		}
	}
}
