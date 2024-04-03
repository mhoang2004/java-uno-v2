import java.util.ArrayList;

public abstract class User {
    static final int INIT_CARD = 7;
    static final int GAP_CARD_HORIZONTAL = 50;
    static final int GAP_CARD_VERTICAL = 20;

    protected ArrayList<Card> cards;
    protected String position;
    protected User nextUser; // User kế tiếp
    protected boolean isTurn; // xem lượt đó có phải của user này không
    protected boolean isUserHit; // xem lượt đó user có ra bài không
    protected boolean isPlayer;

    int xPos;
    int yPos;

    User(Deck deck, String position) {
        this.position = position;
        isPlayer = true;
        cards = new ArrayList<Card>();

        for (int i = 0; i < INIT_CARD; i++) {
            Card card = deck.getOneCard();
            // while(i==0 && card.getColor() != null)
            // {
            // card = deck.getOneCard();
            // }
            card.setUser(this);
            cards.add(card);
        }

        isUserHit = false;
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

    public int sizeCards() {
        return cards.size();
    }

    // action card
    public abstract Card drawCard();

    public void hitCard(Card card, boolean check) {
        // Game.prevCard.assignCard(card);

        cards.remove(card);
        setCardsPosition();

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

    // pass qua hẳn 1 user, cấm đi lượt đó
    public void passTurn() {
        this.getNextUser().setTurn(false);
        this.setTurn(false);
        this.getNextUser().getNextUser().setTurn(true);
    }

    // khi user đánh ra lá skip, drawtwo, drawfour, rút bài rồi qua lượt, không được
    // đánh
    public void skip() {
        if (Game.prevCard.getRank() == "SKIP") {
            this.passTurn();
        } else if (Game.prevCard.getRank() == "DRAWTWO") {
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
        sortCard();
        for (Card card : cards) {
            if (checkValid(card)) {
                // card.suggestedEffect();
            } else {
                card.setCard();
            }
        }
        for (Card card : cards) {
            if (checkValid(card) == true) {

                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    // Kiểm tra lá prevCard có phảỉ lá wild
    public boolean checkWild() {
        if (Game.prevCard.getRank() == "WILD") {
            return true;
        } else
            return false;
    }

    // khi user đánh ra là wild
    String changePrevCard(String src, Card card) {
        Card tmp = new Card(src, card.getRank());
        Game.prevCard.assignCard(tmp);
        if (card.getRank() == "DRAWFOUR") {
            // this.passTurn();
            Game.delaySkip(3);
        } else if (Game.getIsReverse() == true) {
            Game.delayReverse(3);
        } else {
            Game.delayReverse(0);
        }

        card.hitCard();
        return src;
    }

    // Kiểm tra lá prevCard có phảỉ lá skip, drawtwo hay drawfour
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
