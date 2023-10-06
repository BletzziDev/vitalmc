package com.nasquicode.vitalcore.bukkit;

import com.nasquicode.vitalcore.bukkit.api.VitalCoreAPI;
import com.nasquicode.vitalcore.bukkit.commands.VitalCore;
import com.nasquicode.vitalcore.bukkit.licensing.LicenseServer;
import com.nasquicode.vitalcore.bukkit.mappers.DatabaseMapper;
import com.nasquicode.vitalcore.bukkit.mappers.PluginsMapper;
import com.nasquicode.vitalcore.bukkit.misc.Constants;
import com.nasquicode.vitalcore.bukkit.objects.Database;
import com.nasquicode.vitalcore.bukkit.utils.Console;
import com.nasquicode.vitalcore.bukkit.utils.CustomFileConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;

public final class Terminal extends JavaPlugin {

    public static Terminal instance;
    public static VitalCoreAPI api = new VitalCoreAPI();
    public static CustomFileConfiguration config;
    public static CustomFileConfiguration messagesFile;

    @Override
    public void onEnable() {
        Terminal.instance = this;

        Console.log("&eLoading files...");
        try {
            config = new CustomFileConfiguration(Constants.configFile, this);
            Console.log(String.format("&eThe file &b%s &ehas been loaded.", Constants.configFile));
            messagesFile = new CustomFileConfiguration(Constants.messagesFile, this);
            Console.log(String.format("&eThe file &b%s &ehas been loaded.", Constants.messagesFile));

            Console.log("&aAll files has been loaded.");
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        if(!LicenseServer.checkLicense(config.getString("license"))) {
            Console.log("&cInvalid license");
            unloadPlugins();
            return;
        }

        Console.log("&aValid license");

        Console.log("&eInitializing databases...");
        try {
            DatabaseMapper.initializeDatabases();
        } catch (SQLException e) {
            Console.log("&cAn error occured while initializing databases.");
            throw new RuntimeException(e);
        }

        Console.log("&eRegistering commands and listeners...");
        getCommand("vitalcore").setExecutor(new VitalCore());
        Console.log("&aAll commands and listeners has been registered.");

        Console.log("&eLoading Vital Plugins...");
        PluginsMapper.load();
        Console.log(String.format("&b%s vital plugins has been loaded.", String.valueOf(PluginsMapper.getPlugins().size())));


        Console.log("&aPlugin initialized!");
    }

    @Override
    public void onDisable() {
        for(Database database : DatabaseMapper.getDatabases().values()) {
            database.closeConnection();
        }
        Console.log("&cPlugin disabled!");
    }
    public void unloadPlugins() {
        Console.log("");
        Console.log("");
        Console.log("");
        Console.log("&cAutomatically shutting down the server in 30 seconds.");
        Console.log("");
        Console.log("");
        Console.log("");
        try {
            Thread.sleep(30000);
            Bukkit.getServer().shutdown();Bukkit.getServer().shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public VitalCoreAPI getAPI() {
        return api;
    }
}