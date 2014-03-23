package me.XvPROTECTEDvX.Economy;

import me.XvPROTECTEDvX.VyzonHub.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * Created by joshuabetz on 3/12/14.
 */
public class Coins {

    private static Coins coins = new Coins();

    public static Coins getManager() {
        return coins;
    }

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    public void addCoins(String name, int amount_to_add) {
        if (map.containsKey(name)) {
            map.put(name, map.get(name) + amount_to_add);
            Main.getInstance().getConfig().set(name, getCoins(name));
        } else {
            map.put(name, amount_to_add);
            Main.getInstance().getConfig().set(name, getCoins(name));
        }
    }

    public int getCoins(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        } else {
            return 0;
        }
    }

    public boolean hasCoins(String name) {
        return map.containsKey(name);
    }

    public void removeCoins(String name, int cost) {
        map.put(name, getCoins(name) - cost);
        Main.getInstance().getConfig().set(name, getCoins(name));
    }

    public void loadConfig(){
        for(String keys : Main.getInstance().getConfig().getKeys(false)){
            int amount = Main.getInstance().getConfig().getInt(keys);
            map.put(keys, amount);
        }
        System.out.print("[Vyzon] Loaded Coins Configuration File!");
    }

    public void saveConfig(){
        Main.getInstance().saveConfig();
    }

    public void startSaveTask(){
        new BukkitRunnable(){
            public void run(){
                Main.getInstance().saveConfig();
            }
        }.runTaskTimer(Main.getInstance(), 20*2, 20*60);
    }
}
