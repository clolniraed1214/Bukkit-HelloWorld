package com.rootbeer.bukkitHelloWorld.other;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.rootbeer.bukkitHelloWorld.HelloWorld;

public class DeathRainInstance {
	private long loopNum = -1;
	private int schedule = -1;
	private int multiples = 1;

	public DeathRainInstance(Player player, String[] args, HelloWorld plugin) {
		int radius = Integer.parseInt(args[0]) - 1;
		int seconds = Integer.parseInt(args[1]);
		int freq = Integer.parseInt(args[2]);
		Location center = player.getLocation();
		
		if (freq > 20) {
			multiples = freq / 20;
			freq /= 20;
		}

		long delay = 20;
		if (args.length > 3) {
			delay = Long.parseLong(args[3]) * 20;
		}

		long loops = seconds * freq;
		setLoopNum(0L);
		double freqDouble = intToDouble(freq);

		schedule = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if (getLoopNum() < loops) {
					incLoopNum();
				} else {
					Bukkit.getScheduler().cancelTask(schedule);
				}
				for (int i = 0; i < getMultiples(); i++) {
					int randX = 0 - radius + (int) (Math.random() * ((2 * radius) + 1));
					int randZ = 0 - radius + (int) (Math.random() * ((2 * radius) + 1));

					World world = player.getWorld();
					Location loc = new Location(world, center.getBlockX() + randX, 255, center.getBlockZ() + randZ);
					player.getWorld().spawnEntity(loc, EntityType.SNOWBALL);
				}
			}
		}, delay, Math.round((1 / freqDouble) * 20.0));
	}

	private final long getLoopNum() {
		return loopNum;
	}

	private final void setLoopNum(long setTo) {
		this.loopNum = setTo;
	}

	private final void incLoopNum() {
		loopNum++;
	}

	private double intToDouble(int value) {
		return Double.parseDouble(Integer.toString(value) + ".0");
	}

	public int getMultiples() {
		return multiples;
	}

	public void setMultiples(int multiples) {
		this.multiples = multiples;
	}
}
