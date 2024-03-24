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
            Game.mainPanel.add(backCard, Integer.valueOf(MyPanel.LAYER++));
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

    public void computerHitCard() {
        Card validCard = cards.get(3); // todo: function LogicComputerHit
        int index = cards.indexOf(validCard);

        Card choosenCard = backCards.get(index);
        choosenCard.assignCard(validCard);

        choosenCard.hitCardAnimation();
        backCards.remove(index);

        int test = 0;
        for (Card backCard : backCards) {
            System.out.println("Card: " + test++);
        }
    }

}
