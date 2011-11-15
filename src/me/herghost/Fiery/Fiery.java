package me.herghost.Fiery;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Logger;

import me.herghost.Fiery.commands.banCommand;
import me.herghost.Fiery.commands.giveCommand;
import me.herghost.Fiery.commands.itemCommand;
import me.herghost.Fiery.commands.kickCommand;
import me.herghost.Fiery.commands.spawnCommand;
import me.herghost.Fiery.commands.unbanCommand;

import me.herghost.Fiery.functions.sqlFunctions;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Fiery extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	
	
	
	
	
	
	
	
	 
	
	public void onEnable(){ 
		log.info("Fiery Plugin Enabled");
		
		@SuppressWarnings("unused")
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
		
		
		
		try 
			{
			    sqlFunctions method = new sqlFunctions();
	        	method.create_tables(); 
			} 
		
		catch
			(SQLException e) 
				{
	        		e.printStackTrace();
	        	}
		
				
		this.getCommand("item").setExecutor(new itemCommand());
		this.getCommand("give").setExecutor(new giveCommand());
		this.getCommand("kick").setExecutor(new kickCommand());
		this.getCommand("spawn").setExecutor(new spawnCommand());
		this.getCommand("ban").setExecutor(new banCommand());
		this.getCommand("unban").setExecutor(new unbanCommand());
		
		
		
		
	}
	
	public void onDisable(){ 
		
		log.info("Fiery Plugin Disabled - Goodbye!");
		 
	}
	
	

}
