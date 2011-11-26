package me.herghost.Fiery.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mopCommand implements CommandExecutor 
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	
	{
		if(cmd.getName().equalsIgnoreCase("mop")&& sender instanceof Player)
		{
		           
           Command.broadcastCommandMessage(sender, "Opping " + args[0]);
		    
	           OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
	            player.setOp(true);
		 		           
	            if (player instanceof Player) 
	        	{
	        	       ((Player)player).sendMessage(ChatColor.YELLOW + "Your have been granted Op status!");
	        	}
		            return true;
	}
		return false;

}
}
