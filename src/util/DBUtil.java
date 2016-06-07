package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static String db = "sqlite";
	public ResultSet rs = null;
	public Connection _conn = null;
	
	public static Connection getConnection() {
		Connection connection=null;
		switch (db) {
			case "sqlite":
				connection = getSqliteConnection();
				break;
			case "mysql":
				connection = getmMySqlConnection();
				break;
	
			}
		return connection;
	}

	public static Connection getSqliteConnection(){
		String driver="org.sqlite.JDBC";
		String conStr="jdbc:sqlite:db/guitar.db";
		Connection conn=null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(conStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;			
	}
	
	public static Connection getmMySqlConnection(){
		String driver="com.mysql.jdbc.Driver";
		String conStr="jdbc:mysql:db/guitar.db";
		Connection conn=null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(conStr,"sa","123456");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;			
	}
	
	public ResultSet query(String mySql) throws Exception {
		try {
			_conn = getConnection();
			Statement stmt = _conn.createStatement();
			rs = stmt.executeQuery(mySql);
			stmt.close();
			_conn.close();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库查询异常："+e.getMessage());
		}
		return null;
	}

	public void update(String mySql) throws Exception {
		try {
			_conn = getConnection();
			Statement stmt = _conn.createStatement();
			stmt.executeUpdate(mySql);
			stmt.close();
			_conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库更新异常："+e.getMessage());
		}
	}
	
}
