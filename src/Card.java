import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Cursor;

public class Card extends JLabel implements MouseListener, Comparable, ActionListener {
    static boolean isDrawOneCard = false;
    static final int WIDTH = 80;
    static final int HEIGHT = 120;
    public boolean isClicked = false;
    private String color;
    private String rank;
    private User user;
    public boolean isSuggest = false;
    static boolean isDragg = false;
    static int newX;
    static int newY;
    boolean isKey = false;
    Timer timer = new Timer(200, this);
    boolean bool = true;
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

    public boolean isKey() {
        return isKey;
    }

    void updateIsKey() {
        isKey = isKey == true ? false : true;
    }

    public void suggestedEffect() {
        isSuggest = true;
        setLocation(getX(), MyPanel.HEIGHT - 120);
        timer.start();
    }

    public Color getColorByBao() {
        if (this.color.equals("B")) {
            return Color.BLUE;
        }
        if (this.color.equals("R")) {
            return Color.RED;
        }
        if (this.color.equals("Y")) {
            return Color.YELLOW;
        }
        return Color.GREEN;
    }

    public static Card createCard(String color, String rank) {
        Card card = new Card(color, rank);
        return card;
    }

    public void removeEffect() {
        isSuggest = false;
        bool = true;
        timer.stop();
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

    public Boolean isSuperSpecial() {
        if (rank.equals("WILD") || rank.equals("DRAWFOUR"))
            return true;
        return false;
    }

    public String getColor() {
        return color;
    }

    public User getUser() {
        return user;
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

    public boolean isSkip() {
        if (this.getRank() == "SKIP") {
            return true;
        }
        if (this.getRank() == "DRAWTWO") {
            return true;
        }
        if (this.getRank() == "DRAWFOUR") {
            return true;
        }
        return false;
    }

    public void drawCardAnimationByBao(int xDeck, int yDeck, int x, int y) {
        Card tempCard = this;
        try {
            SoundControler.soundDraw();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        // Ending point
        final int x2;
        final int y2;
        x2 = x;
        y2 = y;

        Timer timer = new Timer(10, new ActionListener() {
            // Starting point
            int x1 = xDeck;
            int y1 = yDeck;

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
                    Timer time = new Timer(200, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tempCard.drawCardAnimation(Deck.X + 200, Deck.Y + 100);
                            ((Timer) e.getSource()).stop();
                        }

                    });

                    ((Timer) e.getSource()).stop();
                    time.start();
                }
            }
        });

