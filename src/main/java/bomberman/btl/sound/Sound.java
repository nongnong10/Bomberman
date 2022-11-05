package bomberman.btl.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    public Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        //BACKGROUND
        soundURL[0] = getClass().getResource("/sound/background0.wav");
        soundURL[1] = getClass().getResource("/sound/background1.wav");

        //BOMB
        soundURL[2] = getClass().getResource("/sound/explosion.wav");

        //PLAYER DIE
        soundURL[3] = getClass().getResource("/sound/die.wav");

        //ENEMY DIE
        soundURL[4] = getClass().getResource("/sound/enemy_die.wav");
        soundURL[5] = getClass().getResource("/sound/enemy_die1.wav");
        soundURL[6] = getClass().getResource("/sound/enemy_die2.wav");

        //POWER UP
        soundURL[7] = getClass().getResource("/sound/power_up.wav");

        //GAMEOVER
        soundURL[8] = getClass().getResource("/sound/game_over.wav");

    }

    public void setFile(int ind) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[ind]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
