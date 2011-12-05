package me.herghost.Fiery.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.herghost.Fiery.util.Configuration;


public class sqlFunctions 

{
	    
	public void create_table_users() throws SQLException
	{ 
		String user = Configuration.getString("settings.mysql.user");
		String pass = Configuration.getString("settings.mysql.pass");
		String url = "jdbc:mysql://localhost:3306/Fiery";
		Connection conn = DriverManager.getConnection(url, user, pass); 
		PreparedStatement sampleQueryStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users (p_id int NOT NULL AUTO_INCREMENT KEY, p_name varchar(255), p_ip varchar(255))"); 
		sampleQueryStatement.executeUpdate();
		sampleQueryStatement.close(); 
		conn.close(); 
	}
	


	public void create_table_userhomes() throws SQLException
	{
		String user = Configuration.getString("settings.mysql.user");
		String pass = Configuration.getString("settings.mysql.pass");
		String url = "jdbc:mysql://localhost:3306/Fiery";
		Connection conn = DriverManager.getConnection(url, user, pass); 
		PreparedStatement sampleQueryStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS userhomes (world varchar(128), p_name varchar(255) not null, home_x varchar(500), home_y varchar(500), home_z varchar(500), world1 varchar(128), home1_x varchar(500), home1_y varchar(500), home1_z varchar(500), world2 varchar(128), home2_x varchar(500), home2_y varchar(500), home2_z varchar(500), primary key(p_name))");
		sampleQueryStatement.executeUpdate();
		sampleQueryStatement.close(); 
		conn.close(); 
	}



	public void create_table_money() throws SQLException 
	{
		String user = Configuration.getString("settings.mysql.user");
		String pass = Configuration.getString("settings.mysql.pass");
		String url = "jdbc:mysql://localhost:3306/Fiery";
		Connection conn = DriverManager.getConnection(url, user, pass); 
		PreparedStatement sampleQueryStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS money (p_name varchar(128), balance int)");
		sampleQueryStatement.executeUpdate();
		sampleQueryStatement.close(); 
		conn.close(); 
		
	}

	

	
		
	}
