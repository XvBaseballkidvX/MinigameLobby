package me.XvPROTECTEDvX.Scoreboards;

import me.XvPROTECTEDvX.Economy.Coins;
import me.XvPROTECTEDvX.VyzonHub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * Created by joshuabetz on 3/13/14.
 */
public class Board {

    public static void startScoreboardTask(){
        new BukkitRunnable(){
            public void run(){
                for(Player player : Bukkit.getOnlinePlayers()){
                    setScoreboardToPlayer(player);
                }
            }
        }.runTaskTimer(Main.getInstance(), 20*5, 40);
    }



    public static void setScoreboardToPlayer(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("coins", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§a§lVyzon");
        int bal = Coins.getManager().getCoins(player.getName());
        obj.getScore(Bukkit.getOfflinePlayer("§6Coins:")).setScore(bal);
        player.setScoreboard(board);
    }
}
