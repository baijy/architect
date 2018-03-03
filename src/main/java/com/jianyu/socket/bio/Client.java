package com.jianyu.socket.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * BIO测试客户端
 * 
 * @author BaiJianyu
 *
 */
public class Client {
	private static String DEAFAULT_SERVER_IP = "127.0.0.1";
	private static int DEAFAULT_SERVER_PORT = 12345;

	public static void send() {
		send(DEAFAULT_SERVER_PORT);
	}

	public static void send(int port) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			socket = new Socket(DEAFAULT_SERVER_IP, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("小明，你好吗？" + System.currentTimeMillis());
			System.out.println("客户端收到回复：" + in.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				in = null;
			}

			if (null != out) {
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
