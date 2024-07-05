package ru.gikexe.thelocalchat;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public class ChatListener implements Listener {
	@EventHandler
	public void on(AsyncChatEvent event) {
		Player player = event.getPlayer();
		Location location = player.getLocation();
		Component message = event.message();
		int counter = 0;
		for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
			if (otherPlayer.equals(player)) continue;
			Location otherLocation = otherPlayer.getLocation();
			if (!location.getWorld().equals(otherLocation.getWorld())) continue;
			double distance = location.distance(otherLocation);
			if (distance > 50) continue;
			otherPlayer.sendMessage(Component.text((distance < 10 ? "0" : "") + (int) distance + "м ", GRAY)
				.append(Component.text("<"+player.getName()+"> ", WHITE))
				.append(message));
			counter += 1;
		}
		event.setCancelled(true);
		if (counter < 1) {
			player.sendActionBar(Component.text("Тебя никто не услышал (!)", RED));
		} else {
			player.sendMessage(Component.text("<"+player.getName()+"> ", WHITE).append(message));
		}
	}

	@EventHandler
	public void on(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		if (!(message.startsWith("/tell") || message.startsWith("/me") || message.startsWith("/msg"))) return;
		player.sendMessage(Component.text("Эта команда в разработке.", RED));
		event.setCancelled(true);
	}
}
