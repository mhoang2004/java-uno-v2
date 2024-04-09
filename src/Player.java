import java.awt.Cursor;

public class Player extends User {
    Player(Deck deck, String position) {
        super(deck, position);
        setCardsPosition();
        isPlayer = true;
        for (Card card : cards) {
            card.addMouseListener(card);
            card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            Game.addToMainPanel(card);
        }
    }

    public void suggestedEffect() {
        for (Card card : cards) {
            if (this.checkValid(card) == true) {
                card.suggestedEffect();
            } else {
                card.removeEffect();
            }
        }
    }

    public void setCardsPosition() {
        setUserPosition();

        int xPadding = 0;
        int yPadding = 0;
        for (Card card : cards) {
            card.setLocation(xPos + xPadding, yPos + yPadding);
            xPadding += GAP_CARD_HORIZONTAL;
        }
    }

    public Card drawCard() {
        if (Game.deck.getDeck().size() == 0) {
            System.out.println("Het bai roi cuu");
            // Game.deck = new Deck();
        }

        Card card = Game.deck.getOneCard();
        // while(!card.getRank().equals("DRAWFOUR")||!card.getRank().equals("DRAWTWO")
        // ||!card.getRank().equals("SKIP"))
        // {
        // card = Game.deck.getOneCard();
        // }
        card.setLocation(Deck.X, Deck.Y);
        card.addMouseListener(card); // only player not computer
        card.setUser(this);

        Game.addToMainPanel(card);

        cards.add(card);

        card.drawCardAnimation();
        // this.sortCard();
        return card;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    public void hitting() {

    }
}
