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
	
	public static void addDefaults() 
	{
		config.addDefault("settings.mysql.user", "username");
		config.addDefault("settings.mysql.pass", "password");
		
		config.addDefault("money.isenabled", true);
		config.addDefault("money.iscalled", "name_your_currency");
		config.addDefault("money.startingbalance", 50);
		
		config.addDefault("commandcharge.ban", 0);
		config.addDefault("commandcharge.gamemode", 0);
		config.addDefault("commandcharge.give", 0);
		config.addDefault("commandcharge.home", 0);
		config.addDefault("commandcharge.item", 0);
		config.addDefault("commandcharge.kick", 0);
		config.addDefault("commandcharge.op", 0);
	}
	
	//get mysql user
	public static String getUser()
	{
		String user = config.getString("settings.mysql.user");
		return user;
	}
	
	//get mysql pass
	public static String getPass()
	{
		String pass = config.getString("settings.mysql.pass");
		return pass;
	}
	
	//Money
	//is money enabled
	public static boolean getMoneyIsEnabled()
	{
		boolean isEnabled = config.getBoolean("money.isenabled");
		return isEnabled;
	}
	
	//Money Called
	public static String getisCalled()
	{
		String isCalled = config.getString("money.iscalled");
		return isCalled;
	}
	
	//Starting amount	
	public static int getMoneyStarting(int id, String name)
	{
		int startingbalance = config.getInt("money.startingbalance");
		return startingbalance;
	}
	
	
	//Command Charges
	public static int chargeBan(int id, String name)
	{
		int chargeBan = config.getInt("commandcharge.ban");
		return chargeBan;
	}
	
	public static int chargeGame(int id, String name)
	{
		int chargeGame = config.getInt("commandcharge.gamemode");
		return chargeGame;
	}
	
	public static int chargeGive(int id, String name)
	{
		int chargeGive = config.getInt("commandcharge.give");
		return chargeGive;
	}
	
	public static int chargeHome(int id, String name)
	{
		int chargeHome = config.getInt("commandcharge.home");
		return chargeHome;
	}
	
	public static int chargeItem(int id, String name)
	{
		int chargeItem = config.getInt("commandcharge.item");
		return chargeItem;
	}
	
	public static int chargeKick(int id, String name)
	{
		int chargeKick = config.getInt("commandcharge.kick");
		return chargeKick;
	}
	public static int chargeOp(int id, String name)
	{
		int chargeOp = config.getInt("commandcharge.op");
		return chargeOp;
	}
	
	
	
	
	//settings
	public static String getString(String path) {
		String str = config.getString(path);
		return str;
	}
	
	
	
	public static Boolean getBoolean(String path)
	{
		Boolean boo = config.getBoolean(path);
		return boo;
	}

	public static int getInt(String path) {
		int intt = config.getInt(path);
		return intt;
	}
	
}
