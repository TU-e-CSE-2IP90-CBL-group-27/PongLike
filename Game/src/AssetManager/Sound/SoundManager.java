package src.AssetManager.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    public static void playSound(SoundEffectEnum sound) {
        try  {
            URL audio = new URL("file:" + sound.getPath());
            playSoundFile(audio);
        }
        catch (IOException exception) {
            System.out.println("Error while loading file to play sound" +  exception.getMessage());
            System.out.println(System.getProperty("user.dir"));
        }
    }

    public static void playSoundFile(URL audio) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.println(clip.isRunning());
        }
        catch (Exception exception) {
            System.out.println("Error while playing sound " +  exception.getMessage());
            System.out.println(new File(".").getAbsolutePath());
        }
    }
}
