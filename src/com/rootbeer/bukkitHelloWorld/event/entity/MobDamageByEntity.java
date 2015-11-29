package com.rootbeer.bukkitHelloWorld.event.entity;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.rootbeer.bukkitHelloWorld.HelloWorld;
import com.rootbeer.bukkitHelloWorld.commands.ArrowExplode;

public class MobDamageByEntity implements Listener {
	
	private HelloWorld plugin;
	public MobDamageByEntity (HelloWorld pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void EntityDamageByEntity (EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		ItemStack playerChest = player.getInventory().getChestplate();
		if (playerChest.hasItemMeta()) {
			if (playerChest.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Recoil Plating")) {
				if (event.getDamager() instanceof Arrow) {
					Arrow arrow = (Arrow) event.getDamager();
					if (!arrow.hasMetadata("deleteOnHit")) {
						ArrowExplode.arrowExplode(player.getLocation(), false, plugin, true, player.getDisplayName());
					}
					
					if (arrow.hasMetadata("owner")) {
						if (arrow.getMetadata("owner").get(0).equals(player.getDisplayName())) {
							event.setCancelled(true);
						}
					}
				} else {
					ArrowExplode.arrowExplode(player.getLocation(), false, plugin, true, player.getDisplayName());
				}
			}
		}
	}
	
}
