package me.herghost.Fiery.functions;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class sqlFunctions {
	
	
	String user = "bukkitdev";
	String pass = "bukkitdev";
	String url = "jdbc:mysql://localhost:3306/Fiery"; 
	
	public void create_tables() throws SQLException
	{ 
		Connection conn = DriverManager.getConnection(url, user, pass); 
		PreparedStatement sampleQueryStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users (p_id int NOT NULL AUTO_INCREMENT KEY, p_name varchar(255), p_ip varchar(255))"); 
		sampleQueryStatement.executeUpdate(); 
		sampleQueryStatement.close(); 
		conn.close(); 
	}


	
	
	

}
