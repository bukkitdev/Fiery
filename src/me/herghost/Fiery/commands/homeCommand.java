package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import me.herghost.Fiery.util.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class homeCommand implements CommandExecutor {
	
	Logger log = Logger.getLogger("Minecraft");


	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		readCommand((Player) sender, commandLabel, args);
		return true;

	}
	
	private void readCommand(Player player, String cmd, String[] args )
	{
		
		if(cmd.equalsIgnoreCase("home")&& player instanceof Player)
		{
			if(args.length == 0)
			{
			Player p = (Player) player;
			
			try
			{
				String user = Configuration.getString("settings.mysql.user");
				String pass = Configuration.getString("settings.mysql.pass");
				String url = "jdbc:mysql://localhost:3306/Fiery";
				Connection conn = DriverManager.getConnection(url, user, pass); 
				Statement select = conn.createStatement();
				ResultSet result = select.executeQuery("SELECT world,home_x,home_y,home_z FROM userhomes WHERE p_name = '" + p.getName() + "' LIMIT 1");
				
				while(result.next())
				{
					String w = result.getString(1);
					World world = Bukkit.getServer().getWorld(w);
					double x = result.getDouble(2);
					double y = result.getDouble(3);
					double z = result.getDouble(4);
					
				player.teleport(new Location(world, x, y, z));
				p.sendMessage("Welcome Home!");
									
				}
				
						
			}
			 catch( Exception e ) {
			      e.printStackTrace();
			}
		}
			
			if(args.length == 1)
			{
				Player p = (Player) player;
				
				try
				{
					String user = Configuration.getString("settings.mysql.user");
					String pass = Configuration.getString("settings.mysql.pass");
					String url = "jdbc:mysql://localhost:3306/Fiery";
					Connection conn = DriverManager.getConnection(url, user, pass); 
					Statement select = conn.createStatement();
					ResultSet result = select.executeQuery("SELECT world"+args[0]+"," +
							"home"+args[0]+"_x," +
									"home"+args[0]+"_y," +
											"home"+args[0]+"_z" +
													" FROM userhomes " +
													"WHERE p_name = '" + p.getName() + "' LIMIT 1");
					
					while(result.next())
					{
						double x = result.getDouble(2);
						double y = result.getDouble(3);
						double z = result.getDouble(4);
						String w = result.getString(1);
						World world = Bukkit.getServer().getWorld(w);
						log.info(""+args[0]+"");
						player.teleport(new Location(world, x, y, z));
						p.sendMessage("Welcome Home!");
										
					}
					
							
				}
				 catch( Exception e ) {
				      e.printStackTrace();
				}
			}
	}
	}
	

	}
