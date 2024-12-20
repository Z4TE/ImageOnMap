package org.z4te.imageOnMap;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public class Renderer extends MapRenderer {

    private final BufferedImage scaledImage;
    public Renderer(BufferedImage image) {
        this.scaledImage = image;
    }

    @Override
    public void render(@NotNull MapView map, MapCanvas canvas, @NotNull Player player) {
        canvas.drawImage(0,0, MapPalette.resizeImage(scaledImage));
    }
}
