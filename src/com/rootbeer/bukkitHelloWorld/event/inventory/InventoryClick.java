package com.rootbeer.bukkitHelloWorld.event.inventory;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) return;
		Inventory inv = event.getInventory();
		if (!inv.getTitle().equals("Custom Inventory")) return;
		
		
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (item.getType() == Material.COMPASS) {
			player.teleport(player.getWorld().getSpawnLocation());
			player.sendMessage("Teleported to Spawn");
			
			player.getWorld().playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 1);
		}
		
		event.setCancelled(true);
		player.closeInventory();
	}
}
