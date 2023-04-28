package main.java.core.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    Clip clip;
    Clip BackroundMusic;
    FloatControl fc;
    public boolean clipCompleted;
    URL[] soundpaths = new URL[6];
    public SoundPlayer(){
        soundpaths[0] = getClass().getResource("/main/ressources/sounds/CoinPickupSound.wav");
        soundpaths[1] = getClass().getResource("/main/ressources/sounds/DeathSound.wav");
        soundpaths[2] = getClass().getResource("/main/ressources/sounds/ooh.wav");
        soundpaths[3] = getClass().getResource("/main/ressources/sounds/WildschweinDeathSound.wav");
        soundpaths[4] = getClass().getResource("/main/ressources/sounds/BackroundMusic.wav");
        soundpaths[5] = getClass().getResource("/main/ressources/sounds/PunnshSound.wav");

        clipCompleted = false;
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundpaths[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void Playsound(){
        clipCompleted = false;
        clip.start();
        while (clip.isActive()){
            if(!clip.isActive()){
                clipCompleted = true;
            }
        }
    }

    public void PlayBackround(){
        try {
            AudioInputStream ais2 = AudioSystem.getAudioInputStream(soundpaths[4]);
            BackroundMusic = AudioSystem.getClip();
            BackroundMusic.open(ais2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        fc = (FloatControl) BackroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(6);

        BackroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
