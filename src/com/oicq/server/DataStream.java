package com.oicq.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;
import com.oicq.database.DataBaseConnection;
import com.oicq.database.DataCheck;


public final class DataStream implements Runnable {      //3.实现与客户端进行数据交换的类

	private DataInputStream in; //数据输入流
	private DataOutputStream out; //数据输出流
	private String userId;  //用户ID
	private Vector<String> friends;  //用户好友
	private DataBaseConnection con; //数据库连接类DataBaseConnection对象

	public DataStream(Socket clientSocket, String userId) {  //构造方法
		this.userId = userId;  //将本类用户ID初始化
		con = new DataBaseConnection(); //建立数据库连接
		friends = DataCheck.getFriendMember(userId);  //通过用户Id查询其所有的好友
		try {
			in = new DataInputStream(clientSocket.getInputStream());  //获取客户端的输入流
			out = new DataOutputStream(clientSocket.getOutputStream()); //获取客户端的输出流
		} catch (IOException e) {
			System.out.println("创建聊天数据传输流失败：" + e.getMessage());
		}
		sendToAllFriends("OnlineSituation```在线```" + userId);  //将登陆状态信息发送给其所有的好友
	}

	/*
	 run() 接收客户端发送来的请求
	 因为数据输入流读取时候会阻塞，所以将其单独分配在一个线程里面，成功读取信息之后执行处理函数
	 与客户端连接中断之后抛出该异常，此时从服务端在线用户表中删除该用户
	 */
	@Override
	public void run() {
		String scMessage;  //先创建一个String用来存放即将传输来的信息
		try {
			while (true) {
				scMessage = in.readUTF();   //将传输过来的信息读出并初始化给scMessage
				execute(scMessage);			//调用该类的execute方法去更细化的处理这条信息
			}
		} catch (IOException e) {
			ChatServer.getClientUser().remove(userId);// 离线后删除用户在线信息
			sendToAllFriends("OnlineSituation```离线```" + userId);// 通知所有好友离线情况
			System.out.println("删除了key为" + userId + "的hashmap值，剩余在线人数 ：" + ChatServer.getClientUser().size());
			con.close();// 关闭为该用户创建的数据库连接
		}
	}

	private void execute(String message) {    //处理聊天信息的方法
		String res[] = message.split("```", 4);
		if (res.length == 4) {
			/*
			 * res[0]：发送标识 、res[1]：fromId 、res[2]：toId 、res[3]：消息内容
			 */
			String type = res[0];
			String toId = res[2];
			message = new Date().toString() + "```" + message;
			if (type.equals("toFriend")) { //如果是toFriend类型的信息
				if (ChatServer.getClientUser().containsKey(toId)) {  //并且存在用户Id为toId的HashMap对象
					ChatServer.getClientUser().get(toId).send(message); //调用DataStream对象的send（）方法将message发送给ID为toID的客户端，即接收方的客户端
				} else {
					// 好友不在线的情况
				}
				printToDatabase(res[1], res[2], res[3]);
			}
		} else {
			System.out.println("收到的信息不规范：" + message);
		}
	}

	private void sendToAllFriends(String message) {  //将信息发送给全部好友
		for (int i = 0; i < friends.size(); i++) {  //遍历所有的好友
			if (ChatServer.getClientUser().containsKey(friends.get(i))) {  //并且存在以该好友Id为键的HashMap对象
				ChatServer.getClientUser().get(friends.get(i)).send(message);  //获取以该好友Id为键的HashMap对象的值，即DataStream对象，并调用send（）方法发送message
			}
		}
	}

	private void send(String message) {
		try {
			out.writeUTF(message);  //将message写入输出流
		} catch (IOException e) {
			System.out.println(userId + "发送到客户端失败：" + e.getMessage());
		}
	}

	private void printToDatabase(String fromId, String toId, String message) {   //调用该方法插入到数据库中
		String sql;
			sql = "insert into dw_userchat (uchat_fromid,uchat_toid,uchat_message) values(" + fromId + "," + toId + ",'" + message + "')";
		con.putToDatabase(sql);
	}
}
