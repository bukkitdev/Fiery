package me.herghost.Fiery.util;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Logger {
	
	public String prefix = "[" + ChatColor.GREEN + "Fiery" + ChatColor.WHITE + "]";

	public void log(Object message) {
		log(Level.INFO, message);
	}

	public void log(Level level, Object message) {
		Bukkit.getLogger().log(level, "[Fiery] " + message);

	}
	public void debug(Object message) {
		log(Level.parse("DEBUG"), message);
	}

	public void sendError(Player player, Object message) {
		send(player, message);
	}

	public void send(Player player, Object message) {
		player.sendMessage("" + message);
	}
}


