package me.XvPROTECTEDvX.UserEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by joshuabetz on 3/11/14.
 */
public class DropEvent implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(event.getPlayer().hasPermission("vyzon.drop.items")){
            event.setCancelled(false);
        }else{
            event.setCancelled(true);
        }
    }
}
