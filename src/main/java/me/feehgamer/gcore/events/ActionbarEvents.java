package me.feehgamer.gcore.events;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ActionbarEvents implements Listener {
    private static Main plugin;

    public ActionbarEvents(Main plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
        Player p1 = e.getPlayer();
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            Utils.sendActionbar(p, Utils.chat("&8[&a+&8] &7" + p1.getName()));
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage("");
        Player p1 = e.getPlayer();
        for (Player p : Bukkit.getServer().getOnlinePlayers()){
            Utils.sendActionbar(p, Utils.chat("&8[&c-&8] &7" + p1.getName()));
        }
    }
}
