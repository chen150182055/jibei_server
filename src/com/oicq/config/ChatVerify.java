package com.oicq.config;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class ChatVerify implements Serializable {
	private static final long serialVersionUID = -4490443980607193791L;
	private String userId;     //定义用户的用户名
	private String userPassword;   //定义用户的用户密码

	public ChatVerify(String userId, String userPassword) {  //创建一个存储用户ID、密码经过MD5加密后的对象
		this.userId = userId;
		this.userPassword = getMd5(userPassword);
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	private String getMd5(String str) {     //MD5加密方法，传入密码str
		String mdPassword = "";             //先为加密后的密码定义一个String变量
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");  //实例化一个MessageDigest对象，通过提供的静态的getInstance方法。方法中参数指的是加密的算法此处为MD5加密，大小写无所谓。
			// 计算md5函数
			md.update(str.getBytes());  //输入待加密的字符串，String的getBytes()方法是得到一个操作系统默认的编码格式的字节数组
			// 保留16位
			mdPassword = new BigInteger(1, md.digest()).toString(16);   //md.digest是加密之后生成的密文的字节数组 .toString是将其取出16位并返回
		} catch (NoSuchAlgorithmException e) {
			System.out.println("MD5加密失败：" + e.getMessage());
		}
		return mdPassword;      //返回加密后的密文
	}
}