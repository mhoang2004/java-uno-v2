import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;

public class Game {
    final int COMPUTER_NUM = 3;
    static MyPanel mainPanel; // display users
    static Card prevCard;
    static Deck deck;
    static Player player;
    static boolean isReverse; // kiểm tra chiều bài đang đánh
    static ArrayList<Computer> com;
    static boolean isEndGame;
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
        isEndGame = false;
    }

    public static void addToMainPanel(JLabel card) {
        mainPanel.add(card, Integer.valueOf(MyPanel.LAYER++));
    }

    public static boolean getIsReverse() {
        return isReverse;
    }

    // Khi lá prevCard là lá đổi chiều, thực hiện đổi user tiếp theo
    public static void reverse() {
        if (isReverse == true) {
            com.get(2).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(0));
            com.get(0).setNextUser(player);
            player.setNextUser(com.get(2));
            isReverse = false; // ngược chiều kim đồng hồ
        } else {
            com.get(0).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(2));
            com.get(2).setNextUser(player);
            player.setNextUser(com.get(0));
            isReverse = true; // đúng chiều kim đồng hồ
        }
    }

    // Qua lượt đánh của user kế tiếp
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
    //     if(player.checkCard() == false)
    //     {
    //         // Game.addToMainPanel(new DrawCard());
    //     }else{
    //         player.setTurn(true);
    //     } 
    // }
    // Qua lượt đánh của user đối diện, trường hợp này pass qua 1 user là lúc sử
    // dụng lá skip
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

    // Set thời gian máy đánh chậm lại 2s, cho kịp nhìn
    public static void delayReverse(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextUser(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    // Set thời gian máy đánh chậm lại 2 giây cho trường hợp đánh ra lá skip
    public static void delaySkip(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oppositeUser(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
    
    // Máy đánh ra lá bài rồi chuyển qua user tiếp theo, check lá reverse, skip ở trong đây.
    public static void computerHit(int index) {
        if(com.get(index).getTurn() == false) return;
        com.get(index).computerTurn();
        if (endGame()) {
            isEndGame = true;
        }
        
        // REVERSE
        if ((Game.prevCard.getRank() == "REVERSE") && (com.get(index).isUserHit != false)) {
            reverse();
        }
        if(com.get(index).getNextUser().isPlayer() == true && !com.get(index).checkSkip()) {
            player.suggestedEffect();
        }
        com.get(index).getNextUser().setTurn(true);
        com.get(index).setTurn(false);
        // SKIP
        if ((com.get(index).checkSkip()) && (com.get(index).isUserHit != false)) {
            com.get(index).skip();
            if(index == 1)
            {
                player.suggestedEffect();
            }
            delaySkip(index);
            return;
        }
        delayReverse(index);
    }

    // Lượt đánh của máy 0
    public static void computer0Hit() {
        computerHit(0);
    }

    // Lượt đánh của máy 1
    public static void computer1Hit() {
        computerHit(1);
    }

    // Lượt đánh của máy 2
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
}
