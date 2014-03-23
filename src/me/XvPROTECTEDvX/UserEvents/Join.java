package me.XvPROTECTEDvX.UserEvents;

import me.XvPROTECTEDvX.ItemIcons.ItemIcons;
import me.XvPROTECTEDvX.Stacker.Stacker;
import me.XvPROTECTEDvX.VyzonHub.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Stacker.getManager().addPlaying(player.getName());
        if(!(player.hasPlayedBefore())){
            event.setJoinMessage("");
            Bukkit.broadcastMessage("§7§oWelcome to §a§oVyzon Network§7§o " + player.getName());
            this.execute(player);
        }else{
            event.setJoinMessage("");
            this.execute(player);
        }
    }

    public void execute(final Player player){
        Location loc = player.getWorld().getSpawnLocation();
        loc.add(0, 10, 0);
        player.teleport(loc);
        player.sendMessage("§7§oWelcome to the §a§oVyzon Network§7§o on this network we offer a variety\n§7§o of servers to choose from!" +
                " To connect to a §a§oserver§7§o\n§7§o simply open up the §a§oServer Selector Menu§7§o and click a server icon!");
        player.getInventory().setItem(0, ItemIcons.SERVER_SELECTOR);
        player.getInventory().setItem(1, ItemIcons.HAT_SHOP);
        player.getInventory().setItem(2, ItemIcons.STACKER_ON);

        player.setMaxHealth(40);
        player.setHealth(40);
        player.setFoodLevel(20);

        new BukkitRunnable(){
            public void run(){
                String line = "§9------------------------------------";
                player.sendMessage(line);
                player.sendMessage("§e       " + "Complete the Parkour to Earn Coins" + "       ");
                player.sendMessage("§e           " + "Use Coins to purchase hats!" + "           ");
                player.sendMessage(line);
            }
        }.runTaskLater(Main.getInstance(), 20*2);

        for(Player online : Bukkit.getOnlinePlayers()){
            online.showPlayer(player);
        }
        player.showPlayer(player);
    }
}
