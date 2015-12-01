package com.rootbeer.bukkitHelloWorld.lib;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDetails {
	
	public static final boolean failsPlayerCheck (CommandSender sender) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be an in-game player to use this command!");
			return true;
		}
		return false;		
	}
	
}
