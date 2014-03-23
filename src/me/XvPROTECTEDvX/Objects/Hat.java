package me.XvPROTECTEDvX.Objects;

import org.bukkit.inventory.ItemStack;

/**
 * Created by joshuabetz on 3/12/14.
 */
public class Hat {

    private ItemStack hat_material;
    private String name;
    private int cost;
    private String permission;

    public Hat(ItemStack hat_material, String name, int cost, String permission){
        this.hat_material = hat_material;
        this.name = name;
        this.cost = cost;
        this.permission = permission;
    }

    public String getName(){
        return this.name;
    }

    public Integer getCost(){
        return this.cost;
    }

    public String getPermission(){
        return this.permission;
    }

    public ItemStack getHat(){
        return this.hat_material;
    }
}
