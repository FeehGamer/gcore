package me.feehgamer.gcore.commands.vip;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    private Main plugin;
    public Fly(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Utils.chat("&cConsole can't use this command!"));
            return false;
        }
        Player p = (Player)sender;
        if(p.hasPermission("galaxycore.vip.fly")){
            if(args.length > 0){
                if(p.hasPermission("galaxycore.admin.fly.others")){
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target == null){
                        Utils.sendActionbar(p, "&cPlayer " + args[0] + " never joined this server!");
                        return false;
                    }
                    if(!target.isOnline()){
                        Utils.sendActionbar(p, "&cPlayer " + target.getName() + " is not online!");
                        return false;
                    }
                    if(target.getAllowFlight() == true){
                        target.setAllowFlight(false);
                        Utils.sendActionbar(target, "&e&lFLY &6&l &eFlying was turned off!");
                        Utils.sendActionbar(p, "&e&lFLY &6&l &eFlying for &6" + target.getName() + " &ewas turned off!");
                        return true;
                    }
                    target.setAllowFlight(true);
                    Utils.sendActionbar(target, "&e&lFLY &6&l &eFlying was turned on!");
                    Utils.sendActionbar(p, "&e&lFLY &6&l &eFlying for &6" + target.getName() + " &ewas turned on!");
                    return true;
                }
            }
            if(p.getAllowFlight() == true){
                p.setAllowFlight(false);
                Utils.sendActionbar(p, "&e&lFLY &6&l &eFlying for &6" + p.getName() + " &ewas turned off!");
                return true;
            }
            p.setAllowFlight(true);
            Utils.sendActionbar(p, "&e&lFLY &6&l &eFlying for &6" + p.getName() + " &ewas turned on!");
            return true;
        }
        Utils.sendActionbar(p, "&cYou don't have permissions to use this command!");
        return false;
    }
}
