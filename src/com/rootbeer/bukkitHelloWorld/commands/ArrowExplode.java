package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import com.rootbeer.bukkitHelloWorld.HelloWorld;

public class ArrowExplode implements CommandExecutor {

	static int SPEED = 3;
	static int ARROWS_AT_BASE = 90;

	static double ARROW_SPACING = (2 * Math.PI * SPEED) / ARROWS_AT_BASE;
	static double PITCH_INCREMENT = Math.toRadians(180 / (ARROWS_AT_BASE / 2));
	HelloWorld plugin;

	public ArrowExplode(HelloWorld pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		Player player = (Player) sender;

		long arrows;
		double diam;
		double arrowSpace;

		for (double pitch = Math.toRadians(-90); pitch <= Math.toRadians(90); pitch += PITCH_INCREMENT) {
			diam = getSphereDiam(pitch, SPEED);
			arrows = Math.round((diam / ARROW_SPACING));
			arrowSpace = Math.toRadians(360) / arrows;

			for (int i = 0; i < arrows; i++) {
				Arrow arrow = (Arrow) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARROW);
				
				Vector arrowVel = getVector(pitch, i * arrowSpace);

				arrow.setVelocity(arrowVel);
				
				if (args.length > 0) {
					arrow.setMetadata("isBombArrow", new FixedMetadataValue(plugin, true));
				}
				arrow.setMetadata("deleteOnHit", new FixedMetadataValue(plugin, true));
			}
		}

		return false;
	}

	private double getSphereDiam(double angle, int sphereRad) {
		double rad = Math.cos(angle) * sphereRad;
		double diam = 2 * Math.PI * rad;
		return diam;
	}
	
	private Vector getVector(double pitch, double yaw) {
		double yVel = SPEED * Math.sin(pitch);
		double horVel = SPEED * Math.cos(pitch);
		
		double xVel = horVel * Math.cos(yaw);
		double zVel = horVel * Math.sin(yaw);
		
		Vector vec = new Vector(xVel, yVel, zVel);
		return vec;
	}
}
