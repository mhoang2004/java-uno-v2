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
        }

        Card card = Game.deck.getOneCard();
        card.setLocation(Deck.X, Deck.Y);
        card.addMouseListener(card); // only player not computer
        card.setUser(this);
        
        Game.addToMainPanel(card);

        cards.add(card);

        card.drawCardAnimation();
        // this.sortCard();
        return card;
    }
}
