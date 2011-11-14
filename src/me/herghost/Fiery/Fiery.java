package me.herghost.Fiery;

import java.io.File;
import java.util.logging.Logger;

import me.herghost.Fiery.commands.giveCommand;
import me.herghost.Fiery.commands.itemCommand;
import me.herghost.Fiery.commands.kickCommand;
import me.herghost.Fiery.commands.spawnCommand;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Fiery extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	
	 
	
	public void onEnable(){ 
		log.info("Fiery Plugin Enabled");
		
		FileConfiguration config;
		try
			{
			config = getConfig();
			File Fiery = new File("plugins" + File.separator + "Fiery" + File.separator + "config.yml");
			Fiery.mkdir();
			saveConfig();
			}
		
		catch
			(Exception e1)
				{
					e1.printStackTrace();
				}
		
		
		
		
		this.getCommand("item").setExecutor(new itemCommand());
		this.getCommand("give").setExecutor(new giveCommand());
		this.getCommand("kick").setExecutor(new kickCommand());
		this.getCommand("spawn").setExecutor(new spawnCommand());
		
		
		
		
		
		
	}
	
	public void onDisable(){ 
		
		log.info("Fiery Plugin Disabled");
		 
	}

}
