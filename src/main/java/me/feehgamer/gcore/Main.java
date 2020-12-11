package me.feehgamer.gcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.feehgamer.gcore.commands.admin.gamemode.Gamemode;
import me.feehgamer.gcore.commands.admin.moderation.Broadcast;
import me.feehgamer.gcore.commands.admin.moderation.ClearChat;
import me.feehgamer.gcore.commands.admin.moderation.KittyCannon;
import me.feehgamer.gcore.commands.admin.moderation.StaffChat;
import me.feehgamer.gcore.commands.player.Lobby;
import me.feehgamer.gcore.commands.player.info.ListCMD;
import me.feehgamer.gcore.commands.player.info.Staff;
import me.feehgamer.gcore.commands.vip.Fly;
import me.feehgamer.gcore.commands.vip.NightVision;
import me.feehgamer.gcore.events.ActionbarEvents;
import me.feehgamer.gcore.events.InventoryEvents;
import me.feehgamer.gcore.guis.StaffGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private ProtocolManager protocolManager;
    public static CommandSender console;
    @Override
    public void onEnable(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null){
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        if(Bukkit.getPluginManager().getPlugin("ProtocolLib") == null){
            getLogger().warning("Could not find ProtocolLib! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        protocolManager = ProtocolLibrary.getProtocolManager();
        registerCommands();
        registerEvents();
        registerGUI();
        getLogger().info("GCore v2.0 Enabled!");
        loadConfig();
    }
    @Override
    public void onDisable(){
        getLogger().info("GCore v2.0 Disabled!");
    }
    public void registerEvents(){
        new ActionbarEvents(this);
        new InventoryEvents(this);
    }
    public void registerCommands(){
        new Gamemode(this);
        new ClearChat(this);
        new Broadcast(this);
        new NightVision(this);
        new Fly(this);
        new KittyCannon(this);
        new Staff(this);
        new Lobby(this);
        new StaffChat(this);
        new ListCMD(this);
    }
    public void registerGUI() {
        StaffGUI.init();
    }
    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
