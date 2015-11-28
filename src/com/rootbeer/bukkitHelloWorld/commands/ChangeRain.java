package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.rootbeer.bukkitHelloWorld.other.DeathRainWeather;

public class ChangeRain implements CommandExecutor {

	private DeathRainWeather deathRain;
	public ChangeRain (DeathRainWeather dr) {
		deathRain = dr;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			int change = Integer.parseInt(args[0]);
			
			int loop = 0;
			if (Math.abs(change) == change) {
				while (loop < change) {
					deathRain.heavyRain();
					loop++;
				}
			} else {
				while (loop > change) {
					deathRain.lightenRain();
					loop--;
				}
			}
		}
		
		return false;
	}

}
