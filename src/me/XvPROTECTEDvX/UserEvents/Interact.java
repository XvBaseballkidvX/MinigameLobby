package me.XvPROTECTEDvX.UserEvents;

import me.XvPROTECTEDvX.ItemIcons.ItemIcons;
import me.XvPROTECTEDvX.Objects.Hat;
import me.XvPROTECTEDvX.ServerUtil.HatManager;
import me.XvPROTECTEDvX.Stacker.Stacker;
import me.XvPROTECTEDvX.VyzonHub.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by joshuabetz on 3/11/14.
 */
public class Interact implements Listener {

    ArrayList<String> waiting = new ArrayList<String>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if(player.getItemInHand().equals(ItemIcons.SERVER_SELECTOR)){
                showInventory(player);
            }else if(player.getItemInHand().equals(ItemIcons.HAT_SHOP)){
                this.openCustomHatInventory(player);
            }else if(player.getItemInHand().equals(ItemIcons.STACKER_ON)){
                if(waiting.contains(player.getName())){
                    player.sendMessage(Main.getInstance().prefix + "Do not spam the Magic Blaze Rod!");
                }else{
                    player.getInventory().setItem(2, ItemIcons.STACKER_OFF);
                    player.updateInventory();
                    player.sendMessage("§8[§aVyzon§8] §7Stacker Status: §cOff");
                    player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
                    Stacker.getManager().removePlaying(player.getName());
                    this.addToWaiting(player.getName());
                }

            }else if(player.getItemInHand().equals(ItemIcons.STACKER_OFF)){
                if(waiting.contains(player.getName())){
                    player.sendMessage(Main.getInstance().prefix + "Do not spam the Magic Stick!");
                }else{
                    player.getInventory().setItem(2, ItemIcons.STACKER_ON);
                    player.updateInventory();
                    player.sendMessage("§8[§aVyzon§8] §7Stacker Status: §aOn");
                    player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
                    Stacker.getManager().addPlaying(player.getName());
                    this.addToWaiting(player.getName());
                }
            }
        }
    }


    public void openCustomHatInventory(Player player){
        Inventory inv = Bukkit.createInventory(player, 18, "§nVyzon Hat Shop");
        List<ItemStack> items = new ArrayList<ItemStack>();
        for(Hat hat : HatManager.getInstance().getHats()){
            Material mat = hat.getHat().getType();
            ItemStack set = new ItemStack(mat);
            ItemMeta meta = set.getItemMeta();
            if(player.hasPermission(hat.getPermission())){
                meta.setDisplayName(hat.getHat().getItemMeta().getDisplayName());
                meta.setLore(Arrays.asList("§7UNLOCKED"));
                set.setItemMeta(meta);
                items.add(set);
            }else{
                ItemMeta m = set.getItemMeta();
                m.setLore(hat.getHat().getItemMeta().getLore());
                m.setDisplayName(hat.getHat().getItemMeta().getDisplayName());
                String first = m.getLore().get(0);
                m.setLore(Arrays.asList(first, "§cLOCKED"));
                set.setItemMeta(m);
                items.add(set);
            }
        }
        ItemStack[] contents = new ItemStack[items.size()];
        for(int i = 0; i < 9; i++){
            contents[i] = items.get(i);
        }
        inv.setContents(contents);
        inv.setItem(17, ItemIcons.CLEAR_HAT);
        player.openInventory(inv);
    }



    public void showInventory(Player player){
        Inventory inv = Bukkit.createInventory(player, 9, "§nVyzon Servers!");
        inv.setItem(0, ItemIcons.HUB);
        inv.setItem(1, ItemIcons.SKYBLOCK_ICON);
        inv.setItem(2, ItemIcons.SURVIVAL_ICON);
        inv.setItem(3, ItemIcons.PRISON_ICON);
        inv.setItem(4, ItemIcons.FACTIONS_ICON);
        inv.setItem(5, ItemIcons.CREATIVE_ICON);
        inv.setItem(6, ItemIcons.KITPVP_ICON);
        inv.setItem(7, ItemIcons.SKYWARS_ICON);
        inv.setItem(8, ItemIcons.MINIGAME_LOBBY_ICON);
        player.openInventory(inv);
    }

    public static String getInventoryName(){
        return "§nVyzon Servers!";
    }

    public void addToWaiting(final String name){
        waiting.add(name);
        new BukkitRunnable(){
            public void run(){
                waiting.remove(name);
            }
        }.runTaskLater(Main.getInstance(), 20*4);
    }
}
