package org.z4te.imageOnMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageFormatter {

    public static BufferedImage formatImage(String imageUrl, int rows, int cols) {

        try {
            BufferedImage originalImage = ImageIO.read(new URL(imageUrl));

            int gridSize = Math.min(originalImage.getWidth() / cols, originalImage.getHeight() /rows);
            int newWidth = gridSize * cols;
            int newHeight = gridSize * rows;

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            resizedImage.createGraphics().drawImage(originalImage, BufferedImage.SCALE_FAST, 0, newWidth, newHeight, null);

            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
