package me.herghost.Fiery.commands;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.herghost.Fiery.util.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class giveCommand implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("give")&& sender instanceof Player)
		{
			Player player = Bukkit.getPlayerExact(args[0]);
			Player p = (Player) sender;
			String user = Configuration.getString("settings.mysql.user");
			String pass = Configuration.getString("settings.mysql.pass");
			String url = "jdbc:mysql://localhost:3306/Fiery";
			String v = Configuration.getString("money.iscalled");
			boolean t = Configuration.getBoolean("money.isenabled");
			int cost = Configuration.getInt("commandcharge.give");
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
										Material material = Material.matchMaterial(args[1]);
										
										if (material != null) 
											{
												int amount = 1;
												if (args.length >= 3) 
								
											{
													try
														{
															amount = Integer.parseInt(args[2]);
														}
									catch (NumberFormatException ex) {}
									if (amount < 1) amount = 1;
									if (amount > 64) amount = 64;
								}
							
							player.getInventory().addItem(new ItemStack(material, amount));
							Command.broadcastCommandMessage(sender, "Giving  " + player.getName() +"  " + args[2] + "  " + material);
							Command.broadcastCommandMessage(player,  sender.getName() +"  gave you  " + args[2] + "  " + material);
							nbalance = balance - cost;
							Statement select0 = conn.createStatement();
							select0.executeUpdate("UPDATE money SET balance = '" + nbalance + "'WHERE p_name ='" + p.getName() + "'"); 
							p.sendMessage("You have been charged " + cost + " " + v + " - your new balance is " + nbalance + " " + v + "");
							return true;
							
						}
					else 
						
						{
							sender.sendMessage("There's no item called " + args[1]);
							return true;
						}
										
		
	}
			else
			{
				sender.sendMessage("Can't find user " + args[0]);
			}
								return true;			}
					
					
				
if (cost < 1)
					{
						if (player != null)
						{
							Material material = Material.matchMaterial(args[1]);
							
							if (material != null) 
								{
									int amount = 1;
									if (args.length >= 3) 
					
								{
										try
											{
												amount = Integer.parseInt(args[2]);
											}
						catch (NumberFormatException ex) {}
						if (amount < 1) amount = 1;
						if (amount > 64) amount = 64;
					}
				
				player.getInventory().addItem(new ItemStack(material, amount));
				Command.broadcastCommandMessage(sender, "Giving  " + player.getName() +"  " + args[2] + "  " + material);
				Command.broadcastCommandMessage(player,  sender.getName() +"  gave you  " + args[2] + "  " + material);
						
			}
		else 
			
			{
				sender.sendMessage("There's no item called " + args[1]);
			}
							return true;

}
else
{
	sender.sendMessage("Can't find user " + args[0]);
}
				}
else
{
	if (player != null)
	{
		Material material = Material.matchMaterial(args[1]);
		
		if (material != null) 
			{
				int amount = 1;
				if (args.length >= 3) 

			{
					try
						{
							amount = Integer.parseInt(args[2]);
						}
	catch (NumberFormatException ex) {}
	if (amount < 1) amount = 1;
	if (amount > 64) amount = 64;
}

player.getInventory().addItem(new ItemStack(material, amount));
Command.broadcastCommandMessage(sender, "Giving  " + player.getName() +"  " + args[2] + "  " + material);
Command.broadcastCommandMessage(player,  sender.getName() +"  gave you  " + args[2] + "  " + material);
	
}
else 

{
sender.sendMessage("There's no item called " + args[1]);
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
					
					catch
					(SQLException e1) 
					{
						e1.printStackTrace();
					}

					}
				return false;
				
			}
		}



				
			