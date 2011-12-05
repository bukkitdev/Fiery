package me.herghost.Fiery.commands;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.herghost.Fiery.util.Configuration;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class itemCommand implements CommandExecutor {
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("item")&& sender instanceof Player)
			{
				Player player = (Player) sender;
				int idOfItems = Material.matchMaterial(args[0]).getId();
				int numberOfItems = Integer.parseInt(args[1]);
				ItemStack ItemToAdd = new ItemStack(idOfItems,numberOfItems);
				PlayerInventory inventory = player.getInventory();
				inventory.addItem(ItemToAdd);
				
				Material itemGivenMat = Material.getMaterial(idOfItems);
	             String itemGiven = itemGivenMat.toString();
	             sender.sendMessage("§cYou have gained §e" + args[1] + "§6 " + itemGiven + "§c.");
	        

					String user = Configuration.getString("settings.mysql.user");
					String pass = Configuration.getString("settings.mysql.pass");
					String url = "jdbc:mysql://localhost:3306/Fiery";
					
					
					String v = Configuration.getString("money.iscalled");
					boolean t = Configuration.getBoolean("money.isenabled");
					int cost = Configuration.getInt("commandcharge.item");
					
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
									player.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
									
								}
								else
								{
									player.sendMessage("Sorry, your balance is to low to execute this command");
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
