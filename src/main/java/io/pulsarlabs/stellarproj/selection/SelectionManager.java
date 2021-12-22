package io.pulsarlabs.stellarproj.selection;

import io.pulsarlabs.stellarproj.util.MessageUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SelectionManager implements Listener, CommandExecutor {
    private final ItemStack itemStack;
    private final Map<UUID, Location> selectingPoints;
    private final Map<UUID, SelectionArea> selections;

    public SelectionManager() {
        this.itemStack = this.wandItem();
        this.selectingPoints = new HashMap<>();
        this.selections = new HashMap<>();
    }

    private ItemStack wandItem() {
        ItemStack itemStack = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.text("Selection Wand").color(TextColor.color(61, 157, 242)));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to do that!");
            return true;
        }

        Player player = (Player) sender;
        player.getInventory().addItem(this.wandItem());
        sender.sendMessage("You now have the wand item!");

        return true;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!this.itemStack.isSimilar(e.getItem())) return;
        Block clicked = e.getClickedBlock();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        e.setCancelled(true);

        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        if (selectingPoints.containsKey(uuid)) {
            Location p1 = selectingPoints.remove(uuid);
            Location p2 = clicked.getLocation();
            SelectionArea selection = new SelectionArea(p1, p2);
            selections.put(uuid, selection);
            MessageUtil.msg(player, "&aYou have made your selection!");
        } else {
            selectingPoints.put(uuid, clicked.getLocation());
            MessageUtil.msg(player, "&aYou have made a first point in your selection! Do it again on the opposite corner duh!");
        }
    }

    public SelectionArea getSelection(Player player) {
        UUID uuid = player.getUniqueId();
        return selections.getOrDefault(uuid, null);
    }
}
