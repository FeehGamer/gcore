package me.feehgamer.gcore.commands.admin.moderation;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Broadcast implements CommandExecutor {
    private Main plugin;
    public Broadcast(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("broadcast").setExecutor(this);
        plugin.getCommand("actionbar").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            if(sender.hasPermission("galaxycore.broadcast.chat")){
                if(args.length == 0){
                    sender.sendMessage(Utils.chat("&e&lUSAGE &6&l» &e/broadcast &6<message>"));
                    return false;
                }
                Bukkit.broadcastMessage(Utils.chat("&e&lBROADCAST &6&l» &e" + Utils.convertStringArrayToString(args, " ")));
                return true;
            }
            sender.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
            return false;
        }
        if (cmd.getName().equalsIgnoreCase("actionbar")) {
            if(sender.hasPermission("galaxycore.broadcast.actionbar")){
                if(args.length == 0){
                    sender.sendMessage(Utils.chat("&e&lUSAGE &6&l» &e/actionbar &6<message>"));
                    return false;
                }
                for(Player p : Bukkit.getOnlinePlayers()){
                    Utils.sendActionbar(p, Utils.chat(Utils.convertStringArrayToString(args, " ")));
                }
                return true;
            }
            sender.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
            return false;
        }
        return false;
    }
}
