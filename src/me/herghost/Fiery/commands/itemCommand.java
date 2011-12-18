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
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("item")&& sender instanceof Player)
			{
				String user = Configuration.getString("settings.mysql.user");
				String pass = Configuration.getString("settings.mysql.pass");
				String url = "jdbc:mysql://localhost:3306/Fiery";
				
				String v = Configuration.getString("money.iscalled");
				boolean t = Configuration.getBoolean("money.isenabled");
				int cost = Configuration.getInt("commandcharge.item");
				
				int balance;
		     
		    
				Player p = (Player) sender;
				Player player = (Player) sender;
				
				
				try
				{
					Connection conn = DriverManager.getConnection(url, user, pass);
					Statement select = conn.createStatement();
					ResultSet rs = select.executeQuery("SELECT balance FROM money WHERE p_name ='" + p.getName() + "'"); 
					
					while (rs.next()) 
						{
							balance = rs.getInt("balance");
							int nbalance;
							
							if(cost > balance)
							{
								p.sendMessage("Sorry, your balance is to low to execute this command");
								return true;
							}
				if(t && cost > 0 && cost < balance)
					{
						int idOfItems = Material.matchMaterial(args[1]).getId();
						int numberOfItems = Integer.parseInt(args[0]);
						ItemStack ItemToAdd = new ItemStack(idOfItems,numberOfItems);
						PlayerInventory inventory = player.getInventory();
						inventory.addItem(ItemToAdd);
						
						Material itemGivenMat = Material.getMaterial(idOfItems);
			            String itemGiven = itemGivenMat.toString();
			            sender.sendMessage("§cYou have gained §e" + args[0] + "§6 " + itemGiven + "§c.");
			            nbalance = balance - cost;
						Statement select11 = conn.createStatement();
						select11.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
						p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
						return true;
					}
				
				if(t && cost < 1)
				{
					int idOfItems = Material.matchMaterial(args[1]).getId();
					int numberOfItems = Integer.parseInt(args[0]);
					ItemStack ItemToAdd = new ItemStack(idOfItems,numberOfItems);
					PlayerInventory inventory = player.getInventory();
					inventory.addItem(ItemToAdd);
					
					Material itemGivenMat = Material.getMaterial(idOfItems);
		            String itemGiven = itemGivenMat.toString();
		            sender.sendMessage("§cYou have gained §e" + args[0] + "§6 " + itemGiven + "§c.");
		            return true;
				}
				
				if(!t)
				{
					int idOfItems = Material.matchMaterial(args[1]).getId();
					int numberOfItems = Integer.parseInt(args[0]);
					ItemStack ItemToAdd = new ItemStack(idOfItems,numberOfItems);
					PlayerInventory inventory = player.getInventory();
					inventory.addItem(ItemToAdd);
					
					Material itemGivenMat = Material.getMaterial(idOfItems);
		            String itemGiven = itemGivenMat.toString();
		            sender.sendMessage("§cYou have gained §e" + args[0] + "§6 " + itemGiven + "§c.");
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
			
}}
				
	        

					