package me.XvPROTECTEDvX.ServerUtil;

import me.XvPROTECTEDvX.ItemIcons.ItemIcons;
import me.XvPROTECTEDvX.Objects.ServerIcon;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by joshuabetz on 3/10/14.
 */
public class IconUtil {

    private static IconUtil i = new IconUtil();

    public static IconUtil getManager(){
        return i;
    }

    ServerIcon HUB, PRISON, SURVIVAL, SKYBLOCK, KITPVP, FACTIONS, LOBBY, CREATIVE, SKYWARS;

    ArrayList<ServerIcon> icons = new ArrayList<ServerIcon>();

    public void registerServerIcons(){
        HUB = new ServerIcon("Hub");
        PRISON = new ServerIcon("Prison");
        SURVIVAL = new ServerIcon("Survival");
        SKYBLOCK = new ServerIcon("SkyBlock");
        KITPVP = new ServerIcon("KitPvP");
        FACTIONS = new ServerIcon("Factions");
        LOBBY = new ServerIcon("lobby");
        CREATIVE = new ServerIcon("Creative");
        SKYWARS = new ServerIcon("SkyWars");

        icons.add(HUB);
        icons.add(PRISON);
        icons.add(SURVIVAL);
        icons.add(SKYBLOCK);
        icons.add(KITPVP);
        icons.add(FACTIONS);
        icons.add(LOBBY);
        icons.add(CREATIVE);
        icons.add(SKYWARS);

        System.out.println("[Vyzon] Loaded " + icons.size() + " server icon(s)!");
    }

    public ServerIcon getClickedServerIcon(ItemStack clicked){
        String name = clicked.getItemMeta().getDisplayName();
        String replace = ChatColor.stripColor(name);
        for(ServerIcon key : icons){
            if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase(ItemIcons.MINIGAME_LOBBY_ICON.getItemMeta().getDisplayName())){
                return LOBBY;
            }else if(key.getName().equalsIgnoreCase(replace)){
                return key;
            }
        }
        return null;
    }
}

