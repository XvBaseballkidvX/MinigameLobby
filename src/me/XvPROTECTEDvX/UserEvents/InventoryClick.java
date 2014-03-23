package me.XvPROTECTEDvX.UserEvents;

import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.RedirectRequest;
import me.XvPROTECTEDvX.Economy.Coins;
import me.XvPROTECTEDvX.ItemIcons.ItemIcons;
import me.XvPROTECTEDvX.Objects.Hat;
import me.XvPROTECTEDvX.Objects.ServerIcon;
import me.XvPROTECTEDvX.ServerUtil.HatManager;
import me.XvPROTECTEDvX.ServerUtil.IconUtil;
import me.XvPROTECTEDvX.VyzonHub.Main;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class InventoryClick implements Listener{

    Connect connect = Main.getInstance().getServer().getServicesManager().getRegistration(Connect.class).getProvider();

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if(event.getInventory().getName().equalsIgnoreCase(Interact.getInventoryName())){
                event.setCancelled(true);
                ItemStack clicked = event.getCurrentItem();
                if(clicked == null) return;
                if(clicked.hasItemMeta()){
                    ServerIcon icon =  IconUtil.getManager().getClickedServerIcon(clicked);
                    player.closeInventory();
                    if(icon == null) return;
                    player.sendMessage("§aConnecting you to the " + icon.getName() + " server!");
                    try{
                        connect.request(new RedirectRequest(icon.getName(), player.getName()));
                    } catch (RequestException e){
                        e.printStackTrace();
                    }
                }
            }else if(event.getInventory().getName().equalsIgnoreCase("§nVyzon Hat Shop")){
                event.setCancelled(true);
                ItemStack clicked = event.getCurrentItem();
                if(clicked == null) return;
                if(clicked.hasItemMeta()){
                    if(clicked.equals(ItemIcons.CLEAR_HAT)){
                        if(player.getInventory().getHelmet() == null){
                            player.sendMessage(Main.getInstance().prefix + "You aren't wearing a hat!");
                            player.closeInventory();
                        }else{
                            player.sendMessage(Main.getInstance().prefix + "Removing your hat...");
                            player.getInventory().setHelmet(null);
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 5, 3);
                        }
                    }
                    Hat hat = HatManager.getInstance().getClickedHat(clicked);
                    if(hat == null) return;
                    if(player.hasPermission(hat.getPermission())){
                        Material mat = hat.getHat().getType();
                        ItemStack stack = new ItemStack(mat);
                        ItemMeta meta = stack.getItemMeta();
                        meta.setLore(null);
                        meta.setDisplayName(hat.getHat().getItemMeta().getDisplayName());
                        stack.setItemMeta(meta);
                        player.getInventory().setHelmet(stack);
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 3, 100);
                        player.sendMessage("§8[§aVyzon§8]§7 You are now wearing the §6" + hat.getName() + "§7!");
                    }else{
                        int bal = Coins.getManager().getCoins(player.getName());
                        if(bal >= hat.getCost()){
                            Coins.getManager().removeCoins(player.getName(), hat.getCost());
                            Main.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " add " + hat.getPermission());
                            player.sendMessage(Main.getInstance().prefix + "Transaction successful! Enjoy your new hat!");
                            this.startCelebration(player, hat);
                            player.closeInventory();
                        }else{
                            int diff = hat.getCost() - bal;
                            player.sendMessage(Main.getInstance().prefix + "You need §6" + diff + "§7 more coins to make this purchase!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 3, 5);
                        }
                    }
                }
            }else if(event.getInventory().getType().equals(InventoryType.CRAFTING)){
                event.setCancelled(true);
            }else if(event.getSlotType().equals(InventoryType.SlotType.ARMOR)){
                event.setCancelled(true);
            }
        }
    }

    public void startCelebration(final Player player, Hat hat){
        Bukkit.broadcastMessage(Main.getInstance().prefix + "§6" + player.getName() + "§7 has just bought the §6" + hat.getName() + "§7!");
        final int TIMES = 7;
        new BukkitRunnable(){
            int index = TIMES - 1;
            public void run(){
                if(index > 0){
                    index-=1;
                    Firework work = player.getLocation().getWorld().spawn(player.getLocation(), Firework.class);
                    FireworkMeta meta = work.getFireworkMeta();
                    meta.setPower(1);
                    meta.addEffect(FireworkEffect.builder().withColor(Color.LIME).with(FireworkEffect.Type.BURST).withFlicker().withTrail().build());
                    work.setFireworkMeta(meta);
                }else{
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 5);
    }
}
