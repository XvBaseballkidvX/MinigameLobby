package me.XvPROTECTEDvX.UserEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class BlockEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(event.getPlayer().hasPermission("vyzon.build")){
            event.setCancelled(false);
        }else{
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(event.getPlayer().hasPermission("vyzon.build")){
            event.setCancelled(false);
        }else{
            event.setCancelled(true);
        }
    }
}
