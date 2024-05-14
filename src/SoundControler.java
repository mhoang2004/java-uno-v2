import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
public class SoundControler {
    static void soundClick() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File file = new File("../resources/sound/click.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }
    static void soundHit() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File file = new File("../resources/sound/hit.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }
    static void soundDraw() throws LineUnavailableException
    {
        File file = new File("../resources/sound/draw.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }
    static void soundVicroty() throws LineUnavailableException
    {
        File file = new File("../resources/sound/victory.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }
    static void soundLose() throws LineUnavailableException
    {
        File file = new File("../resources/sound/lose.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
       
    }
    static void soundUno() throws LineUnavailableException
    {
        File file = new File("../resources/sound/uno.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
       
    }
}

