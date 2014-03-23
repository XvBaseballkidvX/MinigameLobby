package me.XvPROTECTEDvX.Economy;

import me.XvPROTECTEDvX.VyzonHub.Main;
import net.minecraft.util.org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by joshuabetz on 3/13/14.
 */
public class CoinsListener implements Listener {

    ArrayList<String> cooldown = new ArrayList<String>();

    @EventHandler
    public void onSignInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if(block == null) return;
        if(block.getState() instanceof Sign){
            Sign sign = (Sign) block.getState();
            String line_one = ChatColor.stripColor(sign.getLine(0));
            if(line_one.equalsIgnoreCase("[Vyzon]")){
                if(!(cooldown.contains(player.getName()))){
                    if(NumberUtils.isNumber(sign.getLine(1))){
                        int reward = Integer.parseInt(sign.getLine(1));
                        Coins.getManager().addCoins(player.getName(), reward);
                        player.sendMessage(Main.getInstance().prefix + "You earned §6" + reward + "§7 coin(s) for completing the Parkour!");
                        this.executeOnComplete(player);
                    }else{
                        player.sendMessage(Main.getInstance().prefix + "This sign has a flaw!");
                    }
                }else{
                    player.sendMessage(Main.getInstance().prefix + "You need to wait until your cooldown time is up!");
                }
            }
        }
    }

    public void executeOnComplete(final Player player){
        cooldown.add(player.getName());
        Bukkit.broadcastMessage(Main.getInstance().prefix + "§6" + player.getName() + "§7 has just completed the parkour and earned some coins!");
        player.teleport(player.getWorld().getSpawnLocation().add(0, 10, 0));
        player.sendMessage(Main.getInstance().prefix + "You can complete the parkour again in §62 hours§7!");
        final int TIMES = 7;
        new BukkitRunnable(){
            int index = TIMES - 1;
            public void run(){
                if(index > 0){
                    index-=1;
                    Firework work = player.getWorld().spawn(player.getLocation(), Firework.class);
                    FireworkMeta meta = work.getFireworkMeta();
                    meta.setPower(1);
                    meta.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST).withColor(Color.RED).withFlicker().build());
                    work.setFireworkMeta(meta);
                }else{
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 5);

        new BukkitRunnable(){
            public void run(){
                cooldown.remove(player.getName());
            }
        }.runTaskLater(Main.getInstance(), 20*60*120);
    }
}
