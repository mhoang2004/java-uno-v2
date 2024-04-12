import java.util.ArrayList;
import javax.swing.*;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class User {
    static final int INIT_CARD = 7;
    static final int GAP_CARD_HORIZONTAL = 50;
    static final int GAP_CARD_VERTICAL = 20;

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
            // while(i==0 && card.getColor() != null)
            // {
            // card = deck.getOneCard();
            // }
            card.setUser(this);
            cards.add(card);
            card.setAddress(i);
        }
        isTurn = false;
        isUserHit = false;
        sortCard();
    }
    void offFocus()
    {
        for(int i=0; i< cards.size(); i++)
        {          
            cards.get(i).backDefaultCard();    
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
    public int getPos()
    {
        if (position == "NORTH") 
        {
            return 1;
        } else if (position == "WEST") 
        {
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
    public void sortCard() {
        // Comparator cp = Comparator.comparing(Card::getRank);
        // cards.sort(cp);
        Card firstCard = new Card(cards.get(0));
        for (int i = 1; i < cards.size(); i++) {
            if (firstCard.getColor() != cards.get(i).getColor()) {
                for (int j = i + 1; j < cards.size(); j++) {
                    if (firstCard.getColor() == cards.get(j).getColor()) {
                        Card cardTMP = cards.get(i);
                        cards.set(i, cards.get(j));
                        cards.set(j, cardTMP);
                        break;
                    }
                }
            }
            firstCard = cards.get(i);
        }
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).getColor() == null || cards.get(j).getColor() == null) {
                    continue;
                }
                if (cards.get(i).getRank().compareTo(cards.get(j).getRank()) >= 0
                        && cards.get(i).getColor().equals(cards.get(j).getColor())) {
                    Card cardTMP = cards.get(i);
                    cards.set(i, cards.get(j));
                    cards.set(j, cardTMP);
                    break;
                }
            }
        }
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
        // sortCard();
        // for (Card card : cards) {
        // if (checkValid(card)) {
        // // card.suggestedEffect();
        // } else {
        // card.setCard();
        // }
        // }
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
        return (sizeCards()  == 0);
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
    public ArrayList<Card> getCard()
    {
        return cards;
    }
    public void effectArroundClickCard()
    {
        for (int i=0; i< cards.size(); i++) {
            if(i==0 || i == cards.size()-1)
            {
                if(cards.get(i).isClicked)
                {
                    cards.get(i+1).effectArround();
                    return;
                }
            }else{
                if(cards.get(i).isClicked)
                {
                    cards.get(i+1).effectArround();
                    cards.get(i-1).effectArround();
                    return;
                }
            }
            
        }
    }
}
