import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.*;

public class ButtonUno extends JLabel implements MouseListener {
    static final int WIDTH = 80;
    static final int HEIGHT = 69;

    private boolean unoClicked;

    ButtonUno() {
        super();
        this.unoClicked = false;
        // get button
        ImageIcon icon = new ImageIcon("../resources/images/button-uno.png");
        this.setIcon(icon);
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2 + 300, (MyPanel.HEIGHT - HEIGHT) / 2 + 200, WIDTH, HEIGHT);
        // this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // handled when the button is pressed
    }

    public boolean getUnoClicked() {
        return this.unoClicked;
    }

    public void setUnoClicked() {
        this.unoClicked = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // sound
        try {
            SoundControler.soundClick();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
        unoClicked = true;
        if (Game.player.sizeCards() > 1) {
            Game.displayText();
            Game.notiToUser.setText("Hit the UNO button BEFORE playing your second-to-last-card!");
            Game.notiToUser.removeText();
        } else if (Game.player.sizeCards() == 1) {
            try {
                SoundControler.soundUno();
            } catch (LineUnavailableException e1) {

                e1.printStackTrace();
            }
            Game.player.unoAnimation();
            Game.displayText();
            Game.notiToUser.setText("Uno!");
            Game.notiToUser.removeText();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(new Color(0, 0, 0, 150)); // black transparent 150
        this.setOpaque(false); // transparent
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
