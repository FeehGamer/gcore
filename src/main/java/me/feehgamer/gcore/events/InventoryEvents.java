package me.feehgamer.gcore.events;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.guis.StaffGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {
    private static Main plugin;

    public InventoryEvents(Main plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        String title = e.getInventory().getTitle();
        if(title.equals(StaffGUI.inv_name)){
            e.setCancelled(true);
            if(e.getCurrentItem() == null){
                return;
            }
        }
    }
}
