package com.nasquicode.vitalcore.bukkit.api;

import com.nasquicode.vitalcore.bukkit.mappers.DatabaseMapper;
import com.nasquicode.vitalcore.bukkit.mappers.PluginsMapper;
import com.nasquicode.vitalcore.bukkit.objects.Database;
import com.nasquicode.vitalcore.bukkit.objects.VitalPlugin;
import com.nasquicode.vitalcore.bukkit.utils.Request;

public class VitalCoreAPI {
    public Database getDatabase(String identifier) {
        if(DatabaseMapper.getDatabases().containsKey(identifier)) return DatabaseMapper.getDatabases().get(identifier);
        return null;
    }
    public Request performHttpRequest(String url) {
        return new Request(url);
    }
    public VitalPlugin getVitalPlugin(String name) {
        if(PluginsMapper.getPlugins().containsKey(name)) return PluginsMapper.getPlugins().get(name);
        return null;
    }
}