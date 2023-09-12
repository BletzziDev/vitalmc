package com.nasquicode.vitalcore.bukkit.utils;

import java.util.List;

public class Color {
    public static String string(String text) {
        return text.replace("&","§");
    }
    public static List<String> list(List<String> list) {
        for(int i=0;i<list.size();i++) {
            list.set(i,list.get(i).replace("&","§"));
        }
        return list;
    }
}
