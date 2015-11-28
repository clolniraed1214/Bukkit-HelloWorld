package com.rootbeer.bukkitHelloWorld.other;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.rootbeer.bukkitHelloWorld.HelloWorld;

public class DeathRainWeather {
	private int rainWeight;
	private boolean doRain;
	private boolean isRaining = false;
	private HelloWorld plugin;
	private World world;
	private boolean broadcastOnChange;
	private static Biome[] badBiomes = new Biome[] { Biome.DESERT, Biome.DESERT_HILLS, Biome.DESERT_MOUNTAINS,
			Biome.MESA, Biome.MESA_BRYCE, Biome.MESA_PLATEAU, Biome.MESA_PLATEAU_FOREST,
			Biome.MESA_PLATEAU_FOREST_MOUNTAINS, Biome.MESA_PLATEAU_MOUNTAINS };

	public DeathRainWeather(HelloWorld pl, World world2) {
		doRain = pl.getConfig().getString("AllowRainOnLoad").equals("true");
		plugin = pl;
		world = world2;
		broadcastOnChange = pl.getConfig().getString("BroadcastOnChange").equals("true");
	}

	public void beginRain() {
		setRaining(true);
		rainWeight = 1;
		loop();
	}

	public void endRain() {
		setRaining(false);
		rainWeight = 0;
	}

	private void loop() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				if (isRaining() && doRain()) {
					getThis().loop();
				}
			}
		}, 5L);

		world = Bukkit.getWorlds().get(0);
		Chunk[] chunks = world.getLoadedChunks();

		for (Chunk chunk : chunks) {
			for (int i = 0; i < (rainWeight); i++) {
				if (Math.random() >= .998) {
					int randX = (int) (Math.random() * 16);
					int randZ = (int) (Math.random() * 16);

					Block block = chunk.getBlock(randX, 1, randZ);

					if (!(Arrays.asList(badBiomes).contains(block.getBiome()))) {
						Location blockLoc = block.getLocation();
						int blockX = blockLoc.getBlockX();
						int blockZ = blockLoc.getBlockZ();

						world.spawnEntity(new Location(world, blockX, 256, blockZ), EntityType.SNOWBALL);
					}
				}
			}
		}

		double rainChange = Math.random();

		if (rainChange <= .003) {
			lightenRain();
		} else if (rainChange >= .997) {
			heavyRain();
		}
	}

	public void lightenRain() {
		if (rainWeight > 1) {
			rainWeight -= 1;
			if (broadcastOnChange) {
				List<Player> players = world.getPlayers();
				for (int index = 0; index < players.size(); index++) {
					players.get(index).sendMessage(ChatColor.DARK_GREEN + "The rain lightens...");
				}
			}
		}
	}

	public void heavyRain() {
		if (rainWeight < 10) {
			rainWeight += 1;
			if (broadcastOnChange) {
				List<Player> players = world.getPlayers();
				for (int index = 0; index < players.size(); index++) {
					players.get(index).sendMessage(ChatColor.DARK_GREEN + "The rain thickens...");
				}
			}
		}
	}

	// GETTERS AND SETTERS //
	public boolean doRain() {
		return doRain;
	}

	public void setDoRain(boolean doRain) {
		this.doRain = doRain;
	}

	public boolean isRaining() {
		return isRaining;
	}

	public void setRaining(boolean isRaining) {
		this.isRaining = isRaining;
	}

	private final DeathRainWeather getThis() {
		return this;
	}
}
