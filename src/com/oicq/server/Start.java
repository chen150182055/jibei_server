package com.oicq.server;

public class Start {
	public static void main(String[] args) {
		new Thread(new VerifyThread()).start();  //启动一个身份验证线程，先验证登录是否成功
		System.out.println("服务端已成功创建,当前在线人数：0");
	}
}
//1.首先，创建一个用来启动验证用户登录验证类的线程