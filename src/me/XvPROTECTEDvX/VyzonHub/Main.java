package me.XvPROTECTEDvX.VyzonHub;

import me.XvPROTECTEDvX.Economy.Coins;
import me.XvPROTECTEDvX.Economy.CoinsCommand;
import me.XvPROTECTEDvX.Economy.CoinsListener;
import me.XvPROTECTEDvX.ItemIcons.ItemIcons;
import me.XvPROTECTEDvX.ItemIcons.ItemUtil;
import me.XvPROTECTEDvX.ItemIcons.LoreTask;
import me.XvPROTECTEDvX.Scoreboards.Board;
import me.XvPROTECTEDvX.ServerUtil.HatManager;
import me.XvPROTECTEDvX.ServerUtil.IconUtil;
import me.XvPROTECTEDvX.Stacker.EntityInteract;
import me.XvPROTECTEDvX.Stacker.Stacker;
import me.XvPROTECTEDvX.UserEvents.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class Main extends JavaPlugin{

    private static Main instance;

    public static Main getInstance(){
        return instance;
    }

    public String prefix = "§8[§aVyzon§8]§7 ";

    public void onEnable(){
        this.saveConfig();
        instance = this;
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new BlockEvents(), this);
        pm.registerEvents(new DamageEvent(), this);
        pm.registerEvents(new Interact(), this);
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new Join(), this);
        pm.registerEvents(new Leave(), this);
        pm.registerEvents(new HungerLossEvent(), this);
        pm.registerEvents(new EntityInteract(), this);
        pm.registerEvents(new DropEvent(), this);
        pm.registerEvents(new CoinsListener(), this);
        pm.registerEvents(new SignCreate(), this);
        this.getCommand("coins").setExecutor(new CoinsCommand());

        ItemIcons.registerItems();
        IconUtil.getManager().registerServerIcons();
        ItemUtil.getManager().addItems();
        LoreTask.getTasks().startTask();
        HatManager.getInstance().registerHatItems();
        HatManager.getInstance().registerHats();
        Board.startScoreboardTask();

        Coins.getManager().loadConfig();

        this.startAutoSaveTask();

        for(Player player : Bukkit.getOnlinePlayers()){
            Stacker.getManager().addPlaying(player.getName());
        }
    }

    public void onDisable(){
        this.saveConfig();
    }

    public void startAutoSaveTask(){
        new BukkitRunnable(){
            public void run(){
                System.out.println("[Vyzon] Saving Coins Config...");
                saveConfig();
                System.out.println("[Vyzon] Saving Complete!");
            }
        }.runTaskTimer(Main.getInstance(), 20*10, 20*60*5);
    }
}
