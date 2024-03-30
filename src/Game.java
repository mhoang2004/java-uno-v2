import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game{
    final int COMPUTER_NUM = 3;
    static MyPanel mainPanel; // display users
    static Card prevCard;
    static Deck deck;
    static Player player;
    private boolean isReverse;
    private ArrayList<Computer> com;
    //private boolean isTurnPlayer;
    Game() {
        mainPanel = new MyPanel();

        deck = new Deck();

        player = new Player(deck, "SOUTH");

        prevCard = deck.getOneCard();
        while (prevCard.isSpecial())
        {
            prevCard = deck.getOneCard();
        }
        prevCard.setLocation(Deck.X + Card.WIDTH * 2, Deck.Y);

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

        isReverse = true; // chieu kim dong ho
    }

    public static void addToMainPanel(JLabel card) {
        mainPanel.add(card, Integer.valueOf(MyPanel.LAYER++));
    }

    // REVERSE
    public void reverse() {
        if (this.isReverse == true) {
            com.get(2).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(0));
            com.get(0).setNextUser(player);
            player.setNextUser(com.get(2));
            this.isReverse = false; // nguoc chieu kim dong ho
        } else {
            com.get(0).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(2));
            com.get(2).setNextUser(player);
            player.setNextUser(com.get(0));
            this.isReverse = true; // dung chieu kim dong ho
        }
    }
    public void nextComputer(int index) {
        if (this.isReverse == true) {
            if (index == 0) {
                computer1Played();
            } else if (index == 1) {
                computer2Played();
            } else if (index == 2) {
                player.setTurn(true);
            } else if (index == 3) {
                computer0Played();
            }
        } else if (this.isReverse == false) {
            if (index == 3) {
                computer2Played();
            } else if (index == 2) {
                computer1Played();
            } else if (index == 1) {
                computer0Played();
            } else if (index == 0) {
                player.setTurn(true);
            }
        } 
    }

    public void oppositeComputer(int index) {
        if (index == 0) {
            computer2Played();
        } else if (index == 1) {
            player.setTurn(true);
        } else if (index == 2) {
            computer0Played();
        } else if (index == 3) {
            computer1Played();
        }
    }

    public void delaySkip(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oppositeComputer(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    public void delayReverse(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextComputer(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    public void computerPlayed(int index) {
        com.get(index).computerTurn();
        // REVERSE
        if ((Game.prevCard.getRank() == "REVERSE") && (com.get(index).isPlayedCard != false)) {
            this.reverse();
        }
        com.get(index).nextUser.setTurn(true);
        com.get(index).setTurn(false);
        // SKIP
        if ((com.get(index).checkSkip()) && (com.get(index).isPlayedCard != false)) {
            com.get(index).skip();
            delaySkip(index);
            return;
        }
        delayReverse(index);
    }

    public void computer0Played() {
        computerPlayed(0);
    }

    public void computer1Played() {
        computerPlayed(1);
    }

    public void computer2Played() {
        computerPlayed(2);
    }
    public static boolean check (Card card)
    {
        return !(player.getTurn() == false) && player.checkValid(card);
    }
    public boolean playerPlayed(Card card) {
            System.out.println(check(card));
            if(!check(card))
            {
                return false; 
            }

            System.out.println(check(card));
                if (player.checkWild()) {
                    player.wild();
                }
                player.hitCard(card, check(card));
                prevCard.assignCard(card);
            player.isPlayedCard = true;
            // REVERSE
            // System.out.println("hiiiiii");
            if ((Game.prevCard.getRank() == "REVERSE") && (player.isPlayedCard != false)) {
                this.reverse();
            }
            player.getNextUser().setTurn(true);
            player.setTurn(false);
            // SKIP
            if ((player.checkSkip()) && (player.isPlayedCard != false)) {
                player.skip();
                delaySkip(3);
                return true;
            }
            delayReverse(3);  
            return true;  
              
    }

    public void isMouseClicked() {
        player.setTurn(true);
        
        MouseListener mouseListener = new MouseAdapter() 
        {     
           
            
            @Override
            public void mouseClicked(MouseEvent e) 
            { 
                int size = player.cards.size();
                boolean check = true;
                for (int i=0; i< size ; i++)
                {
                    if (e.getSource() == player.cards.get(i))
                    {
                        if(playerPlayed( player.cards.get(i)))
                        {
                            size--;
                        }
                    }
                }         
            }
        };
        
        
        for (Card card : player.cards) {
            card.addMouseListener(mouseListener);
        }
    }
}
