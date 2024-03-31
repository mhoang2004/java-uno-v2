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

    public Card drawCard() {
        if (Game.deck.getDeck().size() == 0) {
            System.out.println("Het bai roi cuu");
        }

        Card card = Game.deck.getOneCard();
        cards.add(card);

        Card backCard = new Card();

        backCard.setLocation(Deck.X, Deck.Y);
        backCard.setUser(this);

        Game.addToMainPanel(backCard);

        backCards.add(backCard);

        backCard.drawCardAnimation();

        return card;
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
    // Máy chọn ra 1 lá để đánh
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
        this.isUserHit = false;
        for (Card card : cards) {
            if (card.getColor() == Game.prevCard.getColor()) {
                if (card.getRank().length() == 1) {
                    this.isUserHit = true;
                    return card;
                }
            }
        }
        for (Card card : cards) {
            if (card.getRank() == Game.prevCard.getRank()) {
                this.isUserHit = true;
                return card;
            }
        }
        for (Card card : cards) {
            if (card.getColor() == Game.prevCard.getColor()) {
                this.isUserHit = true;
                return card;
            }
        }
        for (Card card : cards) {
            if (card.getRank() == "WILD") {
                this.isUserHit = true;
                return card;
            }
        }
        for (Card card : cards) {
            if (card.getRank() == "DRAWFOUR") {
                this.isUserHit = true;
                return card;
            }
        }
        return null;
    }
    // Máy đánh ra lá đã chọn
    public void computerHitCard() {
        Card validCard = validCard(); // todo: function LogicComputerHit
        if (validCard != null) {
            int index = cards.indexOf(validCard);
            Card chosenCard ;
            try{
                chosenCard = backCards.get(index);
            }catch(IndexOutOfBoundsException x)
            {
                chosenCard = backCards.get(index-1);
            }
            chosenCard.assignCard(validCard);
            Game.prevCard.assignCard(validCard);
            chosenCard.hitCardAnimation();
            backCards.remove(index);
            cards.remove(index);
            System.out.println(backCards.size());
        }
    }
    // Kiểm tra lá wild và nếu máy không có lá nào đánh được thì sau khi rút bài chọn đánh luôn lá đó nếu đánh được
    public void computerTurn() {
        if (this.getTurn() == true) {
            if (checkWild()) {
                this.wild();
            }
            this.computerHitCard();
            if (this.isUserHit == false) {
                this.drawCard();
                this.computerHitCard();
            }
        }
    }
}
