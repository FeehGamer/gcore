package me.feehgamer.gcore.commands.admin.gamemode;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Gamemode implements CommandExecutor {
    private Main plugin;
    GameMode mode = null;
    public Gamemode(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("gamemode").setExecutor(this);
        plugin.getCommand("gmc").setExecutor(this);
        plugin.getCommand("gms").setExecutor(this);
        plugin.getCommand("gma").setExecutor(this);
        plugin.getCommand("gmsp").setExecutor(this);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("gamemode")) {
            if (args.length == 0) {
                sender.sendMessage(Utils.chat("&e&lUSAGE &6&l &e/gamemode <creative|survival|spectator|adventure>"));
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cConsole can't use this command!");
            } else {
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    mode = GameMode.CREATIVE;
                } else if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                    mode = GameMode.SURVIVAL;
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                    mode = GameMode.ADVENTURE;
                } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp")) {
                    mode = GameMode.SPECTATOR;
                } else {
                    mode = null;
                }
                if (mode == null) {
                    p.sendMessage(Utils.chat("&e&lUSAGE &6&l» &e/gamemode <creative|survival|spectator|adventure>"));
                    return false;
                }

                if (p.hasPermission("galaxycore.gamemode." + mode.toString())) {
                    p.setGameMode(mode);
                    Utils.sendActionbar(p, Utils.chat("&e&lGAMEMODE &6&l» &eYour gamemode was set to &6" + mode.toString().toUpperCase()));
                    return true;
                } else {
                   Utils.sendActionbar(p, Utils.chat("&cYou don't have permission to use this command!"));
                }
            }
            return true;
        }







        if(cmd.getName().equalsIgnoreCase("gmc")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cConsole can't use this command!");
            } else {
                Player p = (Player)sender;
                Utils.GM(GameMode.CREATIVE, p, args);
            }
            return true;
        }



        if(cmd.getName().equalsIgnoreCase("gms")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cConsole can't use this command!");
            } else {
                Player p = (Player)sender;
                Utils.GM(GameMode.SURVIVAL, p, args);
            }
            return true;
        }







        if(cmd.getName().equalsIgnoreCase("gma")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cConsole can't use this command!");
            } else {
                Player p = (Player)sender;
                Utils.GM(GameMode.ADVENTURE, p, args);
            }
            return true;
        }







        if(cmd.getName().equalsIgnoreCase("gmsp")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cConsole can't use this command!");
            } else {
                Player p = (Player)sender;
                Utils.GM(GameMode.SPECTATOR, p, args);
            }
            return true;
        }
        return false;
    }
}
