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

import java.awt.image.BufferedImage;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().info("This command can only be executed by players");
            return true;
        }

        if (args.length >= 1) {
            String imageURL = args[2];

            try {
                int cols = Integer.parseInt(args[0]);
                int rows = Integer.parseInt(args[1]);

                BufferedImage scaledImage =  ImageFormatter.formatImage(imageURL, rows, cols);

                assert scaledImage != null;
                int chunkWidth = scaledImage.getWidth() / cols;
                int chunkHeight = scaledImage.getHeight() / rows;

                for (int y = 0; y < rows; y++) {
                    for (int x = 0; x < cols; x++) {
                        ItemStack mapItem = new ItemStack(Material.FILLED_MAP, 1);
                        MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
                        MapView mapView = Bukkit.createMap(player.getWorld());

                        BufferedImage subImage = scaledImage.getSubimage(x * chunkWidth, y * chunkHeight, chunkWidth, chunkHeight);

                        Renderer mapRenderer = new Renderer(subImage);

                        mapView.addRenderer(mapRenderer);
                        mapView.getRenderers().clear();

                        String name = String.format("(%d, %d)", x, y);

                        assert mapMeta != null;
                        mapMeta.setMapView(mapView);
                        mapMeta.setItemName(name);
                        mapItem.setItemMeta(mapMeta);

                        player.getInventory().addItem(mapItem);
                    }
                }
            } catch (NumberFormatException e) {
                sender.sendMessage( ChatColor.RED + "Invalid URL");
                e.printStackTrace();
            }

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Too few arguments");
        }

        return true;
    }
}
