package me.herghost.Fiery;

import java.sql.SQLException;

import java.util.logging.Logger;

import me.herghost.Fiery.commands.banCommand;
import me.herghost.Fiery.commands.giveCommand;
import me.herghost.Fiery.commands.homeCommand;
import me.herghost.Fiery.commands.itemCommand;
import me.herghost.Fiery.commands.kickCommand;
import me.herghost.Fiery.commands.mopCommand;
import me.herghost.Fiery.commands.rmopCommand;
import me.herghost.Fiery.commands.sethomeCommand;
import me.herghost.Fiery.commands.spawnCommand;
import me.herghost.Fiery.commands.unbanCommand;
import me.herghost.Fiery.functions.sqlFunctions;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Fiery extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");

	//The Listener
	private final FieryPlayerListener playerListener = new FieryPlayerListener(this);
	
	//Config File
	public FileConfiguration config;
	
	//OnEnable Methods
	public void onEnable(){ 
		
		//Initialize config file
		loadConfiguration();
		
	
		
		//Send Message to Console
		log.info("Fiery Plugin Enabled - Beta");
		
		
		
		//Create Tables
		try 
			{
			    sqlFunctions method = new sqlFunctions(this);
			    method.create_table_users();
	        	method.create_table_userhomes();
	        	
	        	log.info("Fiery Database Tables OK!");
	      	 }
		
		catch
			(SQLException e1) 
				{
			
					log.info("Something Fucked Up");
					e1.printStackTrace();
	        	}
			
		//register commands
		this.getCommand("item").setExecutor(new itemCommand());
		this.getCommand("give").setExecutor(new giveCommand());
		this.getCommand("kick").setExecutor(new kickCommand());
		this.getCommand("spawn").setExecutor(new spawnCommand());
		this.getCommand("ban").setExecutor(new banCommand());
		this.getCommand("unban").setExecutor(new unbanCommand());
		this.getCommand("sethome").setExecutor(new sethomeCommand());
		this.getCommand("home").setExecutor(new homeCommand());
		this.getCommand("mop").setExecutor(new mopCommand());
		this.getCommand("rmop").setExecutor(new rmopCommand());
		
		
		//register listeners
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN,playerListener, Event.Priority.Normal, this);
	}
		
		
	
	
	
	



	public void onDisable(){ 
		
		log.info("Fiery Plugin Disabled - Goodbye!");
		 
	}
	
	public void loadConfiguration() 
	{
		config = getConfig();
		getConfig().addDefault("settings.mysql.user", "mysql username");
		getConfig().addDefault("settings.mysql.pass", "mysql password");
		getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
