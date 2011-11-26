package me.herghost.Fiery;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class FieryPlayerListener extends PlayerListener 

{
	
	String user = "bukkitdev";
	String pass = "bukkitdev";
	String url = "jdbc:mysql://localhost:3306/Fiery"; 
	
	public Fiery plugin;
	
	public FieryPlayerListener(Fiery plugin)
	{
		this.plugin = plugin;
	}
	
	
	
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		
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
	}
}
