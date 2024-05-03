import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.Timer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public abstract class User {
    static final int INIT_CARD = 7;
    static final int GAP_CARD_HORIZONTAL = 50;
    static final int GAP_CARD_VERTICAL = 20;
    static final String PATH_TO_DB = "../resources/csv/data.csv";

    protected ArrayList<Card> cards;
    protected String position;
    protected User nextUser; // next user
    protected boolean isTurn;
    protected boolean isUserHit; // did user hit a card?
    protected boolean isPlayer;

    protected int xPos;
    protected int yPos;

    User(Deck deck, String position) {
        this.position = position;

        cards = new ArrayList<Card>();

        for (int i = 0; i < INIT_CARD; i++) {
            Card card = deck.getOneCard();
            card.addEvent();
            card.setUser(this);
            if (this.isPlayer()) {
                cards.add(sortCard(card), card);
                System.out.println("Buoc " + i);
                for (Card card2 : cards) {
                    System.out.println(card2);
                }
            } else {
                cards.add(card);
            }
        }
        isTurn = false;
        isUserHit = false;
    }
     void backDefaultCard()
     {
        for (Card card : cards) {
            card.backDefaultCard();
            card.addEvent();
        }
     }
    public void setUserPosition() {
        if (position == "SOUTH") {
            xPos = (MyPanel.WIDTH - (Card.WIDTH + (sizeCards() - 1) * GAP_CARD_HORIZONTAL)) / 2;
            yPos = MyPanel.HEIGHT - 100;
        } else if (position == "NORTH") {
            xPos = (MyPanel.WIDTH - (Card.WIDTH + (sizeCards() - 1) * GAP_CARD_HORIZONTAL)) / 2;
            yPos = 20;
        } else if (position == "WEST") {
            xPos = 20;
            yPos = (MyPanel.HEIGHT - (Card.HEIGHT + (sizeCards() - 1) * GAP_CARD_VERTICAL)) / 2;
        } else { // EAST
            xPos = MyPanel.WIDTH - 100;
            yPos = (MyPanel.HEIGHT - (Card.HEIGHT + (sizeCards() - 1) * GAP_CARD_VERTICAL)) / 2;
        }
    }

    public int getPos() {
        if (position == "NORTH") {
            return 1;
        } else if (position == "WEST") {
            return 0;

        }
        return 2;
    }

    public abstract void setCardsPosition();

    public int sizeCards() {
        return cards.size();
    }

    public abstract boolean isPlayer();

    // action card
    public abstract Card drawCard();

    public void hitCard(Card card, boolean check) {
        cards.remove(card);

        setCardsPosition();
    }

    public boolean getIsPlayer() {
        return isPlayer;
    }

    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public boolean getTurn() {
        return isTurn;
    }

    public String getPosition() {
        return position;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setNextUser(User nextUser) {
        this.nextUser = nextUser;
    }

    public User getNextUser() {
        return nextUser;
    }

    public Card getLastCard() {
        int index = cards.size() - 1;
        return cards.get(index);
    }

    // pass one user, ban that turn
    public void passTurn() {
        this.getNextUser().setTurn(false);
        this.setTurn(false);
        this.getNextUser().getNextUser().setTurn(true);
    }

    // when user play skip, drawtwo, drawfour, draw and pass turn, not play
    public void skip() {
        if (Game.prevCard.getRank() == "SKIP") {
            this.getNextUser().banAnimation();
            this.passTurn();
        } else if (Game.prevCard.getRank() == "DRAWTWO") {
            this.getNextUser().drawCard();
            this.getNextUser().drawCard();
            this.passTurn();

        } else if (Game.prevCard.getRank() == "DRAWFOUR") {
            this.getNextUser().drawCard();
            this.getNextUser().drawCard();
            this.getNextUser().drawCard();
            this.getNextUser().drawCard();
            this.passTurn();
        }
    }

    // Sort Card
    public int sortCard(Card newCard) {
        int index = LogicGame.isNewColor(newCard, this);

        // new card is null color
        if (newCard.getColor() == null) {
            for (int j = 0; j < LogicGame.countCard(newCard.getColor(), this); j++)
                if (cards.get(j).equals(newCard))
                    return j + 1;
            return 0;
        }
        // new card is new card
        if (index == -1)
            return sizeCards();
        int sizeCard = LogicGame.countCard(cards.get(index).getColor(), this) + index;
        System.out.println("THIS CARD IS " + newCard + "-- index insert :" + LogicGame.isNewColor(newCard, this)
                + "-- index last color: " + sizeCard);
        // new card is special
        if (newCard.isSpecial()) {
            for (int j = index; j < sizeCard; j++)
                if (cards.get(j).equals(newCard))
                    return j + 1;
            return index;
        }
        // new card is nomal card
        int i = index;
        while (i < sizeCard) {
            // cadrs[j] is special
            if (cards.get(i).isSpecial())
                // visited to end cards
                if (i + 1 == sizeCard)
                    return i + 1;
                else
                    i++;
            // cards[j] >= new card
            if (cards.get(i).compareTo(newCard) > 0) {
                System.out.println("(cards[j] > new card)I is " + i);
                return i;
            } else {
                System.out.println("(cards[j]< new card)I is " + i);
                if (i + 1 == sizeCard) {
                    return i + 1;
                } else {
                    // next card visited < new card
                    if (cards.get(i + 1).compareTo(newCard) < 0)
                        i++;
                    else
                        // next card visited >= new card
                        return i + 1;
                }

            }
        }
        return index;
    }

    // Check Valid Card
    public boolean checkValid(Card card) {
        Card prevCard = Game.prevCard;
        if (card.getColor() == null) {
            return true;
        }
        // if (prevCard.getRank() == null && card.getColor().charAt(0) ==
        // prevCard.getColor().charAt(0) ) {
        // return true;
        // }
        if (card.getColor() == null) {
            return true;
        }
        if (card.getRank() == prevCard.getRank()) {
            return true;
        }
        try {
            if (card.getColor().charAt(0) == prevCard.getColor().charAt(0)) {
                return true;
            }
        } catch (NullPointerException e) {
            return true;
        }

        if (card.getColor() == null) {
            return true;
        }
        return false;
    }

    // Check card
    public boolean checkCard() {
        for (Card card : cards) {
            if (checkValid(card) == true) {

                return true;
            }
        }
        return false;
    }

    // khi user đánh ra là wild hoặc drawfour
    // String changePrevCard(String src, Card card) {
    // card.hitCard();

    // Card tmp = new Card(src, card.getRank());

    // if (card.getRank() == "DRAWFOUR") {
    // this.passTurn();
    // Game.delaySkip(3);
    // } else {
    // this.nextUser.setTurn(true);
    // this.setTurn(false);
    // Game.delayReverse(3);
    // }

    // return src;
    // }

    // Check prevCard is skip, drawtwo or drawfour
    public boolean checkSkip() {
        if (Game.prevCard.getRank() == "SKIP") {
            return true;
        } else if (Game.prevCard.getRank() == "DRAWTWO") {
            return true;
        } else if (Game.prevCard.getRank() == "DRAWFOUR") {
            return true;
        }
        return false;
    }

    // score
    public int scores() {
        int scores = 0;
        for (Card card : cards) {
            if (card.getRank().length() == 1) {
                scores = scores + Integer.parseInt(card.getRank());
            }
            // if (card.getRank() == "DRAWTWO" || card.getRank() == "SKIP" || card.getRank()
            // == "REVERSE") {
            // scores = scores + 20;
            // }
            if (card.getRank().length() > 1 && card.getColor() != null) {
                scores = scores + 20;
            }
            if (card.getColor() == null) {
                scores = scores + 50;
            }
        }
        return scores;
    }

    public boolean endGame() {
        return (sizeCards() == 0);
    }

    public void banAnimation() {
        final int BAN_WIDTH = 80;
        JLabel banLabel = new JLabel();

        ImageIcon icon = new ImageIcon("../resources/images/ban1.png");
        int x, y;

        if (position.equals("SOUTH") || position.equals("NORTH")) {
            x = (MyPanel.WIDTH - BAN_WIDTH) / 2;
            y = yPos;
        } else {
            x = xPos;
            y = (MyPanel.HEIGHT - BAN_WIDTH) / 2;
        }
        banLabel.setBounds(x, y, BAN_WIDTH, BAN_WIDTH);
        banLabel.setIcon(icon);
        Game.addToMainPanel(banLabel);

        // clear ban after 1 second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.mainPanel.remove(banLabel);
                Game.mainPanel.repaint();
            }
        });

        timer.start();
    }

    public ArrayList<Card> getCard() {
        return cards;
    }

    protected abstract void offFocus();

    protected abstract void effectArroundClickCard();
}
