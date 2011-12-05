package me.herghost.Fiery;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import me.herghost.Fiery.util.Configuration;


import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class FieryPlayerListener extends PlayerListener 
 

{
	
	public static Fiery plugin;
	
	public FieryPlayerListener(Fiery instance) 
	{
		plugin = instance;
	}

	private String user;
	private String pass;
	private String url;
	private String startingbalance;
    	
		
	
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		user = Configuration.getString("settings.mysql.user");
	    pass = Configuration.getString("settings.mysql.pass");
		url = "jdbc:mysql://localhost:3306/Fiery";
		startingbalance = Configuration.getString("money.startingbalance");
		
		String thisplayer = event.getPlayer().getName();
		InetSocketAddress playerip = event.getPlayer().getAddress();
		
		
		try
		{
			Connection conn = DriverManager.getConnection(url, user, pass); 
			ResultSet rs =conn.createStatement().executeQuery("SELECT * FROM users WHERE p_name = '" + thisplayer + "'");
			if (!rs.next())
			{
				
				PreparedStatement sampleQueryStatement = conn.prepareStatement("INSERT INTO users (p_name,p_ip) VALUES ('" + thisplayer + "','" + playerip + "')");
		        sampleQueryStatement.executeUpdate(); 
				sampleQueryStatement.close();
				conn.close(); 
				
			}
	    }
		
	catch
		(SQLException e1) 
			{
				e1.printStackTrace();
        	}
		
		try
		{
			Connection conn = DriverManager.getConnection(url, user, pass); 
			ResultSet rs =conn.createStatement().executeQuery("SELECT * FROM money WHERE p_name = '" + thisplayer + "'");
			if (!rs.next())
			{
				
				PreparedStatement sampleQueryStatement = conn.prepareStatement("INSERT INTO money (p_name,balance) VALUES ('" + thisplayer + "','" + startingbalance + "')");
		        sampleQueryStatement.executeUpdate(); 
				sampleQueryStatement.close();
				conn.close(); 
				
			}
	    }
		
	catch
		(SQLException e1) 
			{
				e1.printStackTrace();
        	}
			
		}
	}

