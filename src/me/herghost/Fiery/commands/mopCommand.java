package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.herghost.Fiery.util.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mopCommand implements CommandExecutor 
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("op")&& sender instanceof Player)
		{
			String user = Configuration.getString("settings.mysql.user");
			String pass = Configuration.getString("settings.mysql.pass");
			String url = "jdbc:mysql://localhost:3306/Fiery";
			
			
			String v = Configuration.getString("money.iscalled");
			boolean t = Configuration.getBoolean("money.isenabled");
			int cost = Configuration.getInt("commandcharge.op");
			
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
						
							Command.broadcastCommandMessage(sender, "Opping " + args[0]);
					    	OfflinePlayer players = Bukkit.getOfflinePlayer(args[0]);
				            players.setOp(true);
				            nbalance = balance - cost;
							Statement select11 = conn.createStatement();
							select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
							p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
							
							
					 		           
				            if (p instanceof Player) 
				        	{
				        	       	((Player)p).sendMessage(ChatColor.YELLOW + "Your have been granted Op status!");
				        	       	return true;
				        	}
				            return true;
					}
					
					if(t && cost < 1)
					{
						Command.broadcastCommandMessage(sender, "Opping " + args[0]);
				    	OfflinePlayer players = Bukkit.getOfflinePlayer(args[0]);
			            players.setOp(true);
			            
				 		           
			            if (p instanceof Player) 
			        	{
			        	       	((Player)p).sendMessage(ChatColor.YELLOW + "Your have been granted Op status!");
			        	       	return true;
			        	}
			            return true;
					}
						
					
					if(!t)
					{
						Command.broadcastCommandMessage(sender, "Opping " + args[0]);
				    	OfflinePlayer players = Bukkit.getOfflinePlayer(args[0]);
			            players.setOp(true);
			            
				 		           
			            if (p instanceof Player) 
			        	{
			        	       	((Player)p).sendMessage(ChatColor.YELLOW + "Your have been granted Op status!");
			        	       	return true;
			        	}
			            return true;
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
		