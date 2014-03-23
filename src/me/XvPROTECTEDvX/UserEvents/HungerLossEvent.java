package me.XvPROTECTEDvX.UserEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by joshuabetz on 3/11/14.
 */
public class HungerLossEvent implements Listener {

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}
