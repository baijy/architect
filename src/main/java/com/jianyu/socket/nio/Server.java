package com.jianyu.socket.nio;

public class Server {
	private static int DEAFAULT_PORT = 12345;
	private static ServerHandler serverHandler;

	public static void start() {
		start(DEAFAULT_PORT);
	}

	public synchronized static void start(int port) {
		if (serverHandler != null) {
			serverHandler.stop();
		} else {
			serverHandler = new ServerHandler(port);
			new Thread(serverHandler).start();
		}
	}
	
	public static void main(String[] args) {
		start();
	}
}
