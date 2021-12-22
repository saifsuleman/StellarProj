package io.pulsarlabs.stellarproj.command;

import io.pulsarlabs.stellarproj.StellarProj;
import io.pulsarlabs.stellarproj.selection.SelectionArea;
import io.pulsarlabs.stellarproj.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSaveBuild implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtil.msg(sender, "&cYou must be a player to do that!");
            return true;
        }

        if (args.length == 0) {
            MessageUtil.msg(sender, "&cUsage: /savebuild <build name>");
            return true;
        }

        Player player = (Player) sender;
        SelectionArea selection = StellarProj.getInstance().getSelectionManager().getSelection(player);
        if (selection == null) {
            MessageUtil.msg(sender, "&cYou do not have an area selected! Select one with the selection wand!");
            return true;
        }

        StellarProj.getInstance().getBuildManager().saveBuild(args[0], player.getLocation(), selection);
        MessageUtil.msg(sender, "&aDone!");
        return true;
    }
}
