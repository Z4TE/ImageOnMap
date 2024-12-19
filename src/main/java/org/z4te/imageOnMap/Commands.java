package org.z4te.imageOnMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().info("This command can only be executed by players");
            return true;
        }

        if (args.length >= 1) {
            String imageURL = args[0];

            ItemStack mapItem = new ItemStack(Material.FILLED_MAP, 1);
            MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();

            MapView mapView = Bukkit.createMap(player.getWorld());

            Renderer mapRenderer = new Renderer(imageURL);

            mapView.addRenderer(mapRenderer);
            mapView.getRenderers().clear();

            assert mapMeta != null;
            mapMeta.setMapView(mapView);
            mapItem.setItemMeta(mapMeta);

            player.getInventory().addItem(mapItem);

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Too few arguments");
        }

        return true;
    }
}
