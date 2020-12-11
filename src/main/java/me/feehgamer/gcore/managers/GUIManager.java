package me.feehgamer.gcore.managers;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.feehgamer.gcore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIManager {
    public static ItemStack createItem(Inventory inv,int amount, int materialID, int invSlot, String displayName, String... loreStrings){
        List<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Material.getMaterial(materialID), amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);

        for(String s: loreStrings){
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot-1, item);
        return item;
    }
    public static ItemStack createItemByte(Inventory inv,int amount,int byteID, int materialID, int invSlot, String displayName, String... loreStrings){
        List<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Material.getMaterial(materialID), amount, (short) byteID);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);

        for(String s: loreStrings){
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot-1, item);
        return item;
    }
    public static ItemStack createSkull(Inventory inv, int amount, int invSlot, Player skullOwner, String displayName, String... loreStrings){
        List<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        meta.setOwningPlayer(skullOwner);
        for(String s: loreStrings){
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot-1, item);
        return item;
    }
}
