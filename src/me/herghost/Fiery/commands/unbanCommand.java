package me.herghost.Fiery.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class unbanCommand implements CommandExecutor 
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("unban"))
		{
			Bukkit.getOfflinePlayer(args[0]).setBanned(false);
			Command.broadcastCommandMessage(sender, "Pardoning " + args[0]);
			return true;
		}
	
		return false;
	}
}
