package me.XvPROTECTEDvX.UserEvents;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class DamageEvent implements Listener {


    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            if(event.getDamager() instanceof Player){
                event.setCancelled(true);
            }
        }
    }

    public void playPopEffect(Location location){
        World w = location.getWorld();

        for(int i = 0; i < 10; i++){
            w.playEffect(location, Effect.FLAME, 3);
        }
        for(int i = 0; i < 3; i ++){
            for(int z = 0; z < 3; z++){
                w.playEffect(location, Effect.LAVA_POP, 3);
            }
        }
        w.playEffect(location.add(0, 1, 0), Effect.HEART, 3);
        w.playSound(location, Sound.ITEM_PICKUP, 2, 1);
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
            event.setCancelled(true);
        }
    }
}
