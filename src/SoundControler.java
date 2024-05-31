import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.Timer;

public class SoundControler {
    static boolean isOn = true;

    static void setIsON(boolean isON) {
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
            if (!isOn) {
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
            if (!isOn) {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }

    static void soundHitSpecial() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("../resources/sounds/hitSpecial.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if (!isOn) {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
    }

    static void soundHitElectricity() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("../resources/sounds/electricity.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if (!isOn) {
                clip.stop();
            }
            Timer timer = new Timer(1100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer) e.getSource()).stop();
                    clip.stop();
                }

            });
            timer.start();
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
            if (!isOn) {
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
            if (!isOn) {
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
            if (!isOn) {
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
            if (!isOn) {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }

    }

    static void soundChooseColor() throws LineUnavailableException {
        File file = new File("../resources/sounds/chooseColor.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if (!isOn) {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }

    }

    static void soundTap() throws LineUnavailableException {
        File file = new File("../resources/sounds/tap.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            if (!isOn) {
                clip.stop();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }

    }
}
