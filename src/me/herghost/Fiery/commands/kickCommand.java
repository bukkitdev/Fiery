package me.herghost.Fiery.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class kickCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("kick")&& sender instanceof Player)
		{
			Player player = Bukkit.getPlayerExact(args[0]);
					    
		          if (player != null) {
		               Command.broadcastCommandMessage(sender, "Kicking " + player.getName() + " for " + args[1]);
		             player.kickPlayer("Kicked by admin");
		           } else {
		               sender.sendMessage("Can't find user " + args[0] + ". No kick.");
		            }
		   
		           return true;
		        }
	
return false;
}
}


