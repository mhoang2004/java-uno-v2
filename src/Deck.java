import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Deck extends JLabel implements MouseListener {
    static final String[] ranks = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "DRAWTWO", "REVERSE", "SKIP" };
    static final String[] colors = { "B", "G", "Y", "R" };
    static final int X = ((MyPanel.WIDTH - Card.WIDTH) / 2) - Card.WIDTH;
    static final int Y = (MyPanel.HEIGHT - Card.HEIGHT) / 2;

    private ArrayList<Card> deck;

    Deck() {
        if (deck != null)
            deck.clear();

        this.createDeck();
        this.shuffleDeck();

        this.setIcon(new ImageIcon("../resources/cards/BACK.png"));
        this.setBounds(X, Y, Card.WIDTH, Card.HEIGHT);
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(this);
    }

    private void createDeck() {
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
        return deck.remove(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Game.player.getTurn() == true && Game.player.checkCard() == false) {
            this.setEnabled(false);
            Game.player.setTurn(false);
            Card cardDrawn = Game.player.drawCard();

            if (Game.player.checkValid(cardDrawn)) {
                // Game.mainPanel.setEnabled(false);
                Game.addToMainPanel(new DrawCard(cardDrawn));
            } else {
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
}
