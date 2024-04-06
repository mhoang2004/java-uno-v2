import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Computer extends User {
    private ArrayList<Card> backCards;

    Computer(Deck deck, String position) {
        super(deck, position);
        isPlayer = false;
        backCards = new ArrayList<>();

        for (Card card : cards) {
            Card backCard = new Card();
            backCard.setUser(this);
            backCards.add(backCard);
            Game.addToMainPanel(backCard);
        }

        setCardsPosition();
    }

    public ArrayList<Card> getCards() {
        return cards;
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
        System.out.println("Draw card " + card);
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

    // Kiểm tra lá prevCard có phải là cần chọn màu không
    public boolean checkChangeColor() {
        if (Game.prevCard.getRank() == "WILD") {
            return true;
        } else if (Game.prevCard.getRank() == "DRAWFOUR") {
            return true;
        }

        return false;
    }

    // Máy chọn màu khi đánh ra lá wild hoặc drawfour
    public String chooseColor() {
        Map<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("B", 0);
        hm.put("R", 0);
        hm.put("Y", 0);
        hm.put("G", 0);

        for (Card card : cards) {
            for (Map.Entry<String, Integer> e : hm.entrySet()) {
                if (e.getKey() == card.getColor()) {
                    e.setValue(e.getValue() + 1);
                }
            }
        }
        int max = 0;
        for (Map.Entry<String, Integer> e : hm.entrySet()) {
            if (e.getValue() >= max)
                max = e.getValue();
        }
        for (Map.Entry<String, Integer> e : hm.entrySet()) {
            if (e.getValue() == max) {
                System.out.println("Change Color: " + e.getKey());
                return e.getKey();
            }
        }
        return null;
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
        //Test drawfour, wild
        // this.isUserHit = false;
        // for(Card card : cards) {
        //     if (card.getRank() == "DRAWFOUR" || card.getRank() == "WILD") {
        //     this.isUserHit = true;
        //     return card;
        // }
        //}
        this.isUserHit = false;
        for (Card card : cards) {
            if (this.checkValid(card) == true) {
                this.isUserHit = true;
                return card;
            }
        }
        // this.isUserHit = false;
        // for (Card card : cards) {
        // if (card.getColor() == Game.prevCard.getColor()) {
        // if (card.getRank().length() == 1) {
        // this.isUserHit = true;
        // return card;
        // }
        // }
        // }
        // for (Card card : cards) {
        // if (card.getRank() == Game.prevCard.getRank()) {
        // if (card.getRank().length() == 1) {
        // this.isUserHit = true;
        // return card;
        // }
        // }
        // }
        // for (Card card : cards) {
        // if (card.getColor() == Game.prevCard.getColor()) {
        // this.isUserHit = true;
        // return card;
        // }
        // }
        // for (Card card : cards) {
        // if (card.getRank() == Game.prevCard.getRank()) {
        // this.isUserHit = true;
        // return card;
        // }
        // }
        // for (Card card : cards) {
        // if (card.getRank() == "WILD") {
        // this.isUserHit = true;
        // return card;
        // }
        // }
        // for (Card card : cards) {
        // if (card.getRank() == "DRAWFOUR") {
        // this.isUserHit = true;
        // return card;
        // }
        // }
        return null;
    }

    // Máy đánh ra lá đã chọn
    public void computerHitCard() {
        Card validCard = validCard(); // todo: function LogicComputerHit
        Card chosenCard = null;

        if (validCard != null) {
            int index = cards.indexOf(validCard);
            chosenCard = backCards.get(index);
            if (this.getTurn() == true) {
                chosenCard.assignCard(validCard);

                Game.prevCard.setColor(validCard.getColor());
                Game.prevCard.setRank(validCard.getRank());
                chosenCard.hitCardAnimation();
                backCards.remove(index);
                cards.remove(index);
                // if (chosenCard.getColor() == null) {
                //     Game.prevCard.setColor(cards.get(0).getColor());
                //     Game.prevCard.setRank(chosenCard.getRank());
                // }
            }
        }
    }
    // Đổi màu prevCard nếu máy đánh ra là lá wild hay drawfour và nếu máy không có
    // lá nào đánh được thì sau khi rút bài chọn đánh luôn lá đó nếu đánh được
    public void computerTurn() {
        if (this.getTurn() == true) {
            this.computerHitCard();
            if (this.isUserHit == true) {
                if (checkChangeColor()) {
                    Game.prevCard.setColor(this.chooseColor());
                }
            } else if (this.isUserHit == false) {
                this.drawCard();
                // Lổi
                // this.computerHitCard();
                // if ((this.isUserHit == true) && (checkChangeColor())) {
                //     Game.prevCard.setColor(this.chooseColor());
                // }
            }
        }
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

}
