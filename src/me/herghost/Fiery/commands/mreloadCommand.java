package me.herghost.Fiery.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mreloadCommand implements CommandExecutor {

	@Override
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	
	{
		if(cmd.getName().equalsIgnoreCase("reload")&& sender instanceof Player)
		{
			 Bukkit.reload();
			 sender.sendMessage(ChatColor.GREEN + "Reload complete.");
		}
		return true;
	}

}
