package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.rootbeer.bukkitHelloWorld.HelloWorld;
import com.rootbeer.bukkitHelloWorld.other.DeathRainWeather;

public class AllowRain implements CommandExecutor {
	
	private DeathRainWeather deathRain;
	
	public AllowRain (HelloWorld pl, DeathRainWeather dr) {
		deathRain = dr;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(args.length == 1)) return false;
		
		if (Boolean.getBoolean(args[0])) {
			deathRain.setDoRain(true);
			sender.sendMessage("Death Rain Enabled");
		} else if (Boolean.getBoolean(args[0])) {
			deathRain.setDoRain(false);
			deathRain.setRaining(false);
			sender.sendMessage("Death Rain Disabled");
			deathRain.endRain();
		}
		
		return false;
	}
	
}
