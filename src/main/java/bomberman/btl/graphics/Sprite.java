package bomberman.btl.graphics;

import bomberman.btl.main.BombermanMain;
import javafx.scene.image.*;

public class Sprite {
    public static final int DEFAULT_SIZE = BombermanMain.DEFAULT_SIZE;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public final int SIZE;
    /**
     * Mảng pixels chứa các pixel của Sprite.
     */
    public int[] pixels;
    protected int realWidth;
    protected int realHeight;
    /**
     * Tọa độ của Sprite trong Sprite Sheet.
     */
    private int x, y;
    private SpriteSheet sheet;

    /**
     * Chứa thông tin một Sprite.
     *
     * @param size  kích thước
     * @param row   hàng trong Sprite Sheet
     * @param col   cột trong Sprite Sheet
     * @param sheet Sprite Sheet
     * @param realWidth chiều rộng thực
     * @param realHeight chiều cao thực
     */
    public Sprite(int size, int row, int col, SpriteSheet sheet, int realWidth, int realHeight) {
        SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.x = col * size - size;
        this.y = row * size - size;
        this.sheet = sheet;
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        load();
    }

    /**
     * Lấy các pixel của Sprite từ Sprite Sheet.
     */
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int pos = y * SIZE + x;
                int posSheet = (this.y + y) * sheet.SIZE + (this.x + x);
                pixels[pos] = sheet.pixels[posSheet];

                //pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    public Image getFxImage() {
        //Tạo một WritableImage có kích cỡ SIZE * SIZE.
        WritableImage wr = new WritableImage(SIZE, SIZE);

        //Trao quyền write các pixel của image cho.
        PixelWriter pw = wr.getPixelWriter();

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (this.pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    //Nếu là nền thì gán color = 0.
                    pw.setArgb(x, y, 0);
                } else {
                    //Ngược lại thì gán color = màu của pixel.
                    pw.setArgb(x, y, this.pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    /**
     * Biến ảnh input thành ảnh có kích thích lớn gấp scaleFactor lần so với Default.
     */
    private Image resample(Image input, int scaleFactor) {

        //Tạo ảnh output với kích cỡ gấp S lần so với input.
        WritableImage output = new WritableImage(realWidth * scaleFactor,
                realHeight * scaleFactor);

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < realHeight; y++) {
            for (int x = 0; x < realWidth; x++) {
                final int argb = reader.getArgb(x, y);
                //Với mỗi pixel của input thì tạo một vùng mang màu pixel ấy kích cỡ S*S.
                //Vị trí x,y hiện tại.
                int posx = x * scaleFactor;
                int posy = y * scaleFactor;
                for (int dy = 0; dy < scaleFactor; dy++) {
                    for (int dx = 0; dx < scaleFactor; dx++) {
                        writer.setArgb(posx + dx, posy + dy, argb);
                    }
                }
            }
        }

        return output;
    }
}
