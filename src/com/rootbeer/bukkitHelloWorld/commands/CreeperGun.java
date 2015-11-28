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

public class CreeperGun implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		ItemStack handHeld = player.getItemInHand();
		if (!(handHeld.getType().equals(Material.BOW))) return false;
		
		ItemMeta handHeldMeta = handHeld.getItemMeta();
		handHeldMeta.setDisplayName(ChatColor.AQUA + "Creeper Gun");
		
		handHeld.setItemMeta(handHeldMeta);
		handHeld.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		player.setItemInHand(handHeld);
				
		return false;
	}
	
	public static ShapedRecipe getCreeperGunRecipe() {
		ItemStack creeperGun = new ItemStack(Material.BOW, 1, (short) 384);
		ItemMeta creeperGunMeta = creeperGun.getItemMeta();
		creeperGunMeta.setDisplayName(ChatColor.AQUA + "Creeper Gun");
		creeperGun.setItemMeta(creeperGunMeta);
		creeperGun.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		ShapedRecipe creeperGunRecipe = new ShapedRecipe(creeperGun).shape("ttt", "tbt", "ttt");
		creeperGunRecipe.setIngredient('t', Material.TNT);
		creeperGunRecipe.setIngredient('b', Material.BOW);
		return creeperGunRecipe;
	}

}
