package com.oicq.config;

import java.io.Serializable;
import java.util.Vector;


public class UserInfo implements Serializable {         //用户信息类

	private static final long serialVersionUID = 4146085358128616967L;

	protected String userId; //用户ID
	protected String userName;//用户昵称
	protected String userSex;//用户性别
	protected String userEmail;//用户邮箱
	protected String userBirthday; //用户生日
	protected String userAvatar;//用户头像
	protected String userTrades;//用户个性签名
	protected String userRegistertime; //用户注册时间
	protected Vector<Friends> friends = new Vector<Friends>();         //定义用户列表，用向量来存储可变的好友列表

	public static class Friends implements Serializable {         //用户群组类
		private static final long serialVersionUID = -1855195980029629286L;
		private String id;  //好友ID
		private String name; //好友昵称
		private String avatar;//好友头像
		private String trades; //好友个性签名
		private String status; //好友登录状态

		public Friends(String id, String name, String avatar, String trades, String status) {
			this.id = id;
			this.name = name;
			this.avatar = avatar;
			this.trades = trades;
			this.status = status;
		}

		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getAvatar() {
			return avatar;
		}
		public String getTrades() {
			return trades;
		}
		public String getStatus() {
			return status;
		}
	}
}
