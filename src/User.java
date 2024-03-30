import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class User {
    static final int INIT_CARD = 7;
    static final int GAP_CARD_HORIZONTAL = 50;
    static final int GAP_CARD_VERTICAL = 20;

    protected ArrayList<Card> cards;
    protected String position;
    protected User nextUser;
    protected boolean turn;
    protected boolean isPlayedCard;
    int xPos;
    int yPos;

    User(Deck deck, String position) {
        this.position = position;
        cards = new ArrayList<Card>();

        for (int i = 0; i < INIT_CARD; i++) {
            Card card = deck.getOneCard();
            card.setUser(this);
            cards.add(card);
        }
        isPlayedCard = false;
        sortCard();
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

    public abstract void setCardsPosition();

    public abstract Card drawCard();

    public int sizeCards() {
        return cards.size();
    }

    public void hitCard(Card card, boolean check) {
        // Game.prevCard.assignCard(card);

        cards.remove(card);
        setCardsPosition();

    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean getTurn() {
        return turn;
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

    public void wild() {
        if (Game.prevCard.getRank() == "WILD") {
            Game.prevCard.setColor("B"); // get from chose color, this is demo
            Game.prevCard.setRank(null);
        }
    }

    public void passTurn() {
        this.getNextUser().setTurn(false);
        this.setTurn(false);
        this.getNextUser().getNextUser().setTurn(true);
    }

    public void skip() {
        if (Game.prevCard.getRank() == "SKIP") {
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
            Game.prevCard.setColor("B"); // get from chose color, this is demo
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
        for(int i=0; i< cards.size() -1; i++)
        {
            for(int j = i+1; j < cards.size(); j++ )
            {
                if(cards.get(i).getColor() == null || cards.get(j).getColor() == null)
                {
                   continue;
                }
                if(cards.get(i).getRank().compareTo(cards.get(j).getRank()) >= 0 && cards.get(i).getColor().equals(cards.get(j).getColor()))
                {
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
        System.out.println("Checkingggggg");
        Card prevCard = Game.prevCard;
        
        if (card.getColor() == prevCard.getColor()) {
            return true;
        }
        if (card.getRank() == prevCard.getRank()) {
            return true;
        }
        if (card.getColor() == null) {
            return true;
        }
        return false;
    }
    // Check card
    
    public boolean checkCard ()
    {
        sortCard();
        for (Card card : cards) {
            if(checkValid(card))
            {
                //card.suggestedEffect();
            }else{
                card.setCard();
            }
        }
        for (Card card : cards) {
            if(checkValid(card)== true){

                return true;
            }
        }
        System.out.println("false");
        return false;
    }
    // Check wild
    public boolean checkWild() {
        if (Game.prevCard.getRank() == "WILD") {
            return true;
        } else
            return false;
    }

    // Check skip
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
}
