package io.pulsarlabs.stellarproj.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageUtil {
    public static String colour(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void msg(CommandSender sender, String s) {
        sender.sendMessage(colour(s));
    }
}