        timer.start();
    }

    public void drawCardAnimationByBao2() {
        Card tempCard = this;
        try {
            SoundControler.soundDraw();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        tempCard.drawCardAnimationByBao(Deck.X, Deck.Y, Deck.X + 200, Deck.Y + 100);
        user.setCardsPosition();
        if (tempCard.user.checkValid(this) == false) {
            this.removeEffect();
        }
        timer.start();
    }

    public void drawCardAnimation(int x, int y) {
        Card tempCard = this;
        try {
            SoundControler.soundDraw();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        // Ending point
        final int x2;
        final int y2;
        String position = user.getPosition();

        if (position.equals("SOUTH") || position.equals("NORTH")) {
            if (position.equals("SOUTH")) {
                x2 = user.getXPos() + ((user.sortCard(this)) * User.GAP_CARD_HORIZONTAL);
            } else {
                x2 = user.getXPos() + (user.sizeCards() * User.GAP_CARD_HORIZONTAL);
            }
            y2 = user.getYPos();
        } else {
            x2 = user.getXPos();
            y2 = user.getYPos() + (user.sizeCards() * User.GAP_CARD_VERTICAL);
        }

        Timer timer = new Timer(5, new ActionListener() {
            // Starting point
            int x1 = x;
            int y1 = y;

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

                    if (isDrawOneCard && !user.isPlayer() && user.getTurn() == true) {
                        System.out.println("HELLLO");
                        Timer timer = new Timer(500, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ((Timer) e.getSource()).stop();
                                isDrawOneCard = false;
                                Card card = user.getLastCard();
                                System.out.println(card);

                                if (user.checkValid(card)) {
                                    Computer computer = (Computer) user;
                                    try {
                                        Game.hisComputerHit.put(computer.getPos(), computer.computerHitCard());
                                    } catch (UnsupportedAudioFileException | IOException
                                            | LineUnavailableException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                    Game.updatePrevCard();
                                    Game.deck.removeEffect();
                                    int index = Game.computerNumber(computer);
                                    // skip or draw cards

                                    if ((computer.isUserHit == true) && (computer.checkChangeColor())) {
                                        Game.prevCard.setColor(computer.chooseColor());
                                    }
                                    if (computer.endGame()) {
                                        Game.mainPanel.add(new EndGame(), Integer.valueOf(MyPanel.LAYER++));
                                    } else {
                                        if (Game.nextIsPlayer(index) == true) {
                                            Game.player.offFocus();
                                            Game.player.suggestedEffect();
                                            if (Game.player.checkCard() == false) {
                                                Game.deck.suggestedEffect();
                                            }
                                        }
                                        // REVERSE
                                        if ((Game.prevCard.getRank() == "REVERSE") && (computer.isUserHit != false)) {
                                            Game.reverse();
                                        }
                                        computer.getNextUser().setTurn(true);
                                        computer.setTurn(false);
                                        // SKIP
                                        if ((computer.checkSkip()) && (computer.isUserHit != false)) {
                                            computer.skip();
                                            Game.delaySkip(index);
                                            return;
                                        }
                                        Game.delayReverse(index);
                                    }
                                }
                            }

                        });
                        timer.start();

                    }

                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    }

    public void hitCardAnimation() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (this.isSpecial()) {
            SoundControler.soundHitSpecial();
        } else {
            SoundControler.soundHit();
        }

        Game.mainPanel.setLayer(this, MyPanel.LAYER++);

        Card tempCard = this;
        Timer timer = new Timer(10, new ActionListener() {
            // Starting point
            int x1 = tempCard.getX();
            int y1 = tempCard.getY();

            // Ending point
            int x2 = (MyPanel.WIDTH - Card.WIDTH) / 2;
            int y2 = (MyPanel.HEIGHT - Card.HEIGHT) / 2;

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
        addMouseMotionListener(new MouseAdapter() {
            int x, y;

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                isDragg = true;
            }
        });
        if (e.getClickCount() == 2 || (e.getClickCount() == 1 && isClicked && user.checkValid(this))) {

            try {
                processing();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if (e.getClickCount() == 1) {
            user.offFocus();
            isClicked = true;
            user.effectArroundClickCard();
            this.setLocation(this.getX(), MyPanel.HEIGHT - 150);
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    }

    public void backDefaultCard() {
        this.isClicked = false;
        this.setLocation(this.getX(), MyPanel.HEIGHT - 110);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void effectArround() {
        this.setLocation(this.getX(), MyPanel.HEIGHT - 130);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    void processing() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (Game.check(this)) {
            Game.setButtonUno();
            this.removeEffect();
            hitCard();
            Game.player.isUserHit = true;
            if (user.getCard().size() - 1 == 0) {
                Game.mainPanel.add(new EndGame(), Integer.valueOf(MyPanel.LAYER++));
            } else {
                if (this.getColor() == null) {
                    new ChooseColorPanel();
                } else {
                    Game.player.hitCard(this, Game.check(this));
                    Game.updatePrevCard();
                    Game.prevCard.setColor(this.getColor());
                    Game.prevCard.setRank(this.getRank());
                    Game.checkTheCase();
                }
                Game.displayNotification();
                Game.checkUno();

            }
            for (Card card : user.cards) {
                card.removeEffect();
            }
        }
    }

    void hitCard() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.removeMouseListener(this);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        Game.prevCard.setColor(this.getColor());
        Game.prevCard.setRank(this.getRank());

        this.hitCardAnimation();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // System.out.println("HELLLLLLO");

    }

    void addEvent() {
        this.addMouseMotionListener(new MouseAdapter() {
            int x, y;

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("HI");

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Card.isDragg = true;
                Card.newX = getX() + e.getX() - x;
                Card.newY = getY() + e.getY() - y;
                setBounds(Card.newX, Card.newY, Card.WIDTH, Card.HEIGHT);
            }
        });
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isDragg) {
            isDragg = false;
            if (user.checkValid(this) && user.getTurn() == true) {
                try {
                    processing();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                for (Card card : user.getCard()) {
                    card.backDefaultCard();
                    card.isClicked = false;
                    if (user.checkValid(card) && user.getTurn() == true) {
                        card.isSuggest = true;
                        Border border = new LineBorder(Color.YELLOW, 5);
                        card.setBorder(border);
                    }
                }
                this.drawCardAnimation(newX, newY);
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("1111");
    }

    @Override
    public int compareTo(Object o) {
        Card card2 = (Card) o;
        return this.rank.compareTo(card2.rank);
    }

    boolean equal(Object o) {
        Card card2 = (Card) o;
        return this.rank.equals(card2.rank);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (bool == true) {
            bool = false;
            Border border = new LineBorder(Color.YELLOW, 5);
            setBorder(border);

        } else {
            bool = true;
            Border border = new LineBorder(Color.YELLOW, 0);
            setBorder(border);

        }
    }
}
