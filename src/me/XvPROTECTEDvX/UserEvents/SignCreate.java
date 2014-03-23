package me.XvPROTECTEDvX.UserEvents;

import me.XvPROTECTEDvX.VyzonHub.Main;
import net.minecraft.util.org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Created by joshuabetz on 3/13/14.
 */
public class SignCreate implements Listener {

    @EventHandler
    public void onSignCreate(SignChangeEvent event){
        if(event.getBlock().getState().getType() == Material.SIGN || event.getBlock().getState().getType() == Material.SIGN_POST || event.getBlock().getState().getType() == Material.WALL_SIGN){
            if(event.getLine(0).equalsIgnoreCase("[Vyzon]")){
                event.setLine(0, "§8[§aVyzon§8]");
                if(NumberUtils.isNumber(event.getLine(1))){
                    int reward = Integer.parseInt(event.getLine(1));
                    event.setLine(1, "" + reward);
                    event.getPlayer().sendMessage(Main.getInstance().prefix + "Sign created!");
                    event.getBlock().getState().update();
                }else{
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(Main.getInstance().prefix + "Wrong Format!");
                    Player player = event.getPlayer();
                    player.sendMessage("§cFORMAT:");
                    player.sendMessage("§cLine 1: [Vyzon]");
                    player.sendMessage("§cLine 2: INPUT_REWARD_AMOUNT]");
                    player.sendMessage("§cLeave Line 3 and 4 Blank!");
                    event.getBlock().breakNaturally();
                }
            }
        }
    }
}
