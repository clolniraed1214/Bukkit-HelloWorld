package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.rootbeer.bukkitHelloWorld.HelloWorld;
import com.rootbeer.bukkitHelloWorld.lib.CommandDetails;

public class ArrowExplode implements CommandExecutor {

	int SPEED;
	int ARROWS_AT_BASE;
	
	double ARROW_SPACING;
	double PITCH_INCREMENT;
	HelloWorld plugin;

	public ArrowExplode(HelloWorld pl) {
		plugin = pl;
		
		SPEED = plugin.getConfig().getInt("SPEED");
		ARROWS_AT_BASE = plugin.getConfig().getInt("Arrow Explode Density");
		
		ARROW_SPACING = (2 * Math.PI * SPEED) / ARROWS_AT_BASE;
		PITCH_INCREMENT = Math.toRadians(180 / (ARROWS_AT_BASE / 2));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (CommandDetails.failsPlayerCheck(sender)) return false;
		Player player = (Player) sender;

		arrowExplode(plugin, player.getLocation(), args.length > 0, plugin, true, "", false);
		return false;
	}
	
	public static void arrowExplode (HelloWorld pl, Location hitLoc, boolean explode, JavaPlugin plugin, boolean shiftUp, String owner, boolean highDamage) {
		long arrows;
		double diam ;
		double arrowSpace;
		Location center = hitLoc;
		
		plugin = pl;
		
		double SPEED = plugin.getConfig().getDouble("Speed");
		int ARROWS_AT_BASE = plugin.getConfig().getInt("Arrow Explode Density");
		
		double ARROW_SPACING = (2 * Math.PI * SPEED) / ARROWS_AT_BASE;
		double PITCH_INCREMENT = Math.toRadians(180 / (ARROWS_AT_BASE / 2));
		
		if (shiftUp) {
			center = new Location(hitLoc.getWorld(), hitLoc.getX(), hitLoc.getY() + 3, hitLoc.getZ());
		}

		for (double pitch = Math.toRadians(-90); pitch <= Math.toRadians(90); pitch += PITCH_INCREMENT) {
			diam = getSphereDiam(pitch, SPEED);
			arrows = Math.round((diam / ARROW_SPACING));
			arrowSpace = Math.toRadians(360) / arrows;

			for (int i = 0; i < arrows; i++) {
				Arrow arrow = (Arrow) center.getWorld().spawnEntity(center, EntityType.ARROW);
				
				Vector arrowVel = getVector(pitch, i * arrowSpace).multiply(SPEED);

				arrow.setVelocity(arrowVel);
				
				if (explode) {
					arrow.setMetadata("isBombArrow", new FixedMetadataValue(plugin, true));
				}
				arrow.setMetadata("deleteOnHit", new FixedMetadataValue(plugin, true));
				arrow.setMetadata("owner", new FixedMetadataValue(plugin, owner));
				
				if (highDamage) {
					arrow.setMetadata("highDamage", new FixedMetadataValue(plugin, true));
				}
			}
		}
	}

	private static double getSphereDiam(double angle, double sphereRad) {
		double rad = Math.cos(angle) * sphereRad;
		double diam = 2 * Math.PI * rad;
		return diam;
	}
	
	private static Vector getVector(double pitch, double yaw) {
		double yVel = Math.sin(pitch);
		double horVel = Math.cos(pitch);
		
		double xVel = horVel * Math.cos(yaw);
		double zVel = horVel * Math.sin(yaw);
		
		Vector vec = new Vector(xVel, yVel, zVel);
		return vec;
	}
}
