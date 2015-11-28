package com.rootbeer.bukkitHelloWorld.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.rootbeer.bukkitHelloWorld.HelloWorld;

public class Equip implements CommandExecutor {

	private HelloWorld plugin;

	public Equip(HelloWorld pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		Player player = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 9, "Equip");
		List<String> admins = plugin.getConfig().getStringList("Server Admins");

		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack arrows = new ItemStack(Material.ARROW, 64);
		PlayerInventory playInv = player.getInventory();

		if (args.length > 0) {
			String kitName = args[0];
			if (kitName.equalsIgnoreCase("admin")) {
				if (admins.contains(player.getName())) {
					sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 127);
					sword.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
					sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 127);

					bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
					bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 127);
					bow.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
					bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 127);

					ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
					chestplate.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 127);
					chestplate.addUnsafeEnchantment(Enchantment.THORNS, 127);
					chestplate.addUnsafeEnchantment(Enchantment.OXYGEN, 127);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 127);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 127);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 127);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 127);
					chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 127);
					
					if (args.length > 1) {
						if (args[1].equals("super")) {
							PotionEffect resist = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 12000, 127);
							PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 12000, 3);
							PotionEffect jump = new PotionEffect(PotionEffectType.JUMP, 12000, 3);
							
							
							player.removePotionEffect(PotionEffectType.JUMP);
							player.removePotionEffect(PotionEffectType.SPEED);
							player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
							
							player.addPotionEffect(jump);
							player.addPotionEffect(speed);
							player.addPotionEffect(resist);
							
							inv.setItem(8, new ItemStack(Material.MILK_BUCKET));
							inv.setItem(7, new ItemStack(Material.SNOW_BLOCK, 64));
							inv.setItem(6, new ItemStack(Material.PUMPKIN, 32));
						}
					}
					
					inv.addItem(sword, bow, arrows, chestplate);
					player.openInventory(inv);
				} else {
					player.sendMessage("You are not an admin!");
				}
			} else {
				player.sendMessage("That is not an equip!");
			}
		} else {
			playInv.clear();
			playInv.setBoots(new ItemStack(Material.AIR));
			playInv.setHelmet(new ItemStack(Material.AIR));
			playInv.setChestplate(new ItemStack(Material.AIR));
			playInv.setLeggings(new ItemStack(Material.AIR));

			inv.addItem(sword, bow, arrows);
			player.openInventory(inv);
		}
		
		return true;
	}

}
