package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Refill implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		
		Player player = (Player) sender;
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setSaturation(32.0f);
		player.sendMessage(ChatColor.BLUE + "You have been refilled!");
		
		return true;
	}

}
