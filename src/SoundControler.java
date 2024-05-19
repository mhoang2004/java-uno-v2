import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundControler {
    static boolean isOn= true;
    static void  setIsON(boolean isON)
    {
       isOn = isON;
    }
    static void soundClick() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("../resources/sounds/click.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if(!isOn)
            {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
       
    }

    static void soundHit() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("../resources/sounds/hit.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if(!isOn)
            {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }

    static void soundDraw() throws LineUnavailableException {
        File file = new File("../resources/sounds/draw.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if(!isOn)
            {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }

    static void soundVicroty() throws LineUnavailableException {
        File file = new File("../resources/sounds/victory.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if(!isOn)
            {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }

    static void soundLose() throws LineUnavailableException {
        File file = new File("../resources/sounds/lose.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if(!isOn)
            {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }

    }

    static void soundUno() throws LineUnavailableException {
        File file = new File("../resources/sounds/uno.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if(!isOn)
            {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }

    }
}
