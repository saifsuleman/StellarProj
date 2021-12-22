package io.pulsarlabs.stellarproj.build;

import io.pulsarlabs.stellarproj.StellarProj;
import io.pulsarlabs.stellarproj.data.YamlConfig;
import io.pulsarlabs.stellarproj.selection.SelectionArea;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildManager {
    public void saveBuild(String filename, Location origin, SelectionArea selection) {
        // safe enough to run asynchronously for added performance
        StellarProj.getInstance().getServer().getScheduler().runTaskAsynchronously(StellarProj.getInstance(), () -> {
            YamlConfig config = new YamlConfig(filename);
            List<Map<String, Object>> blocks = new ArrayList<>();

            // By saving the build ordered by the y-axis, we know it will be placed from the bottom up. c:
            for (int y = selection.getMinY(); y <= selection.getMaxY(); y++) {
                for (int x = selection.getMinX(); x <= selection.getMaxX(); x++) {
                    for (int z = selection.getMinZ(); z <= selection.getMaxZ(); z++) {
                        Location location = new Location(origin.getWorld(), x, y, z);
                        Block block = origin.getWorld().getBlockAt(location);
                        Vector vec = location.clone().subtract(origin).toVector();
                        BlockContainer container = new BlockContainer(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ(), block);
                        blocks.add(container.serialize());
                    }
                }
            }
            config.getConfig().set("blocks", blocks);
            config.save();
        });
    }

    @SuppressWarnings("unchecked")
    public boolean placeBuild(String filename, Location origin) {
        YamlConfig config = new YamlConfig(filename);
        List<Map<String, Object>> blocks = (List<Map<String, Object>>) config.getConfig().get("blocks");
        if (blocks == null) return false;
        for (Map<String, Object> map : blocks) {
            BlockContainer container = new BlockContainer(map);
            Vector vec = new Vector(container.x, container.y, container.z);
            Location location = origin.clone().add(vec);
            location.getBlock().setType(container.material);
        }
        return true;
    }
}
