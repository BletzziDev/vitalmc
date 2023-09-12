package com.nasquicode.vitalcore.bukkit;

import com.nasquicode.vitalcore.bukkit.utils.Console;
import org.bukkit.plugin.java.JavaPlugin;

public final class Terminal extends JavaPlugin {

    @Override
    public void onEnable() {
        Console.log("&aPlugin initialized!");
    }

    @Override
    public void onDisable() {
        Console.log("&cPlugin disabled!");
    }
}
