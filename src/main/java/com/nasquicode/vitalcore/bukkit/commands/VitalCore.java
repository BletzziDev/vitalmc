package com.nasquicode.vitalcore.bukkit.commands;

import com.nasquicode.vitalcore.bukkit.Terminal;
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
            return false;
        }
        return false;
    }
}