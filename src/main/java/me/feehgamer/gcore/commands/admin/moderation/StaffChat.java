package me.feehgamer.gcore.commands.admin.moderation;

import me.clip.placeholderapi.PlaceholderAPI;
import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffChat implements CommandExecutor {
    public static Set<UUID> player = new HashSet<>();
    private Main plugin;
    public StaffChat(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("staffchat").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player p = (Player)sender;
        if(p.hasPermission("galaxycore.staff")){
            if(args.length == 0){
                if(player.contains(p.getUniqueId())){
                    Utils.sendActionbar(p, "&bYou are no longer chatting in &3Staff chat!");
                    player.remove(p.getUniqueId());
                    return true;
                }
                Utils.sendActionbar(p, "&bYou are now chatting in &3Staff chat!");
                player.add(p.getUniqueId());
                return true;
            } else {
                for(Player pl : Bukkit.getOnlinePlayers()){
                    if(pl.hasPermission("galaxycore.staff")){
                        pl.sendMessage(
                                Utils.chat(PlaceholderAPI.setPlaceholders(p,Utils.chat("&b&lSTAFF &3&l»")
                                        + Utils.chat(" %vault_prefix%")) + p.getName()
                                        + " &3&l» &b" + Utils.convertStringArrayToString(args, " ")));
                    }
                }
            }
        }
        return false;
    }
}
