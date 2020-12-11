package me.feehgamer.gcore.commands.vip;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision implements CommandExecutor {
    private Main plugin;
    public NightVision(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("nightvision").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Utils.chat("&cConsole can't use this command!"));
            return false;
        }
        Player p = (Player)sender;
        if(p.hasPermission("galaxycore.vip.nightvision")){
            PotionEffect eff = p.getPotionEffect(PotionEffectType.NIGHT_VISION);
            if(eff != null){
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                Utils.sendActionbar(p, "&e&lNIGHTVISION &6&l» &eNight vision was turned off!");
                return true;
            }
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
            Utils.sendActionbar(p, "&e&lNIGHTVISION &6&l» &eNight vision was turned on!");
            return true;
        }
        Utils.sendActionbar(p, "&cYou don't have permission to use this command!");
        return false;
    }
}
