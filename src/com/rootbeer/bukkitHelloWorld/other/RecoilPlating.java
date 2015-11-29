package com.rootbeer.bukkitHelloWorld.other;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class RecoilPlating {
	
	public static final ItemStack getPlatingCore() {
		ItemStack recPlateCore = new ItemStack(Material.SEA_LANTERN);

		ItemMeta recPlateMeta = recPlateCore.getItemMeta();
		recPlateMeta.setDisplayName(ChatColor.GOLD + "Arrow Explosion Core");

		recPlateCore.setItemMeta(recPlateMeta);
		recPlateCore.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 2);

		return recPlateCore;
	}
	
	public static final ItemStack getRecoilPlating() {
		ItemStack recPlate = new ItemStack(Material.DIAMOND_CHESTPLATE);

		ItemMeta recPlateMeta = recPlate.getItemMeta();
		recPlateMeta.setDisplayName(ChatColor.GOLD + "Recoil Plating");

		recPlate.setItemMeta(recPlateMeta);
		recPlate.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 2);

		return recPlate;
	}

	public static final ShapedRecipe getPlatingBookRecipe() {
		ShapedRecipe plateCoreRecipe = new ShapedRecipe(getPlatingCore());
		plateCoreRecipe.shape("TDT", "BAB", "TDT");
		plateCoreRecipe.setIngredient('T', Material.TNT);
		plateCoreRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
		plateCoreRecipe.setIngredient('B', Material.BEACON);
		plateCoreRecipe.setIngredient('A', Material.ARROW);

		return plateCoreRecipe;
	}

	public static final ShapedRecipe getPlatingRecipe() {
		ShapedRecipe plateRecipe = new ShapedRecipe(getRecoilPlating());
		plateRecipe.shape("O O", "DCD", "OEO");
		plateRecipe.setIngredient('O', Material.OBSIDIAN);
		plateRecipe.setIngredient('D', Material.DISPENSER);
		plateRecipe.setIngredient('C', Material.DIAMOND_CHESTPLATE);
		plateRecipe.setIngredient('E', Material.SEA_LANTERN);
		
		return plateRecipe;
	}
}
