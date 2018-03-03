package com.jianyu.socket.bio;

public class PackageInfo {
	/**
	 * 参考：
	 * http://blog.csdn.net/anxpp/article/details/51512200
	 * 
	 * 总结起来：
	 * 服务端代码：
	 * ServerScoket server = new ServerSocket("12345");
	 * Socket socket = server.accept();
	 * InputStream inputStream = socket.getInputStream();
	 * BufferReader reader = new InputStreamReader(inputStream);
	 * while ((message = in.readLine()) != null) { System.out.println("服务端收到消息："+message)};
	 * PrintWriter out = new PrintWriter();
	 * out.println("服务端发送回复");
	 * 
	 * 客户端代码：
	 * socket = new Socket("127.0.0.1", "12345");
	 * PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	 * out.println("嘿嘿嘿？" + System.currentTimeMillis());
	 * BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	 * System.out.println("客户端收到回复：" + in.readLine());
	 * 
	 * 
	 */
}
