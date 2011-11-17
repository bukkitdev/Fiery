package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sethomeCommand implements CommandExecutor {
	
	Logger log = Logger.getLogger("Minecraft");
	

	String user = "bukkitdev";
	String pass = "bukkitdev";
	String url = "jdbc:mysql://localhost:3306/Fiery"; 

	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		readCommand((Player) sender, commandLabel, args);
		return true;

	}
	
	private void readCommand(Player player, String cmd, String[] args )
	{
		
		if(cmd.equalsIgnoreCase("sethome")&& player instanceof Player)
		{
			if(args.length == 0)
			{
			Player p = (Player) player;
			double x = p.getLocation().getX();
			double y = p.getLocation().getY();
			double z = p.getLocation().getZ();
			String world = p.getWorld().getName();
			try
			{
				Connection conn = DriverManager.getConnection(url, user, pass); 
				PreparedStatement sampleQueryStatement = conn.prepareStatement("REPLACE INTO userhomes SET world = '" + world + "', p_name = '" + p.getName() + "', home_x = '" + x + "', home_y = '" + y + "', home_z = '" + z + "'"); 
				sampleQueryStatement.executeUpdate(); 
				sampleQueryStatement.close();
				p.sendMessage("Home Set Successfully");
				
						
					}
				
			
			
			catch
			(SQLException e1) 
			{
				e1.printStackTrace();
        	}
			
			
		}
			if(args.length == 1)
			{
				Player p = (Player) player;
				double x = p.getLocation().getX();
				double y = p.getLocation().getY();
				double z = p.getLocation().getZ();
				String world = p.getWorld().getName();
				try
				{
					Connection conn = DriverManager.getConnection(url, user, pass); 
					PreparedStatement sampleQueryStatement = conn.prepareStatement("UPDATE userhomes SET p_name ='" + p.getName() +"',world"+args[0]+" ="+world+", home"+args[0]+"_x="+x+",home"+args[0]+"_y ="+y+",home"+args[0]+"_z ="+z+" WHERE p_name = '" + p.getName() + "'"); 
					sampleQueryStatement.executeUpdate(); 
					sampleQueryStatement.close();
					p.sendMessage("Home " + args[0] + " Set Successfully");
					
							
						}
					
				
				
				catch
				(SQLException e1) 
				{
					e1.printStackTrace();
	        	}
				
				
			}
				
		}
	}}
		
	
	



