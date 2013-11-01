package ca.enderlance.banplugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BanPlugin extends JavaPlugin
{
	public void ban(OfflinePlayer tplayer)
	{
		if(!(tplayer.isBanned()))
		{
			tplayer.setBanned(true);
		}
		else if(tplayer.isBanned())
		{
			tplayer.setBanned(false);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ban"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				if(player.hasPermission("ban.ban"))
				{
					if(args.length == 2)
					{
						OfflinePlayer toplayer = sender.getServer().getOfflinePlayer(args[0]);
						if(toplayer.isBanned())
						{
							ban(toplayer);
							player.sendMessage(ChatColor.GOLD + "You have pardoned " + toplayer.getName() + " for " + args[1]);
							logToFile(sender.getName() + "	has changed the ban status of	" + toplayer.getName() + "	to	" + toplayer.isBanned() + "	for	" + args[1]);
						}
						else if(!(toplayer.isBanned()))
						{
							ban(toplayer);
							player.sendMessage(ChatColor.GOLD + "You have banned " + toplayer.getName() + " for " + args[1]);
							logToFile(sender.getName() + "	has changed the ban status of	" + toplayer.getName() + "	to	" + toplayer.isBanned() + "	for	" + args[1]);
						}
					}
					else if(args.length < 2)
					{
						player.sendMessage(ChatColor.RED + "Usage: /ban <player> <reason> -- missing arguments.");
					}
					else if(args.length > 2)
					{
						player.sendMessage(ChatColor.RED + "Usage: /ban <player> <reason> -- argument surplus.");
					}
				}
			}else
			{
				if(args.length == 2)
				{
					OfflinePlayer toplayer = sender.getServer().getOfflinePlayer(args[0]);
					ban(toplayer);
					logToFile(sender.getName() + "		has changed the ban status of	" + toplayer.getName() + "	to	" + toplayer.isBanned() + "	for	" + args[1]);
					sender.sendMessage("Player " + toplayer.getName() + "'s banned status is now " + toplayer.isBanned() + ".");
				}
				else if(args.length < 2)
				{
					sender.sendMessage("Usage: /ban <player> <reason> -- missing arguments.");
				}
				else if(args.length > 2)
				{
					sender.sendMessage("Usage: /ban <player> <reason> -- argument surplus.");
				}
			}
		}
		return true;
	}

	//Method below from https://forums.bukkit.org/threads/making-a-log-file-for-your-plugins.85430/

	public void logToFile(String message)
	{
        	try
        	{
        		File dataFolder = getDataFolder();
        		if(!dataFolder.exists())
        		{
        			dataFolder.mkdir();
        		}
        		File saveTo = new File(getDataFolder(), "banLog.txt");
        		if (!saveTo.exists())
        		{
        			saveTo.createNewFile();
        		}

        		FileWriter fw = new FileWriter(saveTo, true);

		        PrintWriter pw = new PrintWriter(fw);

        		pw.println(message);

			pw.flush();
			pw.close();

	        } catch (IOException e)
        	{

	        	e.printStackTrace();

	        }
	}
}
