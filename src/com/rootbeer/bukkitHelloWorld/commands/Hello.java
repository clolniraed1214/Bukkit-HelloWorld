package com.rootbeer.bukkitHelloWorld.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rootbeer.bukkitHelloWorld.HelloWorld;

public class Hello implements CommandExecutor {
	
	private HelloWorld plugin;
	
	public Hello (HelloWorld hw) {
		plugin = hw;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}

		Player player = (Player) sender;
		player.sendMessage(ChatColor.AQUA + "Hello there, " + player.getName() + "!");
		
		List<String> serverAdmins = plugin.getConfig().getStringList("Server Admins");
		player.sendMessage("The server admins are:");
		for (String admin : serverAdmins) {
			player.sendMessage("    - " + admin);
		}
		
		return true;
	}
}
