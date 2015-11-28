package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class TNTGun implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		ItemStack handHeld = player.getItemInHand();
		if (!(handHeld.getType().equals(Material.BOW))) return false;
		
		ItemMeta handHeldMeta = handHeld.getItemMeta();
		handHeldMeta.setDisplayName(ChatColor.AQUA + "TNT Gun");
		
		handHeld.setItemMeta(handHeldMeta);
		handHeld.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		player.setItemInHand(handHeld);
				
		return false;
	}
	
	public static ShapedRecipe getTNTGunRecipe() {
		ItemStack tntGun = new ItemStack(Material.BOW, 1, (short) 384);
		ItemMeta tntGunMeta = tntGun.getItemMeta();
		tntGunMeta.setDisplayName(ChatColor.AQUA + "TNT Gun");
		tntGun.setItemMeta(tntGunMeta);
		tntGun.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ShapedRecipe tntGunRecipe = new ShapedRecipe(tntGun).shape(" t ", "tbt", " t ");
		tntGunRecipe.setIngredient('t', Material.TNT);
		tntGunRecipe.setIngredient('b', Material.BOW);
		return tntGunRecipe;
	}
}
