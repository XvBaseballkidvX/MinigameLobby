package me.XvPROTECTEDvX.ItemIcons;

import me.XvPROTECTEDvX.UserEvents.Interact;
import me.XvPROTECTEDvX.VyzonHub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joshuabetz on 3/11/14.
 */
public class LoreTask {

    private static LoreTask task = new LoreTask();

    public static LoreTask getTasks(){
        return task;
    }

    public void startTask(){
        final int TIMES = 4;
        new BukkitRunnable(){
            int index = TIMES - 1;
            public void run(){
                index-=1;
                    if(index == 2){
                        refresh();
                        for(ItemStack item : ItemUtil.getManager().getItems()){
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            meta.setLore(Arrays.asList(lore.get(0), "§aClick to Join!"));
                            item.setItemMeta(meta);
                        }
                    }else if(index == 1){
                        refresh();
                        for(ItemStack item : ItemUtil.getManager().getItems()){
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            meta.setLore(Arrays.asList(lore.get(0), "§a➜ Click to Join!"));
                            item.setItemMeta(meta);
                        }
                        index+=2;
                    }
            }
        }.runTaskTimer(Main.getInstance(), 10, 10);
    }

    public void refresh(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getOpenInventory().getTitle().equalsIgnoreCase(Interact.getInventoryName())){
                InventoryView view = player.getOpenInventory();
                Inventory inv = view.getTopInventory();
                inv.setItem(0, ItemIcons.HUB);
                inv.setItem(1, ItemIcons.SKYBLOCK_ICON);
                inv.setItem(2, ItemIcons.SURVIVAL_ICON);
                inv.setItem(3, ItemIcons.PRISON_ICON);
                inv.setItem(4, ItemIcons.FACTIONS_ICON);
                inv.setItem(5, ItemIcons.CREATIVE_ICON);
                inv.setItem(6, ItemIcons.KITPVP_ICON);
                inv.setItem(7, ItemIcons.SKYWARS_ICON);
                inv.setItem(8, ItemIcons.MINIGAME_LOBBY_ICON);
            }
        }
    }
}
