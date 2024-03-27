import java.util.ArrayList;

public class Computer extends User {
    private ArrayList<Card> backCards;

    Computer(Deck deck, String position) {
        super(deck, position);

        backCards = new ArrayList<>();

        for (Card card : cards) {
            Card backCard = new Card();
            backCard.setUser(this);
            backCards.add(backCard);
            Game.addToMainPanel(backCard);
        }

        setCardsPosition();
    }

    public void setCardsPosition() {
        setUserPosition();

        int xPadding = 0;
        int yPadding = 0;
        for (Card backCard : backCards) {
            backCard.setLocation(xPos + xPadding, yPos + yPadding);

            if (position == "NORTH") {
                xPadding += GAP_CARD_HORIZONTAL;
            } else {
                yPadding += GAP_CARD_VERTICAL;
            }
        }
    }

    public Card validCard() {
        // Test reverse
        // for(Card card : cards) {
        // if (card.getRank() == "REVERSE")
        // return card;
        // }

        // Test skip
        // for(Card card : cards) {
        // if (card.getRank() == "SKIP")
        // return card;
        // }
        this.isPlayedCard = false;
        for (Card card : cards) {
            if (card.getColor() == Game.prevCard.getColor()) {
                if (card.getRank().length() == 1) {
                    this.isPlayedCard = true;
                    return card;
                }
            }
        }
        for (Card card : cards) {
            if (card.getRank() == Game.prevCard.getRank()) {
                this.isPlayedCard = true;
                return card;
            }
        }
        for (Card card : cards) {
            if (card.getColor() == Game.prevCard.getColor()) {
                this.isPlayedCard = true;
                return card;
            }
        }
        for (Card card : cards) {
            if (card.getRank() == "WILD") {
                this.isPlayedCard = true;
                return card;
            }
        }
        for (Card card : cards) {
            if (card.getRank() == "DRAWFOUR") {
                this.isPlayedCard = true;
                return card;
            }
        }
        return null;
    }

    // Check wild
    public boolean checkWild() {
        if (Game.prevCard.getRank() == "WILD") {
            return true;
        } else
            return false;
    }

    public void computerHitCard() {
        Card validCard = cards.get(0); // todo: function LogicComputerHit
        int index = cards.indexOf(validCard);

        Card chosenCard = backCards.get(index);
        chosenCard.assignCard(validCard);

        chosenCard.hitCardAnimation();
        backCards.remove(index);
    }

    public void computerTurn() {
        this.computerHitCard();

        // if (this.getTurn() == true) {
        // if (checkWild()) {
        // this.wild();
        // }
        // this.computerHitCard();
        // }
        // if (this.isPlayedCard == false) {
        // this.drawCard();
        // this.computerHitCard();
        // }
    }
}
