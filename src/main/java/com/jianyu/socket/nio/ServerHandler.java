package com.jianyu.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ServerHandler implements Runnable {
	private Selector selector;
	private ServerSocketChannel serverChannel;
	private volatile boolean started;

	public ServerHandler(int port) {
		try {
			// 创建选择器
			selector = Selector.open();
			// 打开监听通道
			serverChannel = ServerSocketChannel.open();
			// 如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
			serverChannel.configureBlocking(false);
			// 绑定端口 backlog设为1024
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);
			// 监听客户端连接请求
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			//
			started = true;
			System.out.println("服务器已启动，端口号：" + port);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1); // 中断JVM
		}
	}

	public void stop() {
		started = false;
	}

	public void run() {
		while (started) {
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handlerInput(key);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (selector != null) {
			try {
				selector.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void handlerInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			if (key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				// 通过ServerSocketChannel的accept创建SocketChannel实例
				// 完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
				SocketChannel sc = ssc.accept();
				// 设置为非阻塞的
				sc.configureBlocking(false);
				// 注册为读
				sc.register(selector, SelectionKey.OP_READ);
			}

			if (key.isReadable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(buffer);

				if (readBytes > 0) {
					buffer.flip();
					byte[] bytes = new byte[buffer.remaining()];
					buffer.get(bytes);
					String message = new String(bytes, "UTF-8");
					System.out.println("服务器收到消息：" + message);

					doWrite(sc, "回复消息");

				} else {
					key.cancel();
					sc.close();
				}
			}
		}
	}

	private void doWrite(SocketChannel sc, String response) throws IOException {
		//将消息编码为字节数组
		byte[] bytes = response.getBytes();
		//根据数组容量创建ByteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		//将字节数组复制到缓冲区
		writeBuffer.put(bytes);
		//flip操作
		writeBuffer.flip(); //把buffer的当前位置更改为buffer缓冲区的第一个位置
		//发送缓冲区的字节数组
		sc.write(writeBuffer);
	}

}
