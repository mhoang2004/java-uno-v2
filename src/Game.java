import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;

public class Game {
    final int COMPUTER_NUM = 3;
    static MyPanel mainPanel; // display users
    static Card prevCard;
    static Deck deck;
    static Player player;
    static boolean isReverse; // direction
    static ArrayList<Computer> com;
    static boolean isEndGame;
    static ButtonUno buttonUno;
    // private boolean isTurnPlayer;

    Game() {

        mainPanel = new MyPanel();

        deck = new Deck();

        player = new Player(deck, "SOUTH");

        prevCard = deck.getOneCard();
        while (prevCard.isSpecial()) {
            prevCard = deck.getOneCard();
        }
        while (player.checkValid(prevCard) == false) {
            prevCard = deck.getOneCard();
        }
        prevCard.setLocation(Deck.X + Card.WIDTH * 2, Deck.Y);

        buttonUno = new ButtonUno();
        buttonUno.addMouseListener(buttonUno);

        addToMainPanel(deck);
        addToMainPanel(prevCard);

        com = new ArrayList<>();
        com.add(new Computer(deck, "WEST"));
        com.add(new Computer(deck, "NORTH"));
        com.add(new Computer(deck, "EAST"));

        // set next user
        com.get(0).setNextUser(com.get(1));
        com.get(1).setNextUser(com.get(2));
        com.get(2).setNextUser(player);
        player.setNextUser(com.get(0));

        isReverse = true; // clockwise
        isEndGame = false;
    }

    public static void addToMainPanel(JLabel card) {
        mainPanel.add(card, Integer.valueOf(MyPanel.LAYER++));
    }

    public static boolean getIsReverse() {
        return isReverse;
    }

    // When prevCard is reverse, change next user
    public static void reverse() {
        if (isReverse == true) {
            com.get(2).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(0));
            com.get(0).setNextUser(player);
            player.setNextUser(com.get(2));
            isReverse = false; // counter-clockwise
        } else {
            com.get(0).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(2));
            com.get(2).setNextUser(player);
            player.setNextUser(com.get(0));
            isReverse = true; // clockwise
        }
    }

    // Skip next user
    public static void nextUser(int index) {
        if (isReverse == true) {
            if (index == 0) {
                computer1Hit();
            } else if (index == 1) {
                computer2Hit();
            } else if (index == 2) {

            } else if (index == 3) {
                computer0Hit();
            }
        } else if (isReverse == false) {
            if (index == 3) {
                computer2Hit();
            } else if (index == 2) {
                computer1Hit();
            } else if (index == 1) {
                computer0Hit();
            } else if (index == 0) {

            }
        }
    }

    // public static void checkDrawCard()
    // {
    // if(player.checkCard() == false)
    // {
    // // Game.addToMainPanel(new DrawCard());
    // }else{
    // player.setTurn(true);
    // }
    // }
    // Pass turn opposite user, this case pass a user when use skip card

    public static void oppositeUser(int index) {
        if (index == 0) {
            computer2Hit();
        } else if (index == 1) {

        } else if (index == 2) {
            computer0Hit();
        } else if (index == 3) {
            computer1Hit();
        }
    }

    // Set computer`s time, delay 2s
    public static void delayReverse(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextUser(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    // Set computer`s time, delay 2s, this case pass a user when use skip card
    public static void delaySkip(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oppositeUser(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    // Computer play card, pass next user, check reverse, skip this here
    public static void computerHit(int index) {
        if (com.get(index).getTurn() == false)
            return;
        com.get(index).computerTurn();
        if (com.get(index).endGame()) {
            Game.addToMainPanel(new EndGame());
        } else {
            // REVERSE
            if ((Game.prevCard.getRank() == "REVERSE") && (com.get(index).isUserHit != false)) {
                reverse();
            }
            if (com.get(index).getNextUser().isPlayer() == true && !com.get(index).checkSkip()) {
                player.suggestedEffect();
            }
            com.get(index).getNextUser().setTurn(true);
            com.get(index).setTurn(false);
            // SKIP
            if ((com.get(index).checkSkip()) && (com.get(index).isUserHit != false)) {
                com.get(index).skip();
                if (index == 1) {
                    player.suggestedEffect();
                }
                delaySkip(index);
                return;
            }
            delayReverse(index);
        }
    }

    // Turn`s computer 0
    public static void computer0Hit() {
        computerHit(0);
    }

    // Turn`s computer 1
    public static void computer1Hit() {
        computerHit(1);
    }

    // Turn`s computer 2
    public static void computer2Hit() {
        computerHit(2);
    }

    public static boolean check(Card card) {
        return !(player.getTurn() == false) && player.checkValid(card);
    }

    public void start() {
        player.suggestedEffect();
        player.setTurn(true);
    }

    public static boolean endGame() {
        return (player.sizeCards() == 0 || com.get(0).sizeCards() == 0 ||
                com.get(1).sizeCards() == 0 || com.get(2).sizeCards() == 0);
    }

    public static void displayButtonUno() {
        if (player.sizeCards() <= 2) {
            addToMainPanel(buttonUno);
        }
    }

    public static void setButtonUno() {
        buttonUno.setUnoClicked();
    }

    // check player click button uno
    public static void checkUno() {
        if (player.sizeCards() == 1) {
            // wait player 3s to click button uno
            Timer timer = new Timer(3000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (buttonUno.getUnoClicked() == false) {
                        System.out.println("Please click button uno. You must draw card");
                        player.drawCard();
                    }
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();
        }
    }
}
