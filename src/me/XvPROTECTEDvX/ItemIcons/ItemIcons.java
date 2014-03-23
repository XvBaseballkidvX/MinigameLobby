package me.XvPROTECTEDvX.ItemIcons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class ItemIcons {

    public static ItemStack HUB;
    public static ItemStack FACTIONS_ICON;
    public static ItemStack MINIGAME_LOBBY_ICON;
    public static ItemStack SKYBLOCK_ICON;
    public static ItemStack KITPVP_ICON;
    public static ItemStack SURVIVAL_ICON;
    public static ItemStack CREATIVE_ICON;
    public static ItemStack SKYWARS_ICON;
    public static ItemStack PRISON_ICON;
    public static ItemStack HAT_SHOP;

    public static ItemStack SERVER_SELECTOR;
    public static ItemStack STACKER_ON;
    public static ItemStack STACKER_OFF;

    public static ItemStack CLEAR_HAT;


    public static void registerItems(){

        //Server Icons

                HUB = setData(new ItemStack(Material.WORKBENCH), "§aHub", Arrays.asList(
                "§7Connect to the Hub!",
                ""
        ));

        FACTIONS_ICON = setData(new ItemStack(Material.TNT), "§aFactions", Arrays.asList(
                "§7Build your clan & dominate!",
                ""
        ));

        MINIGAME_LOBBY_ICON = setData(new ItemStack(Material.SNOW_BALL), "§aMinigame Lobby", Arrays.asList(
                "§7Play some Minigames!",
                ""
        ));

        SKYBLOCK_ICON = setData(new ItemStack(Material.GRASS), "§aSkyblock", Arrays.asList(
                "§7Create an island & survive!",
                ""
        ));

        KITPVP_ICON = setData(new ItemStack(Material.DIAMOND_SWORD), "§aKitPvP", Arrays.asList(
                "§7Constant PvP!",
                ""
        ));

        SURVIVAL_ICON = setData(new ItemStack(Material.WHEAT), "§aSurvival", Arrays.asList(
                "§7Survive & Build!",
                ""
        ));

        CREATIVE_ICON = setData(new ItemStack(Material.DIAMOND_BLOCK),"§aCreative", Arrays.asList(
                "§7Free Build Plots!",
                ""
        ));

        SKYWARS_ICON = setData(new ItemStack(Material.NETHER_STAR), "§aSkyWars" , Arrays.asList(
                "§7Island Battles!",
                ""
        ));

        PRISON_ICON = setData(new ItemStack(Material.IRON_FENCE), "§aPrison", Arrays.asList(
                "§7Rank up & Mine!",
                ""
        ));




        //Interact Items

        SERVER_SELECTOR = setData(new ItemStack(Material.COMPASS), "§aServer Selector §7(Right-Click)", Arrays.asList(
                "§7Right-Click to bring up the server menu!"
        ));

        HAT_SHOP = setData(new ItemStack(Material.CHEST), "§aHat Shop §7(Right-Click)", Arrays.asList(
                "§7Right-Click to open the Hat Shop!"
        ));

        STACKER_ON = setData(new ItemStack(Material.BLAZE_ROD), "§aStacker is turned on!", Arrays.asList(
                "§7Right-Click to toggle Stacker Status!"
        ));

        STACKER_OFF = setData(new ItemStack(Material.STICK), "§cStacker is turned off!", Arrays.asList(
                "§7Right-Click to toggle Stacker Status!"
        ));

        CLEAR_HAT = setData(new ItemStack(Material.WOOL), "§6Click to remove your Hat!", Arrays.asList("§7Remove your sexy hat!"));
    }

    public static ItemStack setData(ItemStack item, String name, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
