package com.nasquicode.vitalcore.bukkit.commands;

import com.nasquicode.vitalcore.bukkit.Terminal;
import com.nasquicode.vitalcore.bukkit.mappers.PluginsMapper;
import com.nasquicode.vitalcore.bukkit.objects.VitalPlugin;
import com.nasquicode.vitalcore.bukkit.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VitalCore implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("vitalcore.use")) {
            sender.sendMessage(Color.string(Terminal.messagesFile.getString("no_permission")));
            return false;
        }

        if(args.length < 1) {
            for(String line : Color.list(Terminal.messagesFile.getStringList("main_command"))) {
                sender.sendMessage(line);
            }
            return true;
        }

        if(args[0].equalsIgnoreCase("list")) {
            if(PluginsMapper.getPlugins().size() == 0) {
                sender.sendMessage(Color.string(Terminal.messagesFile.getString("no_plugin_loaded")));
                return true;
            }

            for(String line : Color.list(Terminal.messagesFile.getStringList("loaded_plugins"))) {
                sender.sendMessage(line.replace("{amount}", String.valueOf(PluginsMapper.getPlugins().size())));
            }

            for(VitalPlugin plugin : PluginsMapper.getPlugins().values()) {
                sender.sendMessage(plugin.getName());
            }
            return true;
        }

        if(args[0].equalsIgnoreCase("info")) {
            if(args.length < 2) {
                sender.sendMessage(Color.string(Terminal.messagesFile.getString("plugin_info_command_usage")));
                return false;
            }

            if(!PluginsMapper.getPlugins().containsKey(args[1])) {
                sender.sendMessage(Color.string(Terminal.messagesFile.getString("plugin_not_loaded")));
                return true;
            }

            for(String line : Color.list(Terminal.messagesFile.getStringList("plugin_info"))) {
                sender.sendMessage(line.replace("{name}", PluginsMapper.getPlugins().get(args[1]).getName()).replace("{version}", PluginsMapper.getPlugins().get(args[1]).getPlugin().getDescription().getVersion()));
            }
        }
        return false;
    }
}