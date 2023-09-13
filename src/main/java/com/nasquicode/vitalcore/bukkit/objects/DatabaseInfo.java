package com.nasquicode.vitalcore.bukkit.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class DatabaseInfo {
    private final String KEY;
    private final String HOST;
    private final String DATABASE;
    private final String USER;
    private final String PASSWORD;
}
