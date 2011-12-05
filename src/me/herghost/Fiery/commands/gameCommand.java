package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.herghost.Fiery.util.Configuration;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gameCommand implements CommandExecutor 
{

public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("gamemode")&& sender instanceof Player)
			{
						Player player = Bukkit.getPlayerExact(args[0]);
						Player p = (Player) sender;
						String user = Configuration.getString("settings.mysql.user");
						String pass = Configuration.getString("settings.mysql.pass");
						String url = "jdbc:mysql://localhost:3306/Fiery";
						String v = Configuration.getString("money.iscalled");
						boolean t = Configuration.getBoolean("money.isenabled");
						int cost = Configuration.getInt("commandcharge.gamemode");
						int balance;
			
						
			try
			{
				Connection conn = DriverManager.getConnection(url, user, pass);
				Statement select = conn.createStatement();
				ResultSet rs = select.executeQuery("SELECT balance FROM money WHERE p_name ='" + p.getName() + "'"); 
				while (rs.next()) 
				{
					balance = rs.getInt("balance");
					int nbalance;
													
if(t && cost > 0 && cost < balance)
{
	if (player != null) 
	{
		int value = -1;
							   
			try {
					value = Integer.parseInt(args[1]);
				} 
			catch (NumberFormatException ex) {}
							    
GameMode mode = GameMode.getByValue(value);
							    
	if (mode != null) 
		{
			if (mode != player.getGameMode()) 
			{
				nbalance = balance - cost;
				Statement select0 = conn.createStatement();
				select0.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
				p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
				Command.broadcastCommandMessage(sender, "Setting " + player.getName() + " to game mode " + mode.getValue());
				player.setGameMode(mode);
			
			if (mode != player.getGameMode()) 
			{
				Command.broadcastCommandMessage(sender, "The game mode change for " + player.getName() + " was cancelled!");
			}
			} 
			
			else 
			{
				sender.sendMessage(player.getName() + " already has game mode " + mode.getValue());
			}
		}
	
	else 
		{
			sender.sendMessage("There is no game mode with id " + args[1]);
		}
	} 
						
	
	
	else 
	{
		sender.sendMessage("Can't find user " + args[0]);
	}
							   
	return true;
							        
}

if (cost < 1)
{
	if (player != null) 

{
	int value = -1;
						   
		try {
				value = Integer.parseInt(args[1]);
			} 
		catch (NumberFormatException ex) {}
						    
GameMode mode = GameMode.getByValue(value);
						    
if (mode != null) 
	{
		if (mode != player.getGameMode()) 
		{
			
			Command.broadcastCommandMessage(sender, "Setting " + player.getName() + " to game mode " + mode.getValue());
			player.setGameMode(mode);
		
		if (mode != player.getGameMode()) 
		{
			Command.broadcastCommandMessage(sender, "The game mode change for " + player.getName() + " was cancelled!");
		}
		} 
		
		else 
		{
			sender.sendMessage(player.getName() + " already has game mode " + mode.getValue());
		}
	}

else 
	{
		sender.sendMessage("There is no game mode with id " + args[1]);
	}
} 
					


else 
{
	sender.sendMessage("Can't find user " + args[0]);
}
						   
return true;
						        
}

else
{
	{
		if (player != null) 

	{
		int value = -1;
							   
			try {
					value = Integer.parseInt(args[1]);
				} 
			catch (NumberFormatException ex) {}
							    
	GameMode mode = GameMode.getByValue(value);
							    
	if (mode != null) 
		{
			if (mode != player.getGameMode()) 
			{
				
				Command.broadcastCommandMessage(sender, "Setting " + player.getName() + " to game mode " + mode.getValue());
				player.setGameMode(mode);
			
			if (mode != player.getGameMode()) 
			{
				Command.broadcastCommandMessage(sender, "The game mode change for " + player.getName() + " was cancelled!");
			}
			} 
			
			else 
			{
				sender.sendMessage(player.getName() + " already has game mode " + mode.getValue());
			}
		}

	else 
		{
			sender.sendMessage("There is no game mode with id " + args[1]);
		}
	} 
						


	else 
	{
		sender.sendMessage("Can't find user " + args[0]);
	}
							   
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






	