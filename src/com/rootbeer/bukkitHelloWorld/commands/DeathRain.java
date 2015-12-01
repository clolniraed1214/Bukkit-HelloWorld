package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rootbeer.bukkitHelloWorld.HelloWorld;
import com.rootbeer.bukkitHelloWorld.other.DeathRainInstance;

public class DeathRain implements CommandExecutor {

	private final HelloWorld plugin;
	
	public DeathRain (HelloWorld pl) {
		plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		if (args.length < 3) {
			sender.sendMessage("Usage is: /deathrain <radius> <seconds> <freq> [delay]");
			return false;
		}
		
		if (Integer.parseInt(args[2]) > 20) {
			if ((Integer.parseInt(args[2]) % 20) != 0) {
				sender.sendMessage("Sorry, but if the freq is larger than 20,\nit must be a multiple of 20.");
				return false;
			}
		}
		
		if (Integer.parseInt(args[2]) < 20) {
			if ((20 % Integer.parseInt(args[2])) != 0) {
				sender.sendMessage("Sorry, but if the freq is less than 20,\nit must be a factor of 20");
				return false;
			}
		}
		
		Player player = (Player) sender;
		new DeathRainInstance(player, args, plugin);
		player.sendMessage(ChatColor.DARK_PURPLE + "May death rain upon them...");

		return false;
	}
}
