package me.XvPROTECTEDvX.ItemIcons;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class ItemUtil {

    private static ItemUtil u = new ItemUtil();
    public static ItemUtil getManager(){
        return u;
    }

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    public void addItems(){
        items.add(ItemIcons.CREATIVE_ICON);
        items.add(ItemIcons.FACTIONS_ICON);
        items.add(ItemIcons.KITPVP_ICON);
        items.add(ItemIcons.PRISON_ICON);
        items.add(ItemIcons.SKYBLOCK_ICON);
        items.add(ItemIcons.KITPVP_ICON);
        items.add(ItemIcons.MINIGAME_LOBBY_ICON);
        items.add(ItemIcons.SKYWARS_ICON);
        items.add(ItemIcons.SURVIVAL_ICON);
        items.add(ItemIcons.HUB);
    }

    public ArrayList<ItemStack> getItems(){
        return items;
    }
}
