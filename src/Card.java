import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Cursor;

public class Card extends JLabel implements MouseListener {
    static final int WIDTH = 80;
    static final int HEIGHT = 120;

    private String color;
    private String rank;
    private User user;

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
        Border border = new LineBorder(Color.YELLOW, 5);
        setBorder(border);
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
        System.out.println("prevCard: " + Game.prevCard.toString());
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
                        Game.addToMainPanel(new ChooseColor());
                        
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
        if (Game.check(this)) {
            hitCard(); 

            if (this.getColor() == null) {
    
            } else {
                
                Game.player.hitCard(this, Game.check(this));
                Game.prevCard.setColor(this.getColor());
                Game.prevCard.setRank(this.getRank());
                Game.player.isUserHit = true;
                if (Game.endGame()) {
                    Game.isEndGame = true;
                }
                // REVERSE
                if ((Game.prevCard.getRank() == "REVERSE") && (Game.player.isUserHit != false)) {
                    Game.reverse();
                }
                Game.player.getNextUser().setTurn(true);
                Game.player.setTurn(false);
    
                // SKIP
                if ((Game.player.checkSkip()) && (Game.player.isUserHit != false)) {
                    Game.player.skip();
                    Game.delaySkip(3);
                }
                Game.delayReverse(3);
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
        this.setLocation(this.getX(), MyPanel.HEIGHT - 120);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setLocation(this.getX(), MyPanel.HEIGHT - 100);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

}
