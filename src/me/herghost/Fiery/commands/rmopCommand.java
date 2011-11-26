package me.herghost.Fiery.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rmopCommand implements CommandExecutor {

public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	
	{
		if(cmd.getName().equalsIgnoreCase("rmop")&& sender instanceof Player)
		{
		           
           Command.broadcastCommandMessage(sender, "de-opping " + args[0]);
		    
	           OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
	            player.setOp(false);
	if (player instanceof Player) 
	{
	       ((Player)player).sendMessage(ChatColor.YELLOW + "Your Op Status has been removed");
	}
	            	          
	   
		            return true;
	}
		return false;

}
}