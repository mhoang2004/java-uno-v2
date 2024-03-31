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
    private boolean isReverse; // kiểm tra chiều bài đang đánh
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

        isReverse = true; // chiều kim đồng hồ
    }

    public static void addToMainPanel(JLabel card) {
        mainPanel.add(card, Integer.valueOf(MyPanel.LAYER++));
    }

    // Khi lá prevCard là lá đổi chiều, thực hiện đổi user tiếp theo
    public void reverse() {
        if (this.isReverse == true) {
            com.get(2).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(0));
            com.get(0).setNextUser(player);
            player.setNextUser(com.get(2));
            this.isReverse = false; // ngược chiều kim đồng hồ
        } else {
            com.get(0).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(2));
            com.get(2).setNextUser(player);
            player.setNextUser(com.get(0));
            this.isReverse = true; // đúng chiều kim đồng hồ
        }
    }
    // Qua lượt đánh của user kế tiếp
    public void nextComputer(int index) {
        if (this.isReverse == true) {
            if (index == 0) {
                computer1Hit();
            } else if (index == 1) {
                computer2Hit();
            } else if (index == 2) {
                player.setTurn(true);
            } else if (index == 3) {
                computer0Hit();
            }
        } else if (this.isReverse == false) {
            if (index == 3) {
                computer2Hit();
            } else if (index == 2) {
                computer1Hit();
            } else if (index == 1) {
                computer0Hit();
            } else if (index == 0) {
                player.setTurn(true);
            }
        } 
    }
    // Qua lượt đánh của user đối diện, trường hợp này pass qua 1 user là lúc sử dụng lá skip
    public void oppositeComputer(int index) {
        if (index == 0) {
            computer2Hit();
        } else if (index == 1) {
            player.setTurn(true);
        } else if (index == 2) {
            computer0Hit();
        } else if (index == 3) {
            computer1Hit();
        }
    }
    // Set thời gian máy đánh chậm lại 2s, cho kịp nhìn
    public void delayReverse(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextComputer(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
    // Set thời gian máy đánh chậm lại 2 giây nhưng cho trường hợp đánh ra lá skip
    public void delaySkip(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oppositeComputer(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
    // Máy đánh ra lá bài rồi chuyển qua user tiếp theo, check lá reverse, skip ở trong đây.
    public void computerHit(int index) {
        com.get(index).computerTurn();
        // REVERSE
        if ((Game.prevCard.getRank() == "REVERSE") && (com.get(index).isUserHit != false)) {
            this.reverse();
        }
        com.get(index).nextUser.setTurn(true);
        com.get(index).setTurn(false);
        // SKIP
        if ((com.get(index).checkSkip()) && (com.get(index).isUserHit != false)) {
            com.get(index).skip();
            delaySkip(index);
            return;
        }
        delayReverse(index);
    }
    // Lượt đánh của máy 0
    public void computer0Hit() {
        computerHit(0);
    }
    // Lượt đánh của máy 1
    public void computer1Hit() {
        computerHit(1);
    }
    // Lượt đánh của máy 2
    public void computer2Hit() {
        computerHit(2);
    }
    public static boolean check (Card card)
    {
        return !(player.getTurn() == false) && player.checkValid(card);
    }
    // Lượt đánh của player
    public boolean playerHit(Card card) {
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
            player.isUserHit = true;
            // REVERSE
            // System.out.println("hiiiiii");
            if ((Game.prevCard.getRank() == "REVERSE") && (player.isUserHit != false)) {
                this.reverse();
            }
            player.getNextUser().setTurn(true);
            player.setTurn(false);
            // SKIP
            if ((player.checkSkip()) && (player.isUserHit != false)) {
                player.skip();
                delaySkip(3);
                return true;
            }
            delayReverse(3);  
            return true;  
              
    }
    // bắt sự kiện chuột click vào 1 lá bài của player
    public void mouseClicked() { 
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
                        if(playerHit( player.cards.get(i)))
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
