package me.herghost.Fiery.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gameCommand implements CommandExecutor {

	@Override
	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("game")&& sender instanceof Player)
        {
        	
        	Player player = Bukkit.getPlayerExact(args[0]);
        	    
        	            if (player != null) 
        	            	{
        	                	int value = -1;
        	                		
        	                	try 
        	                		{
        	                			value = Integer.parseInt(args[1]);
        	                		} 
        	                	catch (NumberFormatException ex) 
        	                		{
        	                		
        	                		}
        	  
        	              GameMode mode = GameMode.getByValue(value);
        	 
        	            if (mode != null) {
        	                   if (mode != player.getGameMode()) {
                            Command.broadcastCommandMessage(sender, "Setting " + player.getName() + " to game mode " + mode.getValue());
        	                    player.setGameMode(mode);
        	                       if (mode != player.getGameMode()) {
        	                            Command.broadcastCommandMessage(sender, "The game mode change for " + player.getName() + " was cancelled!");
        	                        }
        	                   } else {
        	                     sender.sendMessage(player.getName() + " already has game mode " + mode.getValue());
        	                   }
        	              } else {
        	                   sender.sendMessage("There is no game mode with id " + args[1]);
        	               }
        	       } else {
        	                sender.sendMessage("Can't find user " + args[0]);
        	          }
        	    
        	           return true;
        	       }
		return false;
        }
	}


