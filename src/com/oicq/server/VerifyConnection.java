package com.oicq.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.oicq.config.ChatVerify;
import com.oicq.database.DataBaseConnection;
import com.oicq.database.DataCheck;


public final class VerifyConnection implements Runnable {  //该类用来验证客户端的登录请求，聊天请求等
	private Socket userSocket;    //客户端Socket对象

	public VerifyConnection(Socket userSocket) {   //将客户端的Socket对象置为userSocket
		this.userSocket = userSocket;
	}

	@Override
	public void run() {
		try {
			//1.先建立输入输出流，应为前面已经连接到了Socket端口，并且该端口的套接字对象已经被传入到了本类中
			// 对象输入输出流
			ObjectInputStream in = new ObjectInputStream(userSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(userSocket.getOutputStream());
			while (true) {
				Object obj = in.readObject();		// 接收一个对象流
				Object result = switchCon(obj);      // 获取处理结果，调用该方法用来处理各种客户端的请求并返回
				out.writeObject(result);           // 返回给客户端这个处理结果，将返回的结果写入输出流传输给客户端
				in.close();// 关闭所有流
				userSocket.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("VerifyConnection 与服务器失去联系 ：" + e.getMessage());
		}
	}

	public Object switchCon(Object obj) {     //不同的处理事件有不同的处理方法
		DataCheck check = new DataCheck();// 创建 Data_Check 来完成这些工作
		String objName = obj.getClass().getSimpleName();// 获取对象类型即获取该基类的名字
		Object result = null; // 返回结果对象
		System.out.println("当前处理对象类型为 " + objName);
		switch (objName) {
		// 登录验证
		case "ChatVerify":              //如果为ChatVerify类型的事件 ，即为验证用户是否登录
			ChatVerify loginVerify = (ChatVerify) obj;         //先将传入的对象转换为ChatVerify对象进行初步处理
			if (ChatServer.getClientUser().containsKey(loginVerify.getUserId())) // 查找ChatServer类中的HashMap从而查询该用户是否登录
				result = "Repeat_login";     //重复登录
			else {
				result = check.isLoginSuccess(loginVerify.getUserId(), loginVerify.getUserPassword()); //调用用户登录验证方法
				if (result != null && (Boolean) result == true) {
					System.out.println("登录成功，为该用户创建一个聊天线程");
					new Thread(new ChatThread(loginVerify.getUserId())).start();// 为登录成功的用户创建聊天线程
				}
			}
			break;

		case "String":           //如果为String类型的事件
			// 获取字符串内容
			String field = obj.toString();
			// 如果为获取信息
			if (field.startsWith("getUserInfo")) {                   //如果客户端发送的是getUserInfo类型的信息
				field = field.replace("getUserInfo", "");
				result = check.getUserInfo(field);
			} else if (field.startsWith("getChatRecord")) {           //如果客户端发送的是getChatRecord类型的信息
				// 获取聊天记录
				String res[] = field.split("```", 4);
				if (res.length == 4) {			//res[0]：getChatRecord、res[1]：fromId、res[2]：toId
					result = check.getChatRecord(res[1], res[2]);
				}
			} else if (field.startsWith("setMyTrades")) {                //如果客户端发送的是setMyTrades类型的信息
				// 替换前缀
				String res[] = field.split("```", 3);
				if (res.length == 3) {			//res[0]：setMyTrades、res[1]：myId、res[2]：newTrades
					DataBaseConnection con = new DataBaseConnection();
					String sql = "UPDATE dw_user SET user_trades = '" + res[2] + "' WHERE user_id = " + res[1];
					con.putToDatabase(sql);
					con.close();
				}
			}
			break;
		default:
			break;
		}
		return result;
	}


}
