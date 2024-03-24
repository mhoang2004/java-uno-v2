import java.awt.Cursor;

public class Player extends User {
    Player(Deck deck, String position) {
        super(deck, position);
        setCardsPosition();

        for (Card card : cards) {
            card.addMouseListener(card);
            card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            Game.mainPanel.add(card, Integer.valueOf(MyPanel.LAYER++));
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
}
