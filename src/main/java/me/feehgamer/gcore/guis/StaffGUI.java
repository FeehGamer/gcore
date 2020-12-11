package me.feehgamer.gcore.guis;

import me.feehgamer.gcore.Utils;
import me.feehgamer.gcore.managers.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.ArrayList;
import java.util.List;
import me.feehgamer.gcore.Main;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffGUI {
    public static Inventory inv;
    public static Main plugin;
    public static String inv_name;
    public static int rows = 3;
    public static int cells = 9;
    public static int inv_total = rows * cells;

    public static void init(){
        inv_name = Utils.chat("&c&lONLINE STAFF");
        inv = Bukkit.createInventory(null, inv_total);
    }
    public static Inventory GUI(Player p){
        String worldName = p.getWorld().getName();
        if(Utils.isVanished(p)){
                worldName = Utils.chat("&cCan't get");
        }
        Inventory toReturn = Bukkit.createInventory(null, inv_total, inv_name);
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                Player on = players.get(i);
                if(on.hasPermission("galaxycore.staff")){

                        GUIManager.createSkull(inv, 1, i+1, on, "&f" + on.getDisplayName(),"&7Nickname: &e"+ on.getName(),
                                PlaceholderAPI.setPlaceholders(on, Utils.chat("&7Rank: &e%vault_rank%")),
                                "&7Playtime: &e" + Utils.getPlaytime(on), "&7World he is in: &e" + worldName);

                }
            }
        toReturn.setContents(inv.getContents());
        return toReturn;
    }

}
