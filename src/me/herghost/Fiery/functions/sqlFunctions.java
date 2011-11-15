package me.herghost.Fiery.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sqlFunctions {
	
	String user = "bukkitdev";
	String pass = "bukkitdev";
	String url = "jdbc:mysql://localhost:3306/Fiery"; 
	
		
	
	public void create_tables() throws SQLException { 
		Connection conn = DriverManager.getConnection(url, user, pass); 
		PreparedStatement sampleQueryStatement = conn.prepareStatement("CREATE TABLE users (id int)"); 
		sampleQueryStatement.executeUpdate(); 
		sampleQueryStatement.close(); 
		conn.close(); 
	}

}
