package me.feehgamer.gcore.commands.admin.moderation;

import me.feehgamer.gcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

public class KittyCannon implements CommandExecutor {
    private Main plugin;
    public KittyCannon(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("kittycannon").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player p = (Player)sender;
        if(p.hasPermission("galaxycore.kittycannon")){
            EntityType cat = EntityType.OCELOT;

            final Ocelot ocelot = (Ocelot)p.getWorld().spawn(p.getEyeLocation(), cat.getEntityClass());
            ocelot.setBaby();
            ocelot.setTamed(true);
            ocelot.setOwner(p);
            ocelot.setCustomName("§eKitty <3");
            ocelot.setInvulnerable(true);
            ocelot.setCollidable(false);
            ocelot.setVelocity(p.getEyeLocation().getDirection().multiply(2));
            BukkitTask task = new BukkitRunnable() {
                public void run() {
                    if(ocelot.isOnGround()){
                        Location loc = ocelot.getLocation();
                        loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 1);
                        loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                        ocelot.remove();
                        cancel();
                    }
                }
            }.runTaskTimer(plugin,1,1);
            return true;
        }
        return false;
    }
}
