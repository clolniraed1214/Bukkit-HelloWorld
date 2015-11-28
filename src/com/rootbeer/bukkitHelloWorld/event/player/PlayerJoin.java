package com.rootbeer.bukkitHelloWorld.event.player;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class PlayerJoin implements Listener {

	//private HelloWorld plugin;

	//public PlayerJoin(HelloWorld hw) {
	//	plugin = hw;
	//}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// String welcomeMessage = ChatColor.translateAlternateColorCodes('&',
		// plugin.getConfig().getString("Welcome Message"));

		Player player = event.getPlayer();

		PacketPlayOutTitle welcomeTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE,ChatSerializer
						.a("{\"text\":\"Welcome to my Server!\",\"color\":\"gold\",\"bold\":true,\"underlined\":true}"),20, 40, 30);
		PacketPlayOutTitle welcomeSubtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,ChatSerializer
						.a("{\"text\":\"Enjoy your Stay\",\"color\":\"dark_red\",\"italic\":true}"),20, 40, 30);
		
		
		PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
		connection.sendPacket(welcomeTitle);
		connection.sendPacket(welcomeSubtitle);
	}
}
