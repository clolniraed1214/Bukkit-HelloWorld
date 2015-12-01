package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rootbeer.bukkitHelloWorld.other.RecoilPlating;

public class GetRecoilPlating implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		
		Player player = (Player) sender;
		if (!(player.getItemInHand().getType().equals(Material.AIR))) return false;
		
		player.setItemInHand(RecoilPlating.getRecoilPlating());
		
		return true;
	}

}
