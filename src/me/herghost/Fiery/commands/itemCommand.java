package me.herghost.Fiery.commands;



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
				Player player = (Player) sender;
				int idOfItems = Material.matchMaterial(args[0]).getId();
				int numberOfItems = Integer.parseInt(args[1]);
				ItemStack ItemToAdd = new ItemStack(idOfItems,numberOfItems);
				PlayerInventory inventory = player.getInventory();
				inventory.addItem(ItemToAdd);
				
				Material itemGivenMat = Material.getMaterial(idOfItems);
	             String itemGiven = itemGivenMat.toString();
	             sender.sendMessage("§cYou have gained §e" + args[1] + "§6 " + itemGiven + "§c.");
	        
				return true;
			}
		return false;
	}
}


