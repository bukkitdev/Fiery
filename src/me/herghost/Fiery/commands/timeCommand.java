package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.herghost.Fiery.util.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class timeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
	
        if(cmd.getName().equalsIgnoreCase("settime")&& sender instanceof Player)
        {
        	
        	String user = Configuration.getString("settings.mysql.user");
			String pass = Configuration.getString("settings.mysql.pass");
			String url = "jdbc:mysql://localhost:3306/Fiery";
			
			
			String v = Configuration.getString("money.iscalled");
			boolean t = Configuration.getBoolean("money.isenabled");
			int cost = Configuration.getInt("commandcharge.settime");
			
			int balance;
		     
		    
			Player p = (Player) sender;
			
			
			try
			{
				Connection conn = DriverManager.getConnection(url, user, pass);
				Statement select = conn.createStatement();
				ResultSet rs = select.executeQuery("SELECT balance FROM money WHERE p_name ='" + p.getName() + "'"); 
				while (rs.next()) 
				{
					balance = rs.getInt("balance");
					int nbalance;
					
					if(t && cost > balance)
					{
						p.sendMessage("Sorry, your balance is to low to execute this command");
						return true;
					}
		
					
					if(t && cost > 0 && cost < balance)
					{
        	
							if(args[0].equalsIgnoreCase("day"))
							{
								for (World world : Bukkit.getWorlds()) 
									{
										world.setTime(0);
									}
										Command.broadcastCommandMessage(sender, "Its is now day time!");
										
										nbalance = balance - cost;
			    						Statement select11 = conn.createStatement();
			    						select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
			    						p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
										return true;
							}
			
							if(args[0].equalsIgnoreCase("night"))
							{
								for(World world : Bukkit.getWorlds())
									{
										world.setTime(86400);
									}
										Command.broadcastCommandMessage(sender, "Its is now night time!");
										nbalance = balance - cost;
			    						Statement select11 = conn.createStatement();
			    						select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
			    						p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
										return true;
							}
					}
					
					if(t && cost < 1)
					{
						if(args[0].equalsIgnoreCase("day"))
						{
							for (World world : Bukkit.getWorlds()) 
								{
									world.setTime(0);
								}
									Command.broadcastCommandMessage(sender, "Its is now day time!");
									return true;
						}
		
						if(args[0].equalsIgnoreCase("night"))
						{
							for(World world : Bukkit.getWorlds())
								{
									world.setTime(86400);
								}
									Command.broadcastCommandMessage(sender, "Its is now night time!");
									return true;
						}
					}
					
					if(!t)
					{
						if(args[0].equalsIgnoreCase("day"))
						{
							for (World world : Bukkit.getWorlds()) 
								{
									world.setTime(0);
								}
									Command.broadcastCommandMessage(sender, "Its is now day time!");
									return true;
						}
		
						if(args[0].equalsIgnoreCase("night"))
						{
							for(World world : Bukkit.getWorlds())
								{
									world.setTime(86400);
								}
									Command.broadcastCommandMessage(sender, "Its is now night time!");
									return true;
						}
					}
						
					}
			}
			catch
			(SQLException e1) 
			{
				e1.printStackTrace();
			}
			}
	return false;
}
}
