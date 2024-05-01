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
            card.backDefaultCard();
        }
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
        // card.setLocation(Deck.X, Deck.Y);
        card.addMouseListener(card); // only player not computer
        card.setUser(this);

        Game.addToMainPanel(card);

        // cards.add(card);
        cards.add(sortCard(card), card);
        int xPadding = 0;
        int yPadding = 0;
        for (Card card2 : cards) {
            Game.mainPanel.remove(card2);
        }
        for (Card card1 : cards) {
            Game.addToMainPanel(card1);
        }
        Game.mainPanel.repaint();
        card.drawCardAnimation();
        return card;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    protected void offFocus() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).backDefaultCard();
        }
    }

    public void effectArroundClickCard() {
        if (cards.size() > 1) {
            for (int i = 0; i < cards.size(); i++) {
                if (i == 0) {
                    if (cards.get(i).isClicked) {
                        cards.get(i + 1).effectArround();
                        return;
                    }
                } else if (i == cards.size() - 1) {
                    if (cards.get(i).isClicked) {
                        cards.get(i - 1).effectArround();
                        return;
                    }
                } else {
                    if (cards.get(i).isClicked) {
                        cards.get(i + 1).effectArround();
                        cards.get(i - 1).effectArround();
                        return;
                    }
                }

            }
        }

    }

}
