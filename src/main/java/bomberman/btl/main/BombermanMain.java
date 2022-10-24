package bomberman.btl.main;
import javax.swing.JFrame;

public class BombermanMain {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Not resize window
        window.setResizable(false);
        window.setTitle("Bomber");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        //this window to be sized of the prefered size
        window.pack();

        //Not specify = center of screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}