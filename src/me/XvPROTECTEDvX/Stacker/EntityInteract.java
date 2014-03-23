package me.XvPROTECTEDvX.Stacker;

import me.XvPROTECTEDvX.VyzonHub.Main;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


/**
 * Created by joshuabetz on 3/11/14.
 */
public class EntityInteract implements Listener {

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event){
        if(event.getRightClicked() instanceof Player){
            Player player = event.getPlayer();
            Player stacked = (Player) event.getRightClicked();
            String name = stacked.getName();
            Stacker s = Stacker.getManager();
            if(isPassenger(player)){
                event.setCancelled(true);
            }else{
                if(s.isPlaying(event.getPlayer().getName())){
                    if(s.isPlaying(name)){
                        event.getPlayer().setPassenger(stacked);
                    }else{
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("§8[§aVyzon§8] §7" + stacked.getName() + " is not participating in stacker!");
                    }
                }else{
                    event.getPlayer().sendMessage("§8[§aVyzon§8] §7You are not playing Stacker!");
                }
            }
        }
    }

    @EventHandler
    public void onPassengerThrow(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getPassenger() == null) return;
        if(player.getPassenger() instanceof Player){
            final Player pass = (Player) player.getPassenger();
            pass.getWorld().playEffect(pass.getLocation(), Effect.EXPLOSION_HUGE, 3);
            pass.getWorld().playSound(pass.getLocation(), Sound.EXPLODE, 3, 1);
            pass.getVehicle().eject();
            pass.setVelocity(player.getLocation().getDirection().multiply(.7).add(new Vector(0, .5, 0)));
            pass.setAllowFlight(true);
            pass.setFlying(false);
            player.sendMessage("§8[§aVyzon§8] §7You have just launched §6" + pass.getName() + "§7!");
            pass.sendMessage("§8[§aVyzon§8] §6" + player.getName() + "§7 has just launched you!");

            new BukkitRunnable(){
                public void run(){
                    pass.setAllowFlight(false);
                    pass.setFlying(false);
                }
            }.runTaskLater(Main.getInstance(), 20*1);
        }
    }

    public boolean isPassenger(Player player){
        return player.getVehicle() != null;
    }
}
