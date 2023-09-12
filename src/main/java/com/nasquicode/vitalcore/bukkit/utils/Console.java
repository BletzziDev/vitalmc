package com.nasquicode.vitalcore.bukkit.utils;

import org.bukkit.Bukkit;

public class Console {
    private static String prefix = "&b[VitalCore]";

    public static void log(String content) {
        Bukkit.getConsoleSender().sendMessage(Color.string(prefix+"&f "+content));
    }
}
