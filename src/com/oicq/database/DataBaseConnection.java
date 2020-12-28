package com.oicq.database;

import java.sql.*;
import com.oicq.config.ServerInfo;

public final class DataBaseConnection {

	private Connection conn = null;  //创建Connection对象，以便连接数据库
	private Statement psql = null;  //创建Statement对象，用于处理sql语句。
	private ResultSet resultSet = null; //ResultSet对象，结果集对象。 封装了结果集的对象：内部有一个可移动的光标，默认情况，指向第一条记录集的上一条记录：

	public DataBaseConnection() {         //连接数据库方法
		String dbDriver = "com.mysql.cj.jdbc.Driver";// 数据库驱动名
		// 数据库所在域
		String dbUrl = "jdbc:mysql://" + ServerInfo.MYSQL_IP + ":" + ServerInfo.MYSQL_PORT + "/" + ServerInfo.DB_NAME+ "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
		try {
			// 加载驱动
			Class.forName(dbDriver);
			// 获取连接对象
			conn = DriverManager.getConnection(dbUrl, ServerInfo.DB_USER_NAME, ServerInfo.DB_USER_PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("无法连接到数据库：" + e.getMessage());
		}
	}

	public ResultSet getFromDatabase(String sql) {
		try {
			psql = conn.createStatement();// 创建对象参数化 sql
			resultSet = psql.executeQuery(sql);	// 开始查询
		} catch (SQLException e) {
			System.out.println("数据库查询失败：" + e.getMessage());
		}
		return resultSet;
	}

	public void putToDatabase(String sql) {        //更新数据库操作
		try {
			psql = conn.createStatement();
			psql.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("更新数据库异常：" + e.getMessage());
		}
	}

	public void close() {   //数据库关闭方法
		try {
			if (resultSet != null && !resultSet.isClosed())
				resultSet.close();
		} catch (SQLException e) {
			System.out.println("结果集关闭异常：" + e.getMessage());
		}
		try {
			if (psql != null && !psql.isClosed())
				psql.close();
		} catch (SQLException e) {
			System.out.println("更新集关闭异常：" + e.getMessage());
		}
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("数据库连接关闭异常：" + e.getMessage());
		}
	}
	public static void main(String[] args) {
		new DataBaseConnection();
	}
}
