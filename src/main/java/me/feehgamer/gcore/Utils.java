package me.feehgamer.gcore;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Utils {
    private static Class<?> craftPlayer;

    private static Method handle;

    private static Field pingField;

    private static Main plugin;

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("GCore");
    }

    public static void tellConsole(final String message) {
        (new BukkitRunnable() {
            public void run() {
                Bukkit.getConsoleSender().sendMessage(message);
            }
        }).runTask(getPlugin());
    }
    public static boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }
    public static String getPlaytime(Player p) {
        Statistic tick = null;
        boolean minute = false;
        try {
            tick = Statistic.valueOf("PLAY_ONE_TICK");
        } catch (IllegalArgumentException iae) {
            tick = Statistic.valueOf("PLAY_ONE_MINUTE");
        }
        int time = p.getStatistic(tick) / (minute ? 1 : 20) / (minute ? 1 : 60);
        long tL = Long.parseLong(Integer.toString(time * 60 * 1000));
        return String.format("%02dd %02dh %02dm", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toDays(tL)), Long.valueOf(tL / 3600000L % 24L),
                Long.valueOf(tL / 60000L % 60L) });
    }
    public static int getPing(Player p) {
        CraftPlayer pingcraft = (CraftPlayer)p;
        EntityPlayer pingentity = pingcraft.getHandle();
        return pingentity.ping;
    }
    public static String convertStringArrayToString(String[] strArr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr)
            sb.append(str).append(delimiter);
        return sb.substring(0, sb.length() - 1);
    }
    public static void sendActionbar(Player p, String message) {
        Player player = Bukkit.getPlayer(p.getUniqueId());
        message = chat(message);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }
    public static boolean GM(GameMode mode, Player p, String[] arg){
        if(arg.length > 0){
            Player target = Bukkit.getPlayer(arg[0]);
            if(p.hasPermission("galaxycore.gamemode.others") && p.hasPermission("galaxycore.gamemode." + mode.toString().toLowerCase())){
                if(target == null){
                    sendActionbar(p, chat("&cPlayer" + arg[0] + "never joined server!"));
                    return false;
                }
                if(!target.isOnline()){
                    sendActionbar(p, chat("&cPlayer " + target.getName() + " is not online!"));
                    return false;
                }
                target.setGameMode(mode);
                sendActionbar(target, chat("&e&lGAMEMODE &6&l» &eYour gamemode was updated to &6" + mode.toString().toUpperCase()));
                sendActionbar(p, chat("&e&lGAMEMODE &6&l» &eGamemode of player &6" + target.getName() + " &ewas updated to &6" + mode.toString().toUpperCase()));
                return true;
            }
            sendActionbar(p, chat("&cYou don't have permission to use this command!"));
            return false;
        }
            if(p.hasPermission("galaxycore.gamemode." + mode.toString().toLowerCase())){
                p.setGameMode(mode);
                sendActionbar(p, chat("&e&lGAMEMODE &6&l» &eYour gamemode was updated to &6" + mode.toString().toUpperCase()));
                return true;
            }
            sendActionbar(p, chat("&cYou don't have permission to use this command!"));
            return false;
    }
}
