package io.pulsarlabs.stellarproj.selection;

import org.bukkit.Location;

public class SelectionArea {
    private final Location p1;
    private final Location p2;

    public SelectionArea(Location p1, Location p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getMinX() {
        return Math.min(p1.getBlockX(), p2.getBlockX());
    }

    public int getMinY() {
        return Math.min(p1.getBlockY(), p2.getBlockY());
    }

    public int getMinZ() {
        return Math.min(p1.getBlockZ(), p2.getBlockZ());
    }

    public int getMaxX() {
        return Math.max(p1.getBlockX(), p2.getBlockX());
    }

    public int getMaxY() {
        return Math.max(p1.getBlockY(), p2.getBlockY());
    }

    public int getMaxZ() {
        return Math.max(p1.getBlockZ(), p2.getBlockZ());
    }
}
