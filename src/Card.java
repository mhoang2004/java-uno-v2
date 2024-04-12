import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Cursor;

public class Card extends JLabel implements MouseListener {
    static boolean isDrawOneCard = false;
    static final int WIDTH = 80;
    static final int HEIGHT = 120;
    public boolean isClicked = false;
    private String color;
    private String rank;
    private User user;
    private int address;
    public boolean isSuggest = false;
    // BACK CARD

    Card() {
        super();
        color = null;
        rank = "BACK";
        setCard();
    }

    Card(Card x) {
        super();
        this.color = x.color;
        this.rank = x.rank;
        this.setCard();
    }

    Card(String color, String rank) {
        super();
        this.color = color;
        this.rank = rank;
        setCard();
    }
    public void setAddress(int address)
    {
        this.address = address;
        
    }
    public void setCard() {
        String path = "../resources/cards/";
        if (color != null) {
            path += color + "-";
        }
        path += rank + ".png";
        this.setIcon(new ImageIcon(path));
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setSize(Card.WIDTH, Card.HEIGHT);
    }

    public void suggestedEffect() {
        isSuggest = true;
        Border border = new LineBorder(Color.YELLOW, 5);
        setBorder(border);
        this.setLocation(this.getX(), MyPanel.HEIGHT - 120);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void removeEffect() {
        isSuggest = false;
        setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return color + "-" + rank;
    }

    public void assignCard(Card card) {
        this.color = card.getColor();
        this.rank = card.getRank();
        this.setIcon(card.getIcon());
    }

    public Boolean isSpecial() {
        if (rank.length() > 1)
            return true;
        return false;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public boolean isSkip()
    {
        if(this.getRank() == "SKIP")
        {
            return true;
        }
        if(this.getRank() == "DRAWTWO")
        {
            return true;
        }
        if(this.getRank() == "DRAWFOUR")
        {
            return true;
        }
        return false;
    }

    public void drawCardAnimation() {
        Card tempCard = this;

        // Ending point
        final int x2;
        final int y2;
        String position = user.getPosition();

        if (position.equals("SOUTH") || position.equals("NORTH")) {
            x2 = user.getXPos() + (user.sizeCards() * User.GAP_CARD_HORIZONTAL);
            y2 = user.getYPos();
        } else {
            x2 = user.getXPos();
            y2 = user.getYPos() + (user.sizeCards() * User.GAP_CARD_VERTICAL);
        }

        Timer timer = new Timer(10, new ActionListener() {
            // Starting point
            int x1 = Deck.X;
            int y1 = Deck.Y;

            int dx = x2 - x1;
            int dy = y2 - y1;

            int distance = (int) Math.sqrt(dx * dx + dy * dy);
            int velocity = 10;
            int steps = (int) Math.ceil(distance / velocity);

            int dxStep = (int) dx / steps;
            int dyStep = (int) dy / steps;

            int countStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (countStep < steps) {
                    x1 += dxStep;
                    y1 += dyStep;
                    countStep++;
                    tempCard.setLocation(x1, y1);
                    tempCard.repaint();
                } else {
                    // handle others things
                    user.setCardsPosition();

                    if (isDrawOneCard && !user.isPlayer) {

                        isDrawOneCard = false;
                        Card card = user.getLastCard();
                        System.out.println(card);

                        if (user.checkValid(card)) {
                            Computer computer = (Computer) user;
                            Game.hisComputerHit.put(computer.getPos(),computer.computerHitCard());
                            Game.updatePrevCard();
                            int index = Game.computerNumber(computer);
                            // skip or draw cards

                            if ((computer.isUserHit == true) && (computer.checkChangeColor())) {
                                Game.prevCard.setColor(computer.chooseColor());
                            }
                                if (computer.endGame()) {
                                    Game.addToMainPanel(new EndGame());
                                } else {
                                // REVERSE
                                if ((Game.prevCard.getRank() == "REVERSE") && (computer.isUserHit != false)) {
                                    Game.reverse();
                                }
                                // if (computer.getNextUser().isPlayer() == true && !computer.checkSkip()) {
                                //     player.suggestedEffect();
                                // }
                                computer.getNextUser().setTurn(true);
                                computer.setTurn(false);
                                // SKIP
                                if ((computer.checkSkip()) && (computer.isUserHit != false)) {
                                    computer.skip();
                                    // if (index == 1) {
                                    //     player.suggestedEffect();
                                    // }
                                    Game.delaySkip(index);
                                    return;
                                }
                                Game.delayReverse(index);
                            }
                        }
                    }

                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    }

    public void hitCardAnimation() {
        
        Game.mainPanel.setLayer(this, MyPanel.LAYER++);

        Card tempCard = this;
        Timer timer = new Timer(1, new ActionListener() {
            // Starting point
            int x1 = tempCard.getX();
            int y1 = tempCard.getY();

            // Ending point
            int x2 = Deck.X + 2 * Card.WIDTH;
            int y2 = Deck.Y;

            int dx = x2 - x1;
            int dy = y2 - y1;

            int distance = (int) Math.sqrt(dx * dx + dy * dy);
            int velocity = 10;
            int steps = (int) Math.ceil(distance / velocity);

            int dxStep = (int) dx / steps;
            int dyStep = (int) dy / steps;

            int countStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (countStep < steps) {
                    x1 += dxStep;
                    y1 += dyStep;
                    countStep++;
                } else {
                    // stuff handle after animation
                    user.hitCard(tempCard, true);
                    if ((tempCard.color == null) && (user.getIsPlayer())) {
                        // choose color
                        

                    }

                    ((Timer) e.getSource()).stop();
                }

                tempCard.setLocation(x1, y1);
                tempCard.repaint();
            }
        });

        timer.start();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2 ||(e.getClickCount() ==1 && isClicked&& user.checkValid(this)))
        {
            process();
        }
        if(e.getClickCount() ==1)
        {
            if(this.isClicked == false)
            {
                user.offFocus();
                isClicked = true;
                user.effectArroundClickCard();
                
                this.setLocation(this.getX(), MyPanel.HEIGHT - 140);
                this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
            }
        }
        
    }
    public void backDefaultCard()
    {
        this.isClicked = false;
        this.setLocation(this.getX(), MyPanel.HEIGHT - 100);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    public void effectArround()
    {
        this.setLocation(this.getX(), MyPanel.HEIGHT - 120);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    void process()
    {
        if (Game.check(this)) 
        {
            Game.setButtonUno();
            this.removeEffect();
            hitCard(); 
            System.out.println(this.user.getCard().size());
            if(this.user.getCard().size() - 1 == 0){
                new EndGame();
            }
            Game.player.isUserHit = true;   
            if (user.endGame())
            {
                Game.addToMainPanel(new EndGame());

            }else{
                if (this.getColor() == null) {
                    Game.addToMainPanel(new ChooseColor());
                    // Game.updatePrevCard();   
                } else 
                {                 
                    Game.player.hitCard(this, Game.check(this));
                    
                    Game.prevCard.setColor(this.getColor());
                    Game.prevCard.setRank(this.getRank());   
                    Game.checkTheCase();
                } 
                Game.displayButtonUno();
                Game.checkUno(); 
                
                
            }
            for(Card card : user.cards)
                {
                    card.removeEffect();
                }
        }   
    }
    void hitCard() {
        this.removeMouseListener(this);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        Game.prevCard.setColor(this.getColor());
        Game.prevCard.setRank(this.getRank());
        
        this.hitCardAnimation();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}
