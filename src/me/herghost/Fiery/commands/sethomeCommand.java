package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import me.herghost.Fiery.util.Configuration;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class sethomeCommand implements CommandExecutor 
{ 
	Logger log = Logger.getLogger("Minecraft");

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("sethome")&& sender instanceof Player)
		{
			{
				String user = Configuration.getString("settings.mysql.user");
				String pass = Configuration.getString("settings.mysql.pass");
				String url = "jdbc:mysql://localhost:3306/Fiery";
				
				
				String v = Configuration.getString("money.iscalled");
				boolean t = Configuration.getBoolean("money.isenabled");
				int cost = Configuration.getInt("commandcharge.sethome");
				
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
							if(args.length == 0)
								{
									double x = p.getLocation().getX();
									double y = p.getLocation().getY();
									double z = p.getLocation().getZ();
									String world = p.getWorld().getName();
			
									PreparedStatement sampleQueryStatement = conn.prepareStatement("REPLACE INTO userhomes SET world = '" + world + "', p_name = '" + p.getName() + "', home_x = '" + x + "', home_y = '" + y + "', home_z = '" + z + "'"); 
									sampleQueryStatement.executeUpdate(); 
									sampleQueryStatement.close();
									p.sendMessage("Home Set Successfully");
									
									
									nbalance = balance - cost;
									Statement select11 = conn.createStatement();
									select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
									p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
									return true;
			
								}
							if(args.length == 1)
								{
									double x = p.getLocation().getX();
									double y = p.getLocation().getY();
									double z = p.getLocation().getZ();
									String world = p.getWorld().getName();
									
									
										PreparedStatement sampleQueryStatement = conn.prepareStatement("UPDATE userhomes SET p_name ='" + p.getName() +"',world"+args[0]+" ="+world+", home"+args[0]+"_x="+x+",home"+args[0]+"_y ="+y+",home"+args[0]+"_z ="+z+" WHERE p_name = '" + p.getName() + "'"); 
										sampleQueryStatement.executeUpdate(); 
										sampleQueryStatement.close();
										p.sendMessage("Home " + args[0] + " Set Successfully");
										
										nbalance = balance - cost;
										Statement select11 = conn.createStatement();
										select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
										p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
										return true;
								}
						}
							
						if(t && cost < 1)
						{
							if(args.length == 0)
							{
								double x = p.getLocation().getX();
								double y = p.getLocation().getY();
								double z = p.getLocation().getZ();
								String world = p.getWorld().getName();
		
								PreparedStatement sampleQueryStatement = conn.prepareStatement("REPLACE INTO userhomes SET world = '" + world + "', p_name = '" + p.getName() + "', home_x = '" + x + "', home_y = '" + y + "', home_z = '" + z + "'"); 
								sampleQueryStatement.executeUpdate(); 
								sampleQueryStatement.close();
								p.sendMessage("Home Set Successfully");
								return true;
							}
						if(args.length == 1)
							{
								double x = p.getLocation().getX();
								double y = p.getLocation().getY();
								double z = p.getLocation().getZ();
								String world = p.getWorld().getName();
								
								PreparedStatement sampleQueryStatement = conn.prepareStatement("UPDATE userhomes SET p_name ='" + p.getName() +"',world"+args[0]+" ="+world+", home"+args[0]+"_x="+x+",home"+args[0]+"_y ="+y+",home"+args[0]+"_z ="+z+" WHERE p_name = '" + p.getName() + "'"); 
								sampleQueryStatement.executeUpdate(); 
								sampleQueryStatement.close();
								p.sendMessage("Home " + args[0] + " Set Successfully");
								return true;
									
							}
						}
						
						if(!t)
						{
							if(args.length == 0)
							{
								double x = p.getLocation().getX();
								double y = p.getLocation().getY();
								double z = p.getLocation().getZ();
								String world = p.getWorld().getName();
		
								PreparedStatement sampleQueryStatement = conn.prepareStatement("REPLACE INTO userhomes SET world = '" + world + "', p_name = '" + p.getName() + "', home_x = '" + x + "', home_y = '" + y + "', home_z = '" + z + "'"); 
								sampleQueryStatement.executeUpdate(); 
								sampleQueryStatement.close();
								p.sendMessage("Home Set Successfully");
								return true;
							}
						if(args.length == 1)
							{
								double x = p.getLocation().getX();
								double y = p.getLocation().getY();
								double z = p.getLocation().getZ();
								String world = p.getWorld().getName();
								
								PreparedStatement sampleQueryStatement = conn.prepareStatement("UPDATE userhomes SET p_name ='" + p.getName() +"',world"+args[0]+" ="+world+", home"+args[0]+"_x="+x+",home"+args[0]+"_y ="+y+",home"+args[0]+"_z ="+z+" WHERE p_name = '" + p.getName() + "'"); 
								sampleQueryStatement.executeUpdate(); 
								sampleQueryStatement.close();
								p.sendMessage("Home " + args[0] + " Set Successfully");
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
				
		}
		return false;
	}
	
			
	}
		
	
	



