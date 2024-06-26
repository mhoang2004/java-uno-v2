import java.io.IOException;
import java.util.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Computer extends User {
    private ArrayList<Card> backCards;
    private Map<String, Integer> mpColor;
    private Map<String, Integer> mpRank;
    static int x1 = -MyPanel.WIDTH;
    static int gap = 10;
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
        // init Map
        mpColor = new HashMap<>();
        mpRank = new HashMap<>();
    }

    public void addElement(Map<String, Integer> map, String element) {
        if (element != null) {
            if (map.containsKey(element)) {
                int count = map.get(element);
                map.put(element, count + 1);
            } else {
                map.put(element, 1);
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card drawCard() {
        if(position.equals("NORTH") && !Game.modeSolo)
        {
            return drawCard2() ;
        }
        Card card = Game.deck.getOneCard();   
        cards.add(card);

        Card backCard = new Card();

        backCard.setLocation(Deck.X, Deck.Y);
        backCard.setUser(this);

        Game.addToMainPanel(backCard);

        backCards.add(backCard);

        backCard.drawCardAnimation(Deck.X, Deck.Y);
        // System.out.println("Draw card " + card);
        return card;
    }
    public Card drawCard2() {
        if (Game.deck.getDeck().size() == 0) {
            Game.deck.createDeck();
        }

        Card card = Game.deck.getOneCard();
        if(Game.hisComputerHit.size() != 0)
        {
            while(checkValid(card))
            {
                card = Game.deck.getOneCard();
            }
        }

        
        // card.setLocation(Deck.X, Deck.Y);
        card.setUser(this);
        Game.addToMainPanel(card);

        // cards.add(card);
        cards.add(sortCard(card),card);
        for (Card card2 : cards) {
            Game.mainPanel.remove(card2);
        }
        for (Card card1 : cards) {
            Game.addToMainPanel(card1);
        }
        Game.mainPanel.repaint();
        card.drawCardAnimation(Deck.X, Deck.Y);
        return card;
    }
    public void setCardsPosition() {
        setUserPosition();
        int xPadding = 0;
        int yPadding = 0;
        if(position == "NORTH" && !Game.modeSolo)
        {
            for (Card card : cards) {
                card.setLocation(xPos + xPadding, yPos + yPadding);
    
                if (position == "NORTH") {
                    xPadding += GAP_CARD_HORIZONTAL; 
                    
                } else {
                    yPadding += GAP_CARD_VERTICAL; 
                }
            }
        }
        
        for (Card backCard : backCards) {
            backCard.setLocation(xPos + xPadding, yPos + yPadding);

            if (position == "NORTH") {
                xPadding += GAP_CARD_HORIZONTAL;
            } else {
                yPadding += GAP_CARD_VERTICAL;
            }
        }
    }

    // Check prevCard is wild or drawfour
    public boolean checkChangeColor() {
        if (Game.prevCard.getRank() == "WILD") {
            return true;
        } else if (Game.prevCard.getRank() == "DRAWFOUR") {
            return true;
        }

        return false;
    }

    public String getMaxValue(Map<String, Integer> map) {
        return map.entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    public String getMinValue(Map<String, Integer> map) {
        return map.entrySet().stream().min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    // Computer choose color when play wild or drawfour
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

    public Card getKeyMaxValue(String rank) {
        if (mpRank.containsKey(rank)) {
            if (mpRank.get(rank) == 1) {
                for (Card card : cards)
                    if (card.getRank() == rank)
                        return card;
            } else {
                Map<String, Integer> mpRankColor = new HashMap<>();
                for (Card card : cards)
                    if (card.getRank() == rank)
                        this.addElement(mpRankColor, card.getColor());
                for (Card card : cards)
                    if (card.getColor() == this.getMaxValue(mpRankColor) && card.getRank() == Game.prevCard.getRank())
                        return card;
            }
        }
        return null;
    }

    public Card getKeyMaxValueSpecial(String rank) {
        if (mpRank.containsKey(rank)) {
            if (Game.prevCard.getRank() != rank) {
                for (Card card : cards) {
                    if (card.getRank() == rank && card.getColor() == Game.prevCard.getColor())
                        return card;
                }
            } else {
                Card res = this.getKeyMaxValue(rank);
                if (res != null)
                    return res;
            }
        }
        return null;
    }

    // Computer select one card to play
    public Card validCard() {
        this.isUserHit = false;
        // test skip
        // for (Card card : cards) {
        // if (this.checkValid(card) == true && card.getRank().length() > 1) {
        // this.isUserHit = true;
        // return card;
        // }
        // }
        boolean isHaveCard = false; // check player have card to play
        for (Card card : cards) {
            if (this.checkValid(card) == true) {
                isHaveCard = true;
                if (card.getColor() == Game.prevCard.getColor())
                    addElement(mpColor, card.getColor());
                if (card.getRank() == Game.prevCard.getRank())
                    addElement(mpRank, card.getRank());
            }
        }
        if (isHaveCard == false)
            return null;
        // return card drawTwo or drawFour if next user have a lot of card
        if (this.getNextUser().sizeCards() <= 2) {
            Card res = this.getKeyMaxValueSpecial("DRAWTWO");
            if (res != null) {
                this.isUserHit = true;
                return res;
            } else if (mpRank.containsKey("DRAWFOUR")) {
                for (Card card : cards)
                    if (card.getRank() == "DRAWFOUR") {
                        this.isUserHit = true;
                        return card;
                    }
            }
        } else {
            // getRank.length == 1
            Map<String, Integer> mpRank1 = new HashMap<>();
            for (Card card : cards) {
                if (card.getRank().length() == 1 && card.getColor() == Game.prevCard.getColor())
                    addElement(mpRank1, card.getRank());
            }
            if (mpRank1.size() != 0) {
                for (Card card : cards) {
                    if (mpRank1.containsKey(card.getRank()))
                        addElement(mpRank1, card.getRank());
                }
                for (Card card : cards) {
                    if (card.getColor() == Game.prevCard.getColor() && card.getRank() == this.getMaxValue(mpRank1)) {
                        this.isUserHit = true;
                        return card;
                    }
                }
            } else {
                Map<String, Integer> mpColor1 = new HashMap<>();
                for (Card card : cards) {
                    if (card.getRank().length() == 1) {
                        if (card.getRank() == Game.prevCard.getRank()) {
                            addElement(mpColor1, card.getColor());
                        }
                    }
                }
                for (Card card : cards) {
                    if (mpColor1.containsKey(card.getColor()))
                        addElement(mpColor1, card.getColor());
                }
                for (Card card : cards) {
                    if (card.getRank() == Game.prevCard.getRank() && card.getColor() == this.getMaxValue(mpColor1)) {
                        this.isUserHit = true;
                        return card;
                    }
                }
            }
            // getRank.length > 1, reverse, skip, drawtwo
            Card res = this.getKeyMaxValueSpecial("REVERSE");
            if (res != null) {
                this.isUserHit = true;
                return res;
            }
            res = this.getKeyMaxValueSpecial("SKIP");
            if (res != null) {
                this.isUserHit = true;
                return res;
            }
            res = this.getKeyMaxValueSpecial("DRAWTWO");
            if (res != null) {
                this.isUserHit = true;
                return res;
            }
            if (mpRank.containsKey("WILD")) {
                for (Card card : cards)
                    if (card.getRank() == "WILD") {
                        this.isUserHit = true;
                        return card;
                    }
            } else if (mpRank.containsKey("DRAWFOUR")) {
                for (Card card : cards)
                    if (card.getRank() == "DRAWFOUR") {
                        this.isUserHit = true;
                        return card;
                    }
            }

        }
        for (Card card : cards) {
            if (this.checkValid(card) == true) {
                this.isUserHit = true;
                return card;
            }
        }
        return null;
    }

    // Computer play this card selected
    public Card computerHitCard() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Card validCard = validCard(); // todo: function LogicComputerHit
        Card chosenCard = null;

        if (validCard != null) {
            int index = cards.indexOf(validCard);
            if(!Game.modeSolo)
            {
                if(position == "NORTH"){
                    chosenCard = cards.get(index);
                }else{
                    chosenCard = backCards.get(index);
                }
            }else{
                chosenCard = backCards.get(index);

            }
            
            chosenCard.assignCard(validCard);

            Game.prevCard.setColor(validCard.getColor());
            Game.prevCard.setRank(validCard.getRank());
            if (checkChangeColor()) {
                Game.prevCard.setColor(this.chooseColor());
                // Game.prevCard = new Card(cardHit.getColor(),cardHit.getRank());

            }
            System.out.println(Game.prevCard);
            if(chosenCard.isSuperSpecial())
            {
                chosenCard.setCard(Game.prevCard.getColor(),Game.prevCard.getRank());
            }
            chosenCard.hitCardAnimation();
            
            if (this.getCards().size() - 1 == 0) {
                    if(Game.modeSolo &&this.position == "NORTH")
                    {
                        Game.mainPanel.add(new EndGame("VICTORY"), Integer.valueOf(MyPanel.LAYER++));
                    }else{
                        Game.mainPanel.add(new EndGame("LOSE"), Integer.valueOf(MyPanel.LAYER++));
                    }
                    
                    }
            if(!Game.modeSolo)
            {
                if(position == "NORTH"){
                    

                }else{
                    backCards.remove(index);
                }
            }else{
                backCards.remove(index);

            }
           
            cards.remove(index);
           
            Game.updatePrevCard();
            // if (chosenCard.getColor() == null) {
            // Game.prevCard.setColor(cards.get(0).getColor());
            // Game.prevCard.setRank(chosenCard.getRank());
            // }
        }
        return validCard;
    }

    // Change color prevcard if computer play card is wild or drawfour
    // if dont have card then play this card when it can play
    public Card computerTurn() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Card cardHit = this.computerHitCard();
        
        if (this.isUserHit == true) {
            
        } else if (this.isUserHit == false) {
            Card cardDraw = this.drawCard();
            System.out.println(cardDraw);
            if(checkValid(cardDraw))
            {
                
                Card.isDrawOneCard = true;
                Card newCard = new Card("-1", "-1");
                return newCard;
            }
            Card.isDrawOneCard = true;
        }
        
        return cardHit;
    }

    public void checkUno() {
        if (sizeCards() == 1) {
            unoAnimation();
            try {
                SoundControler.soundUno();
            } catch (LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    protected void offFocus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'offFocus'");
    }

    @Override
    protected void effectArroundClickCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'effectArroundClickCard'");
    }
    
}
