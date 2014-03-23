package me.XvPROTECTEDvX.ServerUtil;

import me.XvPROTECTEDvX.Objects.Hat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by joshuabetz on 3/12/14.
 */
public class HatManager {

    private static HatManager manager = new HatManager();

    public static HatManager getInstance(){
        return manager;
    }

    ArrayList<Hat> hats = new ArrayList<Hat>();

    String basecommand = "pex user <user> add <permission>";

    public Hat MINER_CAP;
    public Hat COB_WEB;
    public Hat NATURE_CAP;
    public Hat SPACEMAN;
    public Hat BEACH_BUM;
    public Hat EXPLOSION_MAN;
    public Hat TREASURE_CHEST;
    public Hat HANDY_MAN;
    public Hat PUMPKIN_MAN;

    public ItemStack MINER;
    public ItemStack COB;
    public ItemStack NATURE;
    public ItemStack SPACE;
    public ItemStack BEACH;
    public ItemStack EXPLOSION;
    public ItemStack TREASURE;
    public ItemStack HANDY;
    public ItemStack PUMPKIN;
    public ItemStack CLEAR_HAT;

    int Miner = 100;
    int Cob = 1000;
    int Nature = 150;
    int Space = 250;
    int Beach = 350;
    int Explosion = 750;
    int Treasure = 1000;
    int Handy = 550;
    int Pumpkin = 750;

    public void registerHatItems(){
        MINER = setData(new ItemStack(Material.DIAMOND_ORE), "§aMiner Cap", Miner);
        COB = setData(new ItemStack(Material.WEB), "§aSpiderman Hat", Cob);
        NATURE = setData(new ItemStack(Material.LEAVES), "§aNature Hat", Nature);
        SPACE = setData(new ItemStack(Material.GLASS), "§aSpaceman Hat", Space);
        BEACH = setData(new ItemStack(Material.SAND), "§aBeach Bum Hat", Beach);
        EXPLOSION = setData(new ItemStack(Material.TNT), "§aGriefer Hat", Explosion);
        TREASURE = setData(new ItemStack(Material.CHEST), "§aTreasure Hat", Treasure);
        HANDY = setData(new ItemStack(Material.WORKBENCH), "§aWorking Man Hat", Handy);
        PUMPKIN = setData(new ItemStack(Material.PUMPKIN), "§aHalloween Mask", Pumpkin);
    }

    public void registerHats(){
        MINER_CAP = new Hat(MINER, "Miner Cap", Miner, "vyzon.hats.miner");
        COB_WEB = new Hat(COB, "Spiderman Hat", Cob, "vyzon.hats.cobweb");
        NATURE_CAP = new Hat(NATURE, "Nature Hat", Nature, "vyzon.hats.nature");
        SPACEMAN = new Hat(SPACE, "Spaceman Hat", Space, "vyzon.hats.spaceman");
        BEACH_BUM = new Hat(BEACH, "Beach Bum Hat", Beach, "vyzon.hats.beachbum");
        EXPLOSION_MAN = new Hat(EXPLOSION, "Griefer Hat", Explosion, "vyzon.hats.griefer");
        TREASURE_CHEST = new Hat(TREASURE, "Treasure Hat", Treasure, "vyzon.hats.treasure");
        HANDY_MAN = new Hat(HANDY, "Working Man Hat", Handy, "vyzon.hats.workingman");
        PUMPKIN_MAN = new Hat(PUMPKIN, "Halloween Mask", Pumpkin, "vyzon.hats.pumpkin");

        hats.add(MINER_CAP);
        hats.add(COB_WEB);
        hats.add(NATURE_CAP);
        hats.add(SPACEMAN);
        hats.add(BEACH_BUM);
        hats.add(EXPLOSION_MAN);
        hats.add(TREASURE_CHEST);
        hats.add(HANDY_MAN);
        hats.add(PUMPKIN_MAN);

        System.out.println("[Vyzon] Loaded " + hats.size() + " hat(s) successfully!");
    }

    public Hat getClickedHat(ItemStack clicked){
        ItemMeta meta = clicked.getItemMeta();
        for(Hat hat : hats){
            String replace = ChatColor.stripColor(meta.getDisplayName());
            if(hat.getName().equalsIgnoreCase(replace)){
                return hat;
            }
        }
        return null;
    }

    public List<Hat> getHats(){
        return hats;
    }

    public ItemStack setData(ItemStack item, String name, int cost){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList("§7Cost: §6" + cost));
        item.setItemMeta(meta);

        return item;
    }
}
