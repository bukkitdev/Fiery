package me.herghost.Fiery.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import me.herghost.Fiery.util.Configuration;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class moneyCommand implements CommandExecutor 
{



	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		readCommand((Player) sender, commandLabel, args);
		return true;
	}
	
	
	private void readCommand(Player player, String cmd, String[] args )
	{
		
		
		
		if(cmd.equalsIgnoreCase("money")&& player instanceof Player)
		{
			
				
				Player p = (Player) player;
				
			
				try
					{
						String user = Configuration.getString("settings.mysql.user");
						String pass = Configuration.getString("settings.mysql.pass");
						String url = "jdbc:mysql://localhost:3306/Fiery";
						
						
						int balance;
						String v = Configuration.getString("money.iscalled");
						Connection conn = DriverManager.getConnection(url, user, pass);
						
						Statement select = conn.createStatement();
						ResultSet rs = select.executeQuery("SELECT balance FROM money WHERE p_name ='" + p.getName() + "'"); 
						while (rs.next()) 
						{
							balance = rs.getInt("balance");
							p.sendMessage("Your current balance is " + balance + " " +  v + "");
						}
						
						
						
						
					}
						
						
						
					
				catch
				(SQLException e1) 
					{
						e1.printStackTrace();
					}
			
			
		}
	}
}
			
	
		
	