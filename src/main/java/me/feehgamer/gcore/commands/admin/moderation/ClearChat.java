package me.feehgamer.gcore.commands.admin.moderation;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    private Main plugin;
    public ClearChat(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("clearchat").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {

        }
        Player p = (Player)sender;
        if(p.hasPermission("galaxycore.moderation.clearchat")){
            for(Player p1 : Bukkit.getOnlinePlayers()){
                if(p1.hasPermission("galaxycore.bypass.clearchat")){
                    Utils.sendActionbar(p1, Utils.chat("&e&lCLEARCHAT &6&l» &eChat was cleared by &6" + p.getName()));
                } else {
                    for(int i = 0; i<200; i++){
                        p1.sendMessage("");
                    }
                    Utils.sendActionbar(p1, Utils.chat("&e&lCLEARCHAT &6&l» &eChat was cleared by &6" + p.getName()));
                }
            }
            return true;
        } else {
            Utils.sendActionbar(p, Utils.chat("&cYou don't have permission to use this command!"));
        }
        return false;
    }
}
