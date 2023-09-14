package com.nasquicode.vitalcore.bukkit.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class CustomStack {
    public static ItemStack get(String name) {
        if(name.length() > 40) {
            // Custom Skull
            return Heads.getSkull(name);
        }
        // ItemStack
        String name_spllited[] = name.split(":");
        String material = name_spllited[0];
        byte data = Byte.parseByte(name_spllited[1]);
        MaterialData materialData = new MaterialData(Material.matchMaterial(material) ,data);
        ItemStack stack = new ItemStack(Material.matchMaterial(material));
        stack.setData(materialData);
        return stack;
    }
}