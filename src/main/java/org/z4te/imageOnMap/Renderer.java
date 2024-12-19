package org.z4te.imageOnMap;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Renderer extends MapRenderer {

    private final String ImageURL;
    public Renderer(String url) {
        this.ImageURL = url;
    }

    @Override
    public void render(@NotNull MapView map, MapCanvas canvas, @NotNull Player player) {

        try {
            URL url = new URL(this.ImageURL);
            BufferedImage image = ImageIO.read(url);
            canvas.drawImage(0,0, MapPalette.resizeImage(image));
        } catch(IOException e) {
            e.printStackTrace();
        }


    }
}
