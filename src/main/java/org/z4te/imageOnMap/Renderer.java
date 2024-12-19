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
    @Override
    public void render(@NotNull MapView map, MapCanvas canvas, @NotNull Player player) {

        try {
            URL url = new URL("https://cdn.discordapp.com/attachments/617581019238891520/1313117974260875274/ERxLJlJVUAAwelq.png?ex=67636729&is=676215a9&hm=dda7876594845e9cfecbe74fa7d39e40664eb29429c5fa7afc46cb3b3968c718&");
            BufferedImage image = ImageIO.read(url);
            canvas.drawImage(0,0, MapPalette.resizeImage(image));
        } catch(IOException e) {
            e.printStackTrace();
        }


    }
}
