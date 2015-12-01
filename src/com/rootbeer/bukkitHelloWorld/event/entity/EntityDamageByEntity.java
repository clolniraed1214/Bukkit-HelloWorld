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

//===============================//
// SERIOUS REFACTORING NECESSARY //
//===============================//

public class EntityDamageByEntity implements Listener {

	private HelloWorld plugin;

	public EntityDamageByEntity(HelloWorld pl) {
		plugin = pl;
	}

	@EventHandler
	public void EntityHurtByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ItemStack playerChest = player.getInventory().getChestplate();
			if (playerChest.hasItemMeta()) {
				if (playerChest.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Recoil Plating")) {
					if (event.getDamager() instanceof Arrow) {
						Arrow arrow = (Arrow) event.getDamager();
						if (!arrow.hasMetadata("deleteOnHit")) {
							ArrowExplode.arrowExplode(player.getLocation(), false, plugin, true,
									player.getDisplayName(), true);
						}
					} else {
						ArrowExplode.arrowExplode(player.getLocation(), false, plugin, true, player.getDisplayName(),
								true);
					}
				}
			}

			if (event.getDamager() instanceof Arrow) {
				Arrow arrow = (Arrow) event.getDamager();
				if (arrow.hasMetadata("owner")) {
					if (arrow.getMetadata("owner").get(0).asString().equalsIgnoreCase(player.getDisplayName())) {
						event.setCancelled(true);
						event.setDamage(0D);
						return;
					}
				}
			}
		}

		if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if (arrow.hasMetadata("highDamage")) {
				event.setDamage(20D);
			}
		}
	}

}
