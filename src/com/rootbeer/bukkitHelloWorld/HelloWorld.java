package com.rootbeer.bukkitHelloWorld;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.rootbeer.bukkitHelloWorld.commands.AllowRain;
import com.rootbeer.bukkitHelloWorld.commands.ArrowExplode;
import com.rootbeer.bukkitHelloWorld.commands.BombBow;
import com.rootbeer.bukkitHelloWorld.commands.ChangeRain;
import com.rootbeer.bukkitHelloWorld.commands.CreeperGun;
import com.rootbeer.bukkitHelloWorld.commands.DeathRain;
import com.rootbeer.bukkitHelloWorld.commands.Equip;
import com.rootbeer.bukkitHelloWorld.commands.GetBombBow;
import com.rootbeer.bukkitHelloWorld.commands.Hello;
import com.rootbeer.bukkitHelloWorld.commands.Info;
import com.rootbeer.bukkitHelloWorld.commands.Menu;
import com.rootbeer.bukkitHelloWorld.commands.Refill;
import com.rootbeer.bukkitHelloWorld.commands.TNTGun;
import com.rootbeer.bukkitHelloWorld.event.block.BlockBreak;
import com.rootbeer.bukkitHelloWorld.event.entity.ShootBow;
import com.rootbeer.bukkitHelloWorld.event.inventory.InventoryClick;
import com.rootbeer.bukkitHelloWorld.event.other.ProjectileHit;
import com.rootbeer.bukkitHelloWorld.event.player.PlayerChat;
import com.rootbeer.bukkitHelloWorld.event.player.PlayerJoin;
import com.rootbeer.bukkitHelloWorld.event.weather.WeatherChange;
import com.rootbeer.bukkitHelloWorld.other.DeathRainWeather;

public class HelloWorld extends JavaPlugin {
	DeathRainWeather deathRain;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		
		registerInstances();
		registerCommands();
		registerEvents();
		registerConfig();
		registerPerms();
		registerRecipes();

		logger.info(pdfFile.getName() + " has been enabled. v" + pdfFile.getVersion());
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " has been disabled.");
	}
	
	private void registerInstances() {
		World world = Bukkit.getWorlds().get(0);
		deathRain = new DeathRainWeather(this, world);
	}

	private void registerCommands() {
		getCommand("hello").setExecutor(new Hello(this));
		getCommand("menu").setExecutor(new Menu());
		getCommand("equip").setExecutor(new Equip(this));
		getCommand("refill").setExecutor(new Refill());
		getCommand("info").setExecutor(new Info());
		getCommand("getbombbow").setExecutor(new GetBombBow());
		getCommand("bombbow").setExecutor(new BombBow());
		getCommand("deathrain").setExecutor(new DeathRain(this));
		getCommand("creepergun").setExecutor(new CreeperGun());
		getCommand("tntgun").setExecutor(new TNTGun());
		getCommand("arrowexp").setExecutor(new ArrowExplode(this));
		getCommand("allowrain").setExecutor(new AllowRain(this, deathRain));
		getCommand("changerain").setExecutor(new ChangeRain(deathRain));
	}

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new ProjectileHit(this), this);
		pm.registerEvents(new ShootBow(this), this);
		pm.registerEvents(new WeatherChange(Bukkit.getWorlds().get(0), deathRain), this);
	}

	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void registerPerms() {
		PluginManager pm = getServer().getPluginManager();

		pm.addPermission(new Permission("helloworld.snowball"));
	}

	private void registerRecipes() {
		getServer().addRecipe(CreeperGun.getCreeperGunRecipe());
		getServer().addRecipe(TNTGun.getTNTGunRecipe());
	}
}
