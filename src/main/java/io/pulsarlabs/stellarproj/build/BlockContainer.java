package io.pulsarlabs.stellarproj.build;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BlockContainer implements ConfigurationSerializable {
    public final int x;
    public final int y;
    public final int z;
    public final Material material;

    public BlockContainer(int x, int y, int z, Block block) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = block.getType();
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", x);
        map.put("y", y);
        map.put("z", z);
        map.put("material", material.name());
        return map;
    }

    public BlockContainer(Map<String, Object> map) {
        this.x = (int) map.get("x");
        this.y = (int) map.get("y");
        this.z = (int) map.get("z");
        this.material = Material.valueOf((String) map.get("material"));
    }
}
