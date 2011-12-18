package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import me.herghost.Fiery.util.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class homeCommand implements CommandExecutor {
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
 {
	Player p = (Player) sender;
	if(cmd.getName().equalsIgnoreCase("home")&& sender instanceof Player)
		{
			
			String user = Configuration.getString("settings.mysql.user");
			String pass = Configuration.getString("settings.mysql.pass");
			String url = "jdbc:mysql://localhost:3306/Fiery";
			String v = Configuration.getString("money.iscalled");
			boolean t = Configuration.getBoolean("money.isenabled");
			int cost = Configuration.getInt("commandcharge.home");
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
					if(t && cost > 0 && cost < balance && args.length == 0)
						{
						    Connection conn1 = DriverManager.getConnection(url, user, pass);
							Statement select1 = conn1.createStatement();
							ResultSet result = select1.executeQuery("SELECT world,home_x,home_y,home_z FROM userhomes WHERE p_name = '" + p.getName() + "' LIMIT 1");
				
							while(result.next())
								{
									String w = result.getString(1);
									World world = Bukkit.getServer().getWorld(w);
									double x = result.getDouble(2);
									double y = result.getDouble(3);
									double z = result.getDouble(4);
					
									p.teleport(new Location(world, x, y, z));
									p.sendMessage("Welcome Home!");
									
									nbalance = balance - cost;
									Statement select11 = conn.createStatement();
									select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
									p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
				
									return true;					
								}
						}
					
					if(t && cost > 0 && cost < balance && args.length == 1)
					{
						Statement select1 = conn.createStatement();
						ResultSet result = select1.executeQuery("SELECT world"+args[0]+"," +
								"home"+args[0]+"_x," +
										"home"+args[0]+"_y," +
												"home"+args[0]+"_z" +
														" FROM userhomes " +
														"WHERE p_name = '" + p.getName() + "' LIMIT 1");
						
						while(result.next())
						{
							double x = result.getDouble(2);
							double y = result.getDouble(3);
							double z = result.getDouble(4);
							String w = result.getString(1);
							World world = Bukkit.getServer().getWorld(w);
							p.teleport(new Location(world, x, y, z));
							p.sendMessage("Welcome Home!");
							
							nbalance = balance - cost;
							Statement select11 = conn.createStatement();
							select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
							p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
							
							return true;
											
						}
						
								
					}
					if(cost > balance)
					{
						p.sendMessage("Sorry, your balance is to low to execute this command");
						return true;
					}
					
					if (t && cost < 1 && args.length == 0)
						{
						    Connection conn1 = DriverManager.getConnection(url, user, pass);
							Statement select1 = conn1.createStatement();
							ResultSet result = select1.executeQuery("SELECT world,home_x,home_y,home_z FROM userhomes WHERE p_name = '" + p.getName() + "' LIMIT 1");
				
							while(result.next())
								{
									String w = result.getString(1);
									World world = Bukkit.getServer().getWorld(w);
									double x = result.getDouble(2);
									double y = result.getDouble(3);
									double z = result.getDouble(4);
					
									p.teleport(new Location(world, x, y, z));
									p.sendMessage("Welcome Home!");
									
									return true;					
								}
						}
					
					if(t && cost < 1 && args.length == 1)
					{
						Statement select1 = conn.createStatement();
						ResultSet result = select1.executeQuery("SELECT world"+args[0]+"," +
								"home"+args[0]+"_x," +
										"home"+args[0]+"_y," +
												"home"+args[0]+"_z" +
														" FROM userhomes " +
														"WHERE p_name = '" + p.getName() + "' LIMIT 1");
						
						while(result.next())
						{
							double x = result.getDouble(2);
							double y = result.getDouble(3);
							double z = result.getDouble(4);
							String w = result.getString(1);
							World world = Bukkit.getServer().getWorld(w);
							p.teleport(new Location(world, x, y, z));
							p.sendMessage("Welcome Home!");
							
							return true;
											
						}
							
				}
					
					if (!t && args.length == 0)
					{
						Connection conn1 = DriverManager.getConnection(url, user, pass);
						Statement select1 = conn1.createStatement();
						ResultSet result = select1.executeQuery("SELECT world,home_x,home_y,home_z FROM userhomes WHERE p_name = '" + p.getName() + "' LIMIT 1");
			
						while(result.next())
							{
								String w = result.getString(1);
								World world = Bukkit.getServer().getWorld(w);
								double x = result.getDouble(2);
								double y = result.getDouble(3);
								double z = result.getDouble(4);
				
								p.teleport(new Location(world, x, y, z));
								p.sendMessage("Welcome Home!");
								
								return true;					
							}
					}
					
					if (!t && args.length == 1)
					{
						Statement select1 = conn.createStatement();
						ResultSet result = select1.executeQuery("SELECT world"+args[0]+"," +
								"home"+args[0]+"_x," +
										"home"+args[0]+"_y," +
												"home"+args[0]+"_z" +
														" FROM userhomes " +
														"WHERE p_name = '" + p.getName() + "' LIMIT 1");
						
						while(result.next())
						{
							double x = result.getDouble(2);
							double y = result.getDouble(3);
							double z = result.getDouble(4);
							String w = result.getString(1);
							World world = Bukkit.getServer().getWorld(w);
							p.teleport(new Location(world, x, y, z));
							p.sendMessage("Welcome Home!");
							
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


		