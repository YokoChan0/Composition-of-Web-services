package myInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class myDatabase {
	final String driver = "com.mysql.cj.jdbc.Driver";
	final String url = "jdbc:mysql://localhost:3306/myservices";
	String name = null;
	String passWord = null;
	Connection conn = null;
	Statement sta = null; 
	public myDatabase(String name0,String pass) {
		name = name0;
		passWord = pass;
	}
	public void connect() throws Exception{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,name,passWord);
			//System.out.println("Connected");
			sta = conn.createStatement();
	}
	public Statement getStatement(){
		return sta;
	}
	public Connection getConnection(){
		return conn;
	}

	public void closeConnect() throws Exception{
		sta.close();
		conn.close();
	}
	public PreparedStatement prepareStatement(String req) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
