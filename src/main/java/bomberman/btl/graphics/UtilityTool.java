package bomberman.btl.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contain some helpful fuction.
 */
public class UtilityTool {

    /**
     * Scale Image.
     */
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(original, 0, 0, width, height, null);
        graphics2D.dispose();
        return scaledImage;
    }
}
