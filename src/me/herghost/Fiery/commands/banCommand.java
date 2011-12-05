package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.herghost.Fiery.util.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class banCommand implements CommandExecutor {


	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		
		
			
			if(cmd.getName().equalsIgnoreCase("ban")&& sender instanceof Player)
			
			if
			(args.length == 0)
			{
				return false;
			}
			
			 
			String user = Configuration.getString("settings.mysql.user");
			String pass = Configuration.getString("settings.mysql.pass");
			String url = "jdbc:mysql://localhost:3306/Fiery";
			
			
			String v = Configuration.getString("money.iscalled");
			boolean t = Configuration.getBoolean("money.isenabled");
			int cost = Configuration.getInt("commandcharge.ban");
			
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
										
					if(t)
					{
						
						if(cost > 0)
							{
								if(cost < balance )
									{
										nbalance = balance - cost;
										Statement select0 = conn.createStatement();
										int rs1 = select0.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
										p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
										Bukkit.getOfflinePlayer(args[0]).setBanned(true);
										if (Bukkit.getPlayer(args[0]) != null) Bukkit.getPlayer(args[0]).kickPlayer("Banned by admin.");
										Command.broadcastCommandMessage(sender, "Banning " + args[0]);
										return true;
							
									}
								if(cost > balance)
									{
										p.sendMessage("Sorry, your balance is to low to execute this command");
										return true;
									}
							}
						
						if(cost < 1)
							{
								Bukkit.getOfflinePlayer(args[0]).setBanned(true);
								if (Bukkit.getPlayer(args[0]) != null) Bukkit.getPlayer(args[0]).kickPlayer("Banned by admin.");
								Command.broadcastCommandMessage(sender, "Banning " + args[0]);
								return true;
							}
								
						
					
					}
					
					else
						{
							Bukkit.getOfflinePlayer(args[0]).setBanned(true);
							if (Bukkit.getPlayer(args[0]) != null) Bukkit.getPlayer(args[0]).kickPlayer("Banned by admin.");
							Command.broadcastCommandMessage(sender, "Banning " + args[0]);
							return true;
						}
					
				}
				
			}
			
			catch
			(SQLException e1) 
			{
				e1.printStackTrace();
			}

			
		return false;
		
	}
}

	
	
	

