package me.herghost.Fiery;

import java.sql.SQLException;


import me.herghost.Fiery.commands.banCommand;
import me.herghost.Fiery.commands.gameCommand;
import me.herghost.Fiery.commands.giveCommand;
import me.herghost.Fiery.commands.homeCommand;
import me.herghost.Fiery.commands.itemCommand;
import me.herghost.Fiery.commands.kickCommand;
import me.herghost.Fiery.commands.moneyCommand;
import me.herghost.Fiery.commands.mopCommand;
//import me.herghost.Fiery.commands.mreloadCommand;
import me.herghost.Fiery.commands.rmopCommand;
import me.herghost.Fiery.commands.sethomeCommand;
import me.herghost.Fiery.commands.spawnCommand;
import me.herghost.Fiery.commands.unbanCommand;
import me.herghost.Fiery.functions.sqlFunctions;
import me.herghost.Fiery.util.Configuration;
import me.herghost.Fiery.util.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Fiery extends JavaPlugin {
	
	
		
	private static Logger logger;
	
	private final FieryPlayerListener playerListener = new FieryPlayerListener(this);
		
	
	//OnEnable Methods
	public void onEnable(){ 
		
		//Load Config/Strings ETC
		initialize();
		
		//Send Message to Console
		logger.log("Fiery Plugin Enabled - Beta");
		
		
		
		//Create Tables
		try 
			{
			    sqlFunctions method = new sqlFunctions();
			    method.create_table_users();
	        	method.create_table_userhomes();
	        	
	        	method.create_table_money();
	        	logger.log("Fiery Database Tables OK!");
	      	 }
		
		catch
			(SQLException e1) 
				{
			
					logger.log("Something Fucked Up");
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
		this.getCommand("op").setExecutor(new mopCommand());
		this.getCommand("deop").setExecutor(new rmopCommand());
		this.getCommand("gamemode").setExecutor(new gameCommand());
		//this.getCommand("reload").setExecutor(new mreloadCommand());
		this.getCommand("money").setExecutor(new moneyCommand());
	}
		
		
		
		
	
	
	public void onDisable(){ 
		
		logger.log("Fiery Plugin Disabled - Goodbye!");
		 
	}
	
	
	
	
	public static Logger getLogger() {
		return logger;
	}
	
		
	public void initialize() {
		Configuration.initialize();
		registerEvents();
		logger = new Logger();
		
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN,playerListener, Event.Priority.Normal, this);
		}
}

