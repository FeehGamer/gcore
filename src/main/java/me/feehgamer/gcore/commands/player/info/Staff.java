package me.feehgamer.gcore.commands.player.info;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import me.feehgamer.gcore.guis.StaffGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Staff implements CommandExecutor{
    private Main plugin;
    public Staff(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("staff").setExecutor(this);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        p.openInventory(StaffGUI.GUI(p));
        p.updateInventory();
        return true;
    }
}
