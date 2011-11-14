package me.herghost.Fiery.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class banCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ban"))
		{
			Bukkit.getOfflinePlayer(args[0]).setBanned(true);
			if (Bukkit.getPlayer(args[0]) != null) Bukkit.getPlayer(args[0]).kickPlayer("Banned by admin.");
		    Command.broadcastCommandMessage(sender, "Banning " + args[0]);
		   
		           return true;
		        }
	
return false;
}
}
