package me.XvPROTECTEDvX.UserEvents;

import me.XvPROTECTEDvX.Stacker.Stacker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class Leave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        event.setQuitMessage("");
        Player player = event.getPlayer();
        Stacker.getManager().removePlaying(event.getPlayer().getName());
        for(Player online : Bukkit.getOnlinePlayers()){
            online.showPlayer(player);
        }
        player.showPlayer(player);
    }

    @EventHandler
    public void onKick(PlayerKickEvent event){
        event.setLeaveMessage("");
        Player player = event.getPlayer();
        Stacker.getManager().removePlaying(event.getPlayer().getName());
        for(Player online : Bukkit.getOnlinePlayers()){
            online.showPlayer(player);
        }
        player.showPlayer(player);
    }
}
