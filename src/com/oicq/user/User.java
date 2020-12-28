package com.oicq.user;

import java.util.Vector;

import com.oicq.config.UserInfo;


public final class User extends UserInfo {
	private static final long serialVersionUID = -2844611810327524136L;
	public User(String userId, String userName, String userEmail, String userSex, String userBirthday,
			String userAvatar, String userTrades, String userRegistertime, Vector<Friends> friends) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userSex = userSex;
		this.userBirthday = userBirthday;
		this.userAvatar = userAvatar;
		this.userTrades = userTrades;
		this.userRegistertime = userRegistertime;
		this.friends = friends;
	}

	public String getUserId() {
		return userId;
	}


	public String getUserName() {
		return userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public String getUserSex() {
		return userSex;
	}


	public String getUserBirthday() {
		return userBirthday;
	}


	public String getUserAvatar() {
		return userAvatar;
	}


	public String getUserTrades() {
		return userTrades;
	}


	public String getUserRegistertime() {
		return userRegistertime;
	}

	public Vector<Friends> getFriends() {
		return friends;
	}


}
