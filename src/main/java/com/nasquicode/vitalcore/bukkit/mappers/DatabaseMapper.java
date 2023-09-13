package com.nasquicode.vitalcore.bukkit.mappers;

import com.nasquicode.vitalcore.bukkit.Terminal;
import com.nasquicode.vitalcore.bukkit.objects.Database;
import com.nasquicode.vitalcore.bukkit.objects.DatabaseInfo;
import com.nasquicode.vitalcore.bukkit.utils.Console;
import lombok.Getter;

import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseMapper {
    @Getter
    private static HashMap<String, Database> databases = new HashMap<String, Database>();

    public static void initializeDatabases() throws SQLException {
        databases.clear();
        for (String key : Terminal.config.getConfigurationSection("storage.databases").getKeys(false)) {
            Database database = new Database(new DatabaseInfo(
                    key,
                    Terminal.config.getString(String.format("storage.databases.%s.host", key)),
                    Terminal.config.getString(String.format("storage.databases.%s.database", key)),
                    Terminal.config.getString(String.format("storage.databases.%s.user", key)),
                    Terminal.config.getString(String.format("storage.databases.%s.password", key))
            ));
            databases.put(key, database);
        }
        Console.log(String.format("&a%s databases has been loaded.", String.valueOf(databases.size())));
    }
}
