package bomberman.btl.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Chứa tất cả các Sprite
 * và biến Sprite Sheet thành một mảng pixels.
 */
public class SpriteSheet {
    public final int SIZE;
    public int[] pixels;
    public String path;
    private BufferedImage image;

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            image = ImageIO.read(this.getClass().getResource(this.path));
            //getRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize)
            //Returns an array of integer pixels in the default RGB color model (TYPE_INT_ARGB)
            //and default sRGB color space, from a portion of the image data.
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
