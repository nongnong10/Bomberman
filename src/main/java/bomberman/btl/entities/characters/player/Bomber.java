package bomberman.btl.entities.characters.player;

import bomberman.btl.Board;
import bomberman.btl.BombermanMain;
import bomberman.btl.entities.characters.Character;
import javafx.scene.image.Image;

public class Bomber extends Character {
    /**
     * now: trang thai hien tai
     * 1: up
     * 2: down
     * 3: left
     * 4: right
     */

    Image[] imagePlayer = {imgCollection.player_up_1.getFxImage(), imgCollection.player_up_2.getFxImage(),
            imgCollection.player_up_3.getFxImage(), imgCollection.player_down_1.getFxImage(),
            imgCollection.player_down_2.getFxImage(), imgCollection.player_down_3.getFxImage(),
            imgCollection.player_left_1.getFxImage(), imgCollection.player_left_2.getFxImage(), imgCollection.player_left_3.getFxImage(),
            imgCollection.player_right_1.getFxImage(), imgCollection.player_right_2.getFxImage(), imgCollection.player_right_3.getFxImage()};

    public Bomber(int row, int col) {
        super(row, col);
        this.image = imgCollection.bomberImg;
    }

    public Bomber(int row, int col, Image image) {
        super(row, col, image);
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean checkMove(int row, int col, Board board) {
        if (row <= 1 || row >= BombermanMain.HEIGHT || col <= 1 || col >= BombermanMain.WIDTH) return false;
        char tmp = board.map[row-1][col-1];
        if (tmp == '#' || tmp == '*') return false;
        return true;
    }

    public void update(int dir, int row, int col, Board board) {
        int nrow = row;
        int ncol = col;
        int xExp = x;
        int yExp = y;
        int xCanva = x;
        int yCanva = y;

        switch (dir){
            //up
            case 1: nrow--; yExp -= 32; break;
            //down
            case 2: nrow++; yExp += 32; break;
            //left
            case 3: ncol--; xExp -= 32; break;
            //right
            case 4: ncol++; xExp += 32; break;
        }

        System.out.println("now: " + x + " " + y);
        System.out.println("exp: " + xExp + " " + yExp);
        if (!checkMove(nrow, ncol, board))  return;

        int move = 11;
        for (int i = 0; i < 3; ++i) {
            switch (dir){
                //up
                case 1: yCanva -= move; break;
                //down
                case 2: yCanva += move; break;
                //left
                case 3: xCanva -= move; break;
                //right
                case 4: xCanva += move; break;
            }
            System.out.println((dir-1)*3+i);
            Image tmp = imagePlayer[(dir-1)*3+i];
            this.image = tmp;
            board.graphicsContext.drawImage(image,x,y);
//            x = xCanva;
//            y = yCanva;
            //setImage(imagePlayer[(dir-1)*3+i]);
            //this.render(board.graphicsContext);
            //board.graphicsContext.drawImage(imagePlayer[(dir-1)*3+i], xCanva, yCanva);
        }

        x = xExp;
        y = yExp;
        row = nrow;
        col = ncol;
        //this.render(board.graphicsContext);
        System.out.println("now: " + x + " " + y);
    }

    public int calcuteMove() {
        return 0;
    }
}
