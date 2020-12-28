package com.oicq.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;


public final class ChatServer {    //聊天服务
	private ServerSocket serverSocket;  //ServerSocket对象
	private InetAddress localHostAddress; //IP地址
	private int serverPort;
	private static HashMap<String, DataStream> clientUser;     //用户ID与其连接对象数据流之间的哈希映射

	// 静态初始化块
	static {
		clientUser = new HashMap<String, DataStream>();  //HashMap对象clientUser主要为以用户Id为键，用户传输数据流对象为值
	}

	public ChatServer(int port) {   //传入一个端口号将本类中的ServerSocket对象初始化，
		try {
			serverPort = port;// 监听端口
			serverSocket = new ServerSocket(serverPort);// 创建监听端口的ServerSocket对象
			localHostAddress = serverSocket.getInetAddress(); // 获取本机地址
		} catch (IOException e) {
			System.out.println("错误信息 ：" + e.getMessage());
		}
	}


	public int getServerPort() {
		return serverPort;
	}

	public String getLocalHostName() {
		return localHostAddress.getHostName();
	}

	public String getLocalHostAddress() {
		return localHostAddress.getHostAddress();
	}

	public int getClientNum() {
		return clientUser.size();
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static HashMap<String, DataStream> getClientUser() {
		return clientUser;
	}
}