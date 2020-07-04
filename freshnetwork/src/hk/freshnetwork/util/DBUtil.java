package hk.freshnetwork.util;

import java.sql.Connection;

public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/freshnetwork?characterEncoding=utf8&useSSL=false";
	private static final String dbUser="root";
	private static final String dbPwd="hk1577279487";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		Connection conn= java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
		System.out.println(conn);
		return conn;
	}
}
