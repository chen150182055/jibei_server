package com.oicq.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.oicq.config.UserInfo;
import com.oicq.config.UserInfo.Friends;
import com.oicq.server.ChatServer;
import com.oicq.user.User;

public final class DataCheck {       //查询用户是否在数据库中能够查询
	public Boolean isLoginSuccess(String userId, String userPassword) { //用户输入的用户名和密码传入该方法
		Boolean isSuccess = new Boolean(false);     //先标记查询成功为false
		try {
			// 查询该用户是否存在
			DataBaseConnection dataCon = new DataBaseConnection();
			String sql = "select * from dw_user where user_id = " + userId + " and user_password = '" + userPassword + "'";
			System.out.println("1  "+userId+ " "+userPassword);
			isSuccess = dataCon.getFromDatabase(sql).next();      //如果存在用户，便返回true，next（）的返回值为Boolean变量
			dataCon.close();        // 关闭与服务器连接对象
		} catch (SQLException e) {
			System.out.println("身份验证信息查询失败:" + e.getMessage());
		}
		return isSuccess;
	}

	private static Vector<String> getMemberFromId(String sql, String row) {         //查询好友或者是群聊中的成员，因为好友或者是群聊中的成员不可能只有一个人，故这里采用向量存储
		DataBaseConnection dataCon = new DataBaseConnection();// 与数据库创建连接
		Vector<String> member = new Vector<String>();// 最终结果Vector数组
		ResultSet resultSet = dataCon.getFromDatabase(sql);// 利用该sql语句查询，返回ResultSet结果集
		try {
			while (resultSet.next()) {
				member.add(resultSet.getString(row));
			}
			dataCon.close();// 关闭连接
		} catch (SQLException e) {
			System.out.println("查询成员ID列表失败：" + e.getMessage());
		}
		return member;
	}


	public static Vector<String> getFriendMember(String myselfId) {      //查询用户的所有好友ID，该方法是基于上面的方法查询
		String sqlString = "select myfriend from dw_useruser where myself = " + myselfId;
		return getMemberFromId(sqlString, "myfriend");
	}

	public Vector<Friends> getUserFriends(String userId, DataBaseConnection dataCon) {   //该方法用来查询好友的信息，例如好友的ID，名字，头像，个性签名，登录状态等
		Vector<Friends> friends = new Vector<Friends>();
		String sqlString = "select * from view_useruser where myself = " + userId;// 查询好友信息
		ResultSet resultSet = dataCon.getFromDatabase(sqlString);
		try {
			while (resultSet.next()) {
				String fId = resultSet.getString("myfriend");
				String fName = resultSet.getString("user_name");
				String fAvatar = resultSet.getString("user_avatar");
				String fTrade = resultSet.getString("user_trades");
				String fStatus = ChatServer.getClientUser().containsKey(fId) ? "在线" : "离线";
				friends.add(new Friends(fId, fName, fAvatar, fTrade, fStatus));
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("获取好友信息失败 " + e.getMessage());
		}
		return friends;
	}

	public UserInfo getUserInfo(String userId) {   //获取用户信息(包括个人资料，群列表及资料，好友列表及资料)
		// 用户个人信息
		String userName = "";
		String userEmail = "";
		String userSex = "";
		String userBirthday = "";
		String userAvatar = "";
		String userTrades = "";
		String userRegistertime = "";
		Vector<Friends> friends;
		UserInfo userInfo = null;
		try {
			DataBaseConnection dataCon = new DataBaseConnection();// 创建数据库连接
			String sqlString = "select * from dw_user where user_id = " + userId;// 查询个人信息
			ResultSet resultSet = dataCon.getFromDatabase(sqlString);
			while (resultSet.next()) {
				userName = resultSet.getString("user_name");
				userEmail = resultSet.getString("user_email");
				userSex = resultSet.getString("user_sex");
				userBirthday = resultSet.getString("user_birthday");
				userAvatar = resultSet.getString("user_avatar");
				userTrades = resultSet.getString("user_trades");
				userRegistertime = resultSet.getString("user_registertime");
			}
			resultSet.close();
			friends = getUserFriends(userId, dataCon);// 查询好友列表信息与群列表信息
			dataCon.close();// 关闭数据库连接

			userInfo = new User(userId, userName, userEmail, userSex, userBirthday, userAvatar, userTrades, userRegistertime, friends);// 创建用户信息对象
		} catch (SQLException e) {
			System.out.println("获取用户信息失败：" + e.getMessage());
		}
		return userInfo;
	}

	public Vector<String> getChatRecord(String fromId, String toId) {  //获取聊天记录
		Vector<String> all = new Vector<String>(); //所有的好友
		DataBaseConnection dataCon = new DataBaseConnection();// 创建数据库连接对象
		String sqlString = "";
			sqlString = "select uchat_fromid fromid,uchat_toid toid,uchat_message message,uchat_datetime timer from dw_userchat where (uchat_fromid =" + fromId + " and uchat_toid = " + toId + ") or (uchat_fromid = " + toId + " and uchat_toid = " + fromId + ")";
		ResultSet resultSet = dataCon.getFromDatabase(sqlString);
		try {
			String tmp = "";
			while (resultSet.next()) {
				tmp = resultSet.getString("timer") + "```";
				tmp += resultSet.getString("fromid") + "```";  //tmp=tmp+resultSet.getString("fromid") + "```"
				tmp += resultSet.getString("toid") + "```";
				tmp += resultSet.getString("message");
				all.add(tmp);
			}
			// 关闭连接
			dataCon.close();
		} catch (SQLException e) {
			System.out.println("获取聊天记录信息失败：" + e.getMessage());
		}
		return all;
	}
}
