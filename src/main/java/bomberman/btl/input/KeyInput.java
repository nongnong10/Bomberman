package bomberman.btl.input;

import bomberman.btl.entity.Player;
import bomberman.btl.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    public GamePanel gamePanel;
    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean bombPressed = false;
    public boolean shootPressed = false;

    public KeyInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (gamePanel.gameState == gamePanel.titleState) {
            titleKeyState(keyCode);
        } else if (gamePanel.gameState == gamePanel.playState) {
            playKeyState(keyCode);
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseKeyState(keyCode);
        } else if (gamePanel.gameState == gamePanel.gameoverState) {
            gameOverKeyState(keyCode);
        }
    }

    public void titleKeyState(int code) {
        if (code == KeyEvent.VK_UP) {
            gamePanel.ui.commandNum--;
            if (gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = 2;
            }
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            gamePanel.ui.commandNum++;
            if (gamePanel.ui.commandNum > 2) {
                gamePanel.ui.commandNum = 0;
            }
            downPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.commandNum == 0) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.level = 1;
                gamePanel.tileManager.loadMap(gamePanel.level);
                gamePanel.assetSetter.setAll();
                gamePanel.ui.commandNum = -1;
            } else if (gamePanel.ui.commandNum == 1) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.assetSetter.setAll();
                gamePanel.ui.commandNum = -1;
            } else if (gamePanel.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void playKeyState(int code) {
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            bombPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.pauseState;
        }
        if (code == KeyEvent.VK_A) {
            shootPressed = true;
        }
    }

    public void pauseKeyState(int code) {
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            bombPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void gameOverKeyState(int code) {
        if (code == KeyEvent.VK_UP) {
            gamePanel.ui.commandNum--;
            if (gamePanel.ui.commandNum < 0) {
                gamePanel.ui.commandNum = 1;
            }
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            gamePanel.ui.commandNum++;
            if (gamePanel.ui.commandNum > 1) {
                gamePanel.ui.commandNum = 0;
            }
            downPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.commandNum == 0) {
                gamePanel.ui.commandNum = -1;
                gamePanel.gameState = gamePanel.playState;
                gamePanel.retry();
            }
            if (gamePanel.ui.commandNum == 1) {
                gamePanel.ui.commandNum = -1;
                gamePanel.player = new Player(this.gamePanel, this);
                gamePanel.gameState = gamePanel.titleState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                bombPressed = false;
                break;
            case KeyEvent.VK_A:
                shootPressed = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
