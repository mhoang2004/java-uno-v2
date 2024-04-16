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
            card.setUser(this);
            if(this.isPlayer())
            {
                cards.add(sortCard(card), card);
                System.out.println("Buoc " + i);
                for (Card card2 : cards) {
                    System.out.println(card2);
                }
            }else{
                cards.add(card);
            }
           
            card.setAddress(i);
        }
        isTurn = false;
        isUserHit = false;
        
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
    public int sortCard(Card newCard) 
    {
        int index =0;
       System.out.println("THIS CARD IS " + newCard+"/////"+isNewColor(newCard));
        if(newCard.getColor() == null)
        {
            for(int j=0; j<countCard(newCard.getColor()); j++)
            {
                if(cards.get(j).getRank().equals(newCard.getRank()))
                    return j+1;   
            }
                return 0;
        }
        if(isNewColor(newCard) == -1)
            return sizeCards();
        index = isNewColor(newCard);
        // new card is special      
        if(newCard.isSpecial())
        {
            for(int j=index; j<countCard(cards.get(index).getColor())+index-1; j++)
                    {
                         if(cards.get(j).getRank().equals(newCard.getRank()))
                            return j+1;   
                    }
                    return index;
        }
        // new card is nomal card
        for(int j=index; j<countCard(cards.get(index).getColor())+index; j++)
        {
            // cadrs[j] is special
            if(cards.get(j).isSpecial())
            {
                if(j == countCard(cards.get(index).getColor())+index -1 )
                {
                    return j+1;
                }else{
                    continue;
                }
            }
            
            // cards[j] >= cards[index]
                if(cards.get(j).getRank().compareTo(newCard.getRank()) >= 0)
                {
                    if(j == countCard(cards.get(index).getColor())+index -1 )
                    {
                        return j+1;
                    }else{
                        continue;
                    }
                }
                // cards[j]< cards[index]
                if(cards.get(j).getRank().compareTo(newCard.getRank()) < 0 )
                {
                    if(j >= index )
                    {
                        return j+1;
                    }else{
                        if(cards.get(j+1).getRank().compareTo(newCard.getRank()) < 0)
                        {
                            continue;
                        }else{
                            return j+1;
                        }
                    }
                 }
                       
        }
                
         return index;
    }
        
       

    // count card in cards to color
    public int countCard(String color)
    {
        int count =0;
        if(color == null)
        {
            for (Card card : cards) {
                if(card.getColor()== null)
                    count ++;
            }
        }else{
            for (Card card : cards) {
                if(card.getColor() == null)
                {
                    continue;
                }
                if(card.getColor().equals(color))
                    count ++;
            }
        }
        
        return count;
    }

    // is new color card 
    public int  isNewColor(Card newCard)
    {
        if(newCard.getColor() ==null)
        {
            for (int i=0; i< sizeCards(); i++) {
                if(cards.get(i).getColor() == null)
                    return i;
            }
            return 0;
        }
        for (int i=0; i< sizeCards(); i++) 
        {
            if(cards.get(i).getColor() == null)
            {
                continue;
            }
            if(cards.get(i).getColor().charAt(0)== newCard.getColor().charAt(0))
                return i;
        }
            
        return -1;
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
        if(cards.size() > 1)
        {
            for (int i=0; i< cards.size(); i++) {
                if(i==0 )
                {
                    if(cards.get(i).isClicked)
                    {
                        cards.get(i+1).effectArround();
                        return;
                    }
                }else if(i == cards.size()-1){
                    if(cards.get(i).isClicked)
                    {
                        cards.get(i-1).effectArround();
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
}
