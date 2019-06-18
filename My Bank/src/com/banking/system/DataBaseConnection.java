package com.banking.system;
import java.sql.*;
public class DataBaseConnection {

	public static Connection connectToDatabase() throws ClassNotFoundException, SQLException {
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system","root","");
		return connection;
		
	}
}
