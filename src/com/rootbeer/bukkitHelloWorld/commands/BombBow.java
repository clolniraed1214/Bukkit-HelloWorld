package com.rootbeer.bukkitHelloWorld.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BombBow implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		if (args.length < 1) {
			sender.sendMessage("Not enough arguments!");
			return false;
		}
		Player player = (Player) sender;
		ItemStack handHeld = player.getItemInHand();
		
		if (!(handHeld.getType().equals(Material.BOW))) return false;
		ItemMeta handHeldMeta = handHeld.getItemMeta();
		
		if (!(handHeldMeta.hasDisplayName())) return false;		
		if (!(handHeldMeta.getDisplayName().toString().equalsIgnoreCase(ChatColor.AQUA + "Bomb Bow"))) return false;
		
		if (args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("false")) {
			if (Boolean.parseBoolean(args[0])) {
				handHeldMeta.setLore(Arrays.asList("Variance"));
				player.sendMessage("Bomb Bow changed to variance mode!");
			} else {
				handHeldMeta.setLore(Arrays.asList("No Variance"));
				player.sendMessage("Bomb Bow changed to no variance mode!");
			}
		} else {
			player.sendMessage("Invalid Parameter");
		}
		
		handHeld.setItemMeta(handHeldMeta);
		player.setItemInHand(handHeld);
		
		return true;
	}

}
