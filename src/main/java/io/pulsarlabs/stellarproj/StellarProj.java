package io.pulsarlabs.stellarproj;

import io.pulsarlabs.stellarproj.build.BuildManager;
import io.pulsarlabs.stellarproj.command.CommandPlaceBuild;
import io.pulsarlabs.stellarproj.command.CommandSaveBuild;
import io.pulsarlabs.stellarproj.selection.SelectionManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StellarProj extends JavaPlugin {
    private static StellarProj instance;

    private SelectionManager selectionManager;
    private BuildManager buildManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.buildManager = new BuildManager();
        this.selectionManager = new SelectionManager();

        getCommand("getwand").setExecutor(this.selectionManager);
        getCommand("placebuild").setExecutor(new CommandPlaceBuild());
        getCommand("savebuild").setExecutor(new CommandSaveBuild());

        this.getServer().getPluginManager().registerEvents(selectionManager, this);
    }

    public SelectionManager getSelectionManager() {
        return selectionManager;
    }

    public BuildManager getBuildManager() {
        return buildManager;
    }

    public static StellarProj getInstance() {
        return instance;
    }
}
