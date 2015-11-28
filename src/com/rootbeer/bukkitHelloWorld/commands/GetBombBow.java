package com.rootbeer.bukkitHelloWorld.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetBombBow implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		ItemStack handHeld = player.getItemInHand();
		if (!(handHeld.getType().equals(Material.BOW))) return false;
		
		ItemMeta handHeldMeta = handHeld.getItemMeta();
		handHeldMeta.setDisplayName(ChatColor.AQUA + "Bomb Bow");
		handHeldMeta.setLore(Arrays.asList("No Variance"));
		
		handHeld.setItemMeta(handHeldMeta);
		handHeld.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
	
		player.setItemInHand(handHeld);
				
		return false;
	}

}
