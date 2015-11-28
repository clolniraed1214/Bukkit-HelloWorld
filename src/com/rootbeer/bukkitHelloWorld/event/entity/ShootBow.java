package com.rootbeer.bukkitHelloWorld.event.entity;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.rootbeer.bukkitHelloWorld.HelloWorld;

public class ShootBow implements Listener {

	private HelloWorld plugin;

	public ShootBow(HelloWorld hw) {
		plugin = hw;
	}

	@EventHandler
	public void EntityShootBowEvent(EntityShootBowEvent event) {
		LivingEntity shooter = event.getEntity();
		Entity projectile = event.getProjectile();

		if (!(shooter instanceof Player))
			return;
		if (!(projectile instanceof Arrow))
			return;
		Player player = (Player) shooter;

		ItemStack bow = player.getItemInHand();
		ItemMeta bowMeta = bow.getItemMeta();

		Arrow arrow = (Arrow) projectile;

		if (bowMeta.getDisplayName().equals(ChatColor.AQUA + "Bomb Bow")) {

			boolean hasVariance = false;
			if (bowMeta.getLore().get(0).toString().equals("Variance"))
				hasVariance = true;

			arrow.setBounce(true);

			arrow.setMetadata("isBombArrow", new FixedMetadataValue(plugin, true));
			arrow.setMetadata("hasVariance", new FixedMetadataValue(plugin, hasVariance));
		} else if (bowMeta.getDisplayName().equals(ChatColor.AQUA + "Creeper Gun")) {
			LivingEntity creeper = (LivingEntity) player.getWorld().spawnEntity(projectile.getLocation(),
					EntityType.CREEPER);
			creeper.setVelocity(arrow.getVelocity());
			creeper.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15 * 20, 127));
			Creeper creeperClass = (Creeper) creeper;
			creeperClass.setPowered(true);

			projectile.remove();
		} else if (bowMeta.getDisplayName().equals(ChatColor.AQUA + "TNT Gun")) {
			TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(projectile.getLocation(), EntityType.PRIMED_TNT);
			tnt.setVelocity(arrow.getVelocity());
			tnt.setFuseTicks(115);
			
			projectile.remove();
		} else if (bowMeta.getDisplayName().equals(ChatColor.BLUE + "Fragmentation Bow")) {
			arrow.setMetadata("fragArrow", new FixedMetadataValue(plugin, "true"));
		}
	}
}
