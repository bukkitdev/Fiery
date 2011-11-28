package me.herghost.Fiery.util;

import me.herghost.Fiery.Fiery;


public class Configuration
{
	@SuppressWarnings("unused")
	private static Fiery plugin;
	private static ConfigurationHandler config = new ConfigurationHandler("plugins/Fiery/config.yml", "Fiery Configuration");
	
	public static void initialize()
	{
		config.load();
		addDefaults();
		config.copyDefaults(true);
		config.save();
	}
	
	public static void addDefaults() {

		config.addDefault("settings.mysql.user", "username");
		config.addDefault("settings.mysql.pass", "password");
	}
	
	public static String getUser()
	{
		String user = config.getString("settings.mysql.user");
		return user;
	}
	
	public static String getPass()
	{
		String pass = config.getString("settings.mysql.pass");
		return pass;
	}
	
	

	public static String getString(String path) {
		String str = config.getString(path);
		return str;
	}
}