package com.rootbeer.bukkitHelloWorld.event.weather;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.rootbeer.bukkitHelloWorld.other.DeathRainWeather;

public class WeatherChange implements Listener {
	private World world;
	private DeathRainWeather deathRain;
	
	public WeatherChange(World wor, DeathRainWeather dr) {
		world = wor;
		deathRain = dr;
	}
	
	@EventHandler
	public void WeatherChangeEvent (WeatherChangeEvent event) {
		deathRain.setRaining(true);
		if (world.getWeatherDuration() > 0) {
			deathRain.beginRain();
		} else {
			deathRain.endRain();
		}
	}
}
