package me.feehgamer.gcore.commands.player;

import me.feehgamer.gcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor {
    private Main plugin;
    public Lobby(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("lobby").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return false;
        }
        Player p = (Player)commandSender;
        p.chat("/spawn");
        return true;
    }
}
