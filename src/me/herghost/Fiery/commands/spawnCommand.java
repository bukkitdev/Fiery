package me.herghost.Fiery.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawnCommand implements CommandExecutor

{
@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("spawn")&& sender instanceof Player)
        {
            Player player = (Player) sender;
            player.teleport(player.getWorld().getSpawnLocation());
            sender.sendMessage("You have been whisked back to spawn");
            return true;
        }
        return false;
    }

}
   