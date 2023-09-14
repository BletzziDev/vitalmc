package com.nasquicode.vitalcore.bukkit.mappers;

import com.nasquicode.vitalcore.bukkit.Terminal;
import com.nasquicode.vitalcore.bukkit.misc.Constants;
import com.nasquicode.vitalcore.bukkit.objects.VitalPlugin;
import com.nasquicode.vitalcore.bukkit.utils.Console;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class PluginsMapper {
    @Getter
    private static HashMap<String, VitalPlugin> plugins = new HashMap<>();

    public static void load() {
        plugins.clear();
        for(String key : Constants.vitalPlugins) {
            if(Bukkit.getPluginManager().getPlugin(key) != null) {
                VitalPlugin plugin = new VitalPlugin(
                        key, Bukkit.getPluginManager().getPlugin(key)
                );
                plugins.put(key,plugin);
            }
        }
    }
}