package com.rootbeer.bukkitHelloWorld.event.other;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.rootbeer.bukkitHelloWorld.HelloWorld;
import com.rootbeer.bukkitHelloWorld.commands.ArrowExplode;

public class ProjectileHit implements Listener {
	
	private HelloWorld plugin;
	public ProjectileHit (HelloWorld pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Projectile p = event.getEntity();
		Location loc = p.getLocation();
		World world = loc.getWorld();
		if (p.getType() == EntityType.SNOWBALL) {		
			world.createExplosion(loc, 4.0f);
		} else if (p.getType() == EntityType.ARROW){
			Arrow arrow = (Arrow) p;
			if (arrow.hasMetadata("isBombArrow")) {
				if (arrow.getMetadata("isBombArrow").get(0).asBoolean()) {
					if (arrow.getMetadata("isBombArrow").get(0).asBoolean()) { 
						
						float destruction = 4.0f;
						if (!arrow.hasMetadata("deleteOnHit")) {
							if (arrow.getMetadata("hasVariance").get(0).asBoolean()) destruction = (float) ((float) arrow.getTicksLived()/7.5);
						}
						
						arrow.getWorld().createExplosion(loc, destruction);
						arrow.remove();
					}
				}
			} else if (arrow.hasMetadata("deleteOnHit")) {
				if (arrow.getMetadata("deleteOnHit").get(0).asBoolean()) {
					arrow.remove();
				}
			} else if (arrow.hasMetadata("fragArrow")) {
				ArrowExplode.arrowExplode(arrow.getLocation(), false, plugin);
			}
		}
	}
}
