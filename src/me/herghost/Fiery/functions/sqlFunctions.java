package me.herghost.Fiery.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.plugin.Plugin;


public class sqlFunctions 

{
	    private Plugin plugin;
	    private String user;
	    private String pass;
	    private String url;
		
		
	
	
	public sqlFunctions(Plugin plugin) 
	{
	    this.plugin = plugin;
		this.user = plugin.getConfig().getString("settings.mysql.user");
	    this.pass = plugin.getConfig().getString("settings.mysql.pass");
	    this.url = "jdbc:mysql://localhost:3306/Fiery";	
	}

	public void create_table_users() throws SQLException
	{ 
		Connection conn = DriverManager.getConnection(url, user, pass); 
		PreparedStatement sampleQueryStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users (p_id int NOT NULL AUTO_INCREMENT KEY, p_name varchar(255), p_ip varchar(255))"); 
		sampleQueryStatement.executeUpdate();
		sampleQueryStatement.close(); 
		conn.close(); 
	}
	


	public void create_table_userhomes() throws SQLException
	{
		Connection conn = DriverManager.getConnection(url, user, pass); 
		PreparedStatement sampleQueryStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS userhomes (world varchar(128), p_name varchar(255) not null, home_x varchar(500), home_y varchar(500), home_z varchar(500), world1 varchar(128), home1_x varchar(500), home1_y varchar(500), home1_z varchar(500), world2 varchar(128), home2_x varchar(500), home2_y varchar(500), home2_z varchar(500), primary key(p_name))");
		sampleQueryStatement.executeUpdate();
		sampleQueryStatement.close(); 
		conn.close(); 
	}

	

	
		
	}
