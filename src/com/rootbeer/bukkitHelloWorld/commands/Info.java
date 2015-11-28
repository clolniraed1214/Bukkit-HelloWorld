package com.rootbeer.bukkitHelloWorld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Info implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		Player player = (Player) sender;

		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(
				"{\"text\":\"Click to view Server Info!\",\"color\":\"dark_green\",\"italic\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://minecraft.net\"}}"));
		connection.sendPacket(packet);
		
		return false;
	}

}
