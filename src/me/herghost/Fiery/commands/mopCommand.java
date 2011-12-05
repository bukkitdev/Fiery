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

	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	
	{
		if(cmd.getName().equalsIgnoreCase("op")&& sender instanceof Player)
		{
		           
           Command.broadcastCommandMessage(sender, "Opping " + args[0]);
		    
	           OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
	            player.setOp(true);
		 		           
	            if (player instanceof Player) 
	        	{
	        	       ((Player)player).sendMessage(ChatColor.YELLOW + "Your have been granted Op status!");
	        	}
	            
	            String user = Configuration.getString("settings.mysql.user");
				String pass = Configuration.getString("settings.mysql.pass");
				String url = "jdbc:mysql://localhost:3306/Fiery";
				
				
				String v = Configuration.getString("money.iscalled");
				boolean t = Configuration.getBoolean("money.isenabled");
				int cost = Configuration.getInt("commandcharge.game");
				
				int balance;
			     
			    
				
				
				try
				{
					Connection conn = DriverManager.getConnection(url, user, pass);
					
					Statement select = conn.createStatement();
					ResultSet rs = select.executeQuery("SELECT balance FROM money WHERE p_name ='" + player.getName() + "'"); 
					while (rs.next()) 
					{
						balance = rs.getInt("balance");
											
						if(t = true && cost > 0)
						{
							int nbalance;
							if(cost < balance )
							{
								nbalance = balance - cost;
								Statement select0 = conn.createStatement();
								int rs1 = select0.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + player.getName() + "'"); 
								((CommandSender) player).sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
								
							}
							else
							{
								((CommandSender) player).sendMessage("Sorry, your balance is to low to execute this command");
							}
						
						}
						
					}
					select.close();
					return true;
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