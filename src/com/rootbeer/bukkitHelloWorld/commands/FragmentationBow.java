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

public class FragmentationBow implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		if (!(player.getItemInHand().getType().equals(Material.BOW))) {
			player.sendMessage("That is not a bow!");
			return false;
		}
		
		ItemStack handHeld = player.getItemInHand();
		ItemMeta handHeldMeta = handHeld.getItemMeta();
		
		handHeldMeta.setDisplayName(ChatColor.BLUE + "Fragmentation Bow");
		handHeld.setItemMeta(handHeldMeta);
		
		handHeld.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		player.setItemInHand(handHeld);
		
		return true;
	}
	
	public static ShapedRecipe getFragBowRecipe() {
		ItemStack fragBow = new ItemStack(Material.BOW);
		fragBow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		ItemMeta fragBowMeta = fragBow.getItemMeta();
		fragBowMeta.setDisplayName(ChatColor.BLUE + "Fragmentation Bow");
		
		fragBow.setItemMeta(fragBowMeta);
		fragBow.setDurability((short) 394);
		
		ShapedRecipe recipe = new ShapedRecipe(fragBow).shape("AAA", "ABA", "AAA");
		recipe.setIngredient('A', Material.ARROW);
		recipe.setIngredient('B', Material.BOW);
		
		return recipe;
	}

}
