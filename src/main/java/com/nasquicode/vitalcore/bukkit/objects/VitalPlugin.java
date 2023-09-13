package com.nasquicode.vitalcore.bukkit.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

@AllArgsConstructor @Getter
public class VitalPlugin {
    private String name;
    private Plugin plugin;
}