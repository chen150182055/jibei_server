package com.oicq.server;
import java.io.IOException;
import java.net.Socket;
import com.oicq.config.ServerInfo;

public final class ChatThread implements Runnable {     //4.创建用户聊天线程

	private String userId;  //用户Id
	private Socket userSocket;  //Socket对象用来连接到ServerSocket对象
	private static ChatServer chatServerInfo;  //ChatServer对象
	static {
		chatServerInfo = new ChatServer(ServerInfo.CHAT_PORT); //初始化ChatServer对象，即将服务器端口号传递给其构造器
	}

	public ChatThread(String userId) {
		this.userId = userId;
	}

	@Override
	public void run() {
		// 先与客户端建立聊天端口的连接
		try {
			userSocket = chatServerInfo.getServerSocket().accept();   //与客户端的聊天端口进行连接
		} catch (IOException e) {
			System.out.println("未能与客户端成功建立连接：" + e.getMessage());
			return;
		}
		DataStream dataStream = new DataStream(userSocket, userId);// 成功接入之后建立数据流（初始化数据流）
		ChatServer.getClientUser().put(userId, dataStream);// 加入到在线人员映射里面
		System.out.println("用户 " + userId + " 已成功登录 ,Info: " + userSocket.getInetAddress());
		System.out.println("当前在线人数： " + ChatServer.getClientUser().size());
		dataStream.run();  //启动数据流线程
	}
}
