import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

public class Deck extends JLabel implements MouseListener, ActionListener {
    static final String[] ranks = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "DRAWTWO", "REVERSE", "SKIP" };
    static final String[] colors = { "B", "G", "Y", "R" };
    static final int X = ((MyPanel.WIDTH - Card.WIDTH) / 2) - 250;
    static final int Y = (MyPanel.HEIGHT - Card.HEIGHT) / 2 + 100;
    private ArrayList<Card> deck;
    private boolean bool= true;
    private Timer timer = new Timer(200, this);
    Clip clip;
    Deck() throws LineUnavailableException {
        if (deck != null)
            deck.clear();

        this.createDeck();
        this.shuffleDeck();

        this.setIcon(new ImageIcon("../resources/cards/BACK.png"));
        this.setBounds(X, Y, Card.WIDTH, Card.HEIGHT);
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        File file = new File("../resources/sounds/sos.wav");
        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            clip= AudioSystem.getClip();
           
            if(Game.accountUser== null)
            {
                clip.open(audioStream);
            }else{
                if(Game.accountUser.isSound)
                {
                    clip.open(audioStream);
                }
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is not supported.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading the audio file.");
        }
        addMouseListener(this);
    }
    
    public void createDeck() {
        deck = new ArrayList<Card>();
        Card tempCard;

        for (String color : colors) {
            for (String rank : ranks) {
                tempCard = new Card(color, rank);
                deck.add(tempCard);

                if (rank != "0") // except "0" any else will have 2 cards
                    deck.add(new Card(tempCard));
            }
        }

        for (int i = 0; i < 4; i++) {
            tempCard = new Card(null, "WILD"); // 4 wild card
            deck.add(tempCard);
        }

        for (int i = 0; i < 4; i++) {
            tempCard = new Card(null, "DRAWFOUR"); // 4 +4 card
            deck.add(tempCard);
        }
    }

    private void shuffleDeck() {
        int size = deck.size();
        Random random = new Random();
        int j;
        for (int i = 0; i < size; i++) {
            j = random.nextInt(size);
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card getOneCard() {
        // if(Game.prevCard != null)
        // {
        //     if(!Game.prevCard.isSuperSpecial() && !deck.getLast().equals(Game.prevCard))
        //     {
        //         deck.addLast(Game.prevCard);
        //     }
        // }
        
       
        return deck.remove(0);
    }

    public void mouseClicked(MouseEvent e) {
        if(Game.player.getTurn() == true && Game.player.checkCard() == false )
        {      
            removeEffect();
            this.setEnabled(false);
            Game.player.setTurn(false);
            Card cardDrawn =  Game.player.drawCard2();
            cardDrawn.removeEffect();             
            if(Game.player.checkValid(cardDrawn))
            {
               // Game.addToMainPanel(new DrawCard(cardDrawn));  
               new DrawCardPanel(cardDrawn);
            }else{
                Game.deck.setEnabled(true);
                Game.player.getNextUser().setTurn(true);
                // Game.player.setTurn(false);
                Game.delayReverse(3);
            }         
      
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

   public void removeEffect() {
    clip.stop();
        timer.stop();
        setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void suggestedEffect() {
        clip.start();
        Border border = new LineBorder(Color.YELLOW, 6);
        setBorder(border);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(bool == true)
                {
                    bool = false;
                    Border border = new LineBorder(Color.YELLOW, 6);
                    setBorder(border);
                }
                else{
                    bool = true;
                    Border border = new LineBorder(Color.RED, 6);
                    setBorder(border);
                }
    }
}
