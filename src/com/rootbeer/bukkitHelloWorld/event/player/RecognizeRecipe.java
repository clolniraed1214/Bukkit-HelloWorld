package com.rootbeer.bukkitHelloWorld.event.player;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.rootbeer.bukkitHelloWorld.other.RecoilPlating;

public class RecognizeRecipe implements Listener {

	@EventHandler
	public void craft(PrepareItemCraftEvent event) {
		if (!(recipeWorks(RecoilPlating.getPlatingRecipe(), event.getInventory().getMatrix())))
			return;
		
		ItemStack slot8Item = event.getInventory().getItem(8);
		if (slot8Item.hasItemMeta()) {
			if (slot8Item.getItemMeta().hasDisplayName()) {
				if (slot8Item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Arrow Explosion Core")) 
				return;
			}
		}

		event.getInventory().setResult(new ItemStack(Material.AIR));
	}

	public boolean recipeWorks(ShapedRecipe recipe, ItemStack[] matrix) {
		Map<Character, ItemStack> keyMap = recipe.getIngredientMap();

		int index = 0;
		for (String array : recipe.getShape()) {
			for (char key : array.toCharArray()) {
				if (key != ' ') {
					if (!(keyMap.get(key).getType().equals(matrix[index].getType())))
						return false;
					index++;
				} else {
					if (!(matrix[index].getType().equals(Material.AIR)))
						return false;
					index++;
				}
			}
		}

		return true;
	}
}
