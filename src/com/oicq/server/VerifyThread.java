package com.oicq.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.oicq.config.ServerInfo;


public final class VerifyThread implements Runnable {     //创建验证用户登录的验证线程
	private static ServerSocket serverSocket;         //创建ServerSocket类的对象
	static {
		serverSocket = new ChatServer(ServerInfo.VERIFY_PORT).getServerSocket();   //将serverSocket初始化，通过ServerInfo中的验证端口监听的端口号
	}

	@Override
	public void run() {
		try {
			while (true) {    //死循环始终重复下面的操作来，等待用户链接
				Socket userSocket = serverSocket.accept();   // 等待用户连接
				System.out.println(userSocket.getInetAddress() + " 发送来的新的验证");
				new Thread(new VerifyConnection(userSocket)).start();// 为用户接入创建一个验证线程
			}
		} catch (IOException e) {
			System.out.println("验证端口服务异常 ：" + e.getMessage());
		}
	}
}
//2.创建用户验证类