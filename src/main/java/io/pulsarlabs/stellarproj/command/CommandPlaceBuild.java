package io.pulsarlabs.stellarproj.command;

import io.pulsarlabs.stellarproj.StellarProj;
import io.pulsarlabs.stellarproj.build.BuildManager;
import io.pulsarlabs.stellarproj.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPlaceBuild implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtil.msg(sender, "&cYou must be a player to do that!");
            return true;
        }

        if (args.length == 0) {
            MessageUtil.msg(sender, "&cUsage: /placebuild <build name>");
            return true;
        }

        Player player = (Player)sender;
        BuildManager buildManager = StellarProj.getInstance().getBuildManager();
        if (!buildManager.placeBuild(args[0], player.getLocation())) {
            MessageUtil.msg(sender, "&cThere is no build saved under that name!");
            return true;
        }
        MessageUtil.msg(sender, "&adone!");
        return true;
    }
}
