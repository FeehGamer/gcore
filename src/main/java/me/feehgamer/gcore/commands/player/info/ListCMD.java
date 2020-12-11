package me.feehgamer.gcore.commands.player.info;

import me.feehgamer.gcore.Main;
import me.feehgamer.gcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("CollectionAddedToSelf")
public class ListCMD implements CommandExecutor {
    List<Player> players = new ArrayList<>();
    List<Player> vanishedPlayers = new ArrayList<>();
    List<String> playersName = new ArrayList<>();
    boolean addVanished;
    List<String> vanishedPlayersName = new ArrayList<>();
    int online = Bukkit.getOnlinePlayers().size();
    private Main plugin;
    public ListCMD(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("list").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmds, String label, String[] args) {
        if(!(sender instanceof Player)){
            players.addAll(Bukkit.getOnlinePlayers());
            sender.sendMessage("§7All online players §a(" + online +"/" + Bukkit.getServer().getMaxPlayers() +") §7§l» §7" + String.valueOf(playersName).replace("[", "").replace("]", "").replace(",", "§a, §7"));
            return true;
        }
        players.addAll(Bukkit.getOnlinePlayers());
            Player p = (Player)sender;
            for(Player checkVanished : players){
                if(Utils.isVanished(checkVanished)){
                    vanishedPlayers.add(checkVanished);
                }
            }
            for(Player remove : vanishedPlayers){
                players.remove(remove);
            }
                if(!p.hasPermission("galaxycore.vanished")){
                    online = Bukkit.getOnlinePlayers().size() - vanishedPlayers.size();
                    addVanished = false;
                } else {
                    addVanished = true;
                }
                for(Player pl : vanishedPlayers){
                    vanishedPlayersName.add(pl.getName());
                }
                for(Player pl : players){
                    playersName.add(pl.getName());
                    if(addVanished){
                        for(String s : vanishedPlayersName){
                            s = s + "§a§l VANISH";
                            playersName.add(s);
                            playersName.sort(String::compareToIgnoreCase);
                        }
                    }
                }
                p.sendMessage("§7All online players §a(" + online +"/" + Bukkit.getServer().getMaxPlayers() +") §7§l» §7" + String.valueOf(playersName).replace("[", "").replace("]", "").replace(",", "§a, §7"));
                vanishedPlayersName.removeAll(vanishedPlayersName);
                playersName.removeAll(playersName);
                players.removeAll(players);
                vanishedPlayers.removeAll(vanishedPlayers);

        return false;
    }
}
