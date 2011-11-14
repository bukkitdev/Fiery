package me.herghost.Fiery.commands;


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
		return false;
				
			}
			
}
