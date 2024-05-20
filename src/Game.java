import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;

import java.io.IOException;
import javax.sound.sampled.*;
public class Game implements KeyListener {
    final int COMPUTER_NUM = 3;
    static MyPanel mainPanel; // display users
    static Card prevCard;
    static Deck deck;
    static Player player;
    static boolean isReverse; // direction
    static ArrayList<Computer> com;
    static boolean isEndGame;
    static ButtonUno buttonUno;
    static Notification notiToUser;
    static Reverse vectorLeft;
    static Reverse vectorRight;
    static HashMap<Integer, Card> hisComputerHit;
    Timer timer;
    static Clip clip;
    // private boolean isTurnPlayer;
    static AccountUser accountUser;
       
    Game(String path, AccountUser accountUser) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //sound 
        File file = new File("../resources/sounds/mainSound.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        this.accountUser = accountUser;
        if (accountUser != null) {
            if (!accountUser.getIsOn()) {
                clip.stop();
            }
            SoundControler.setIsON(accountUser.getIsOn());
        }
       
        mainPanel = new MyPanel(path);
        
        // Game.mainPanel.remove(goLabel);
        deck = new Deck();
        hisComputerHit = new HashMap<Integer, Card>();
        player = new Player(deck, "SOUTH");
        prevCard = new Card("G", "WILD");
        while (prevCard.isSpecial()) {
            prevCard = deck.getOneCard();
        }
        isReverse = true;
        vectorLeft = new Reverse("L");
        vectorRight = new Reverse("R");
        addToMainPanel(vectorLeft);
        addToMainPanel(vectorRight);
        prevCard.setLocation((MyPanel.WIDTH - Card.WIDTH) / 2, (MyPanel.HEIGHT - Card.HEIGHT) / 2);

        buttonUno = new ButtonUno();
        buttonUno.addMouseListener(buttonUno);
        notiToUser = new Notification();

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
            player.reverseAnimation();
            com.get(2).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(0));
            com.get(0).setNextUser(player);
            player.setNextUser(com.get(2));
            isReverse = false; // counter-clockwise
        } else {
            player.reverseAnimation();
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
        Game.notiToUser.removeText();
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
        updatePrevCard();
       // deck.removeEffect();
        Game.notiToUser.removeText();
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
        updatePrevCard();
        if(Game.notiToUser != null)
        {
            Game.notiToUser.removeText();
        }
        
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oppositeUser(index);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
// check user is player or computer
public static boolean nextIsPlayer(int index)
{
    System.out.println("revers: " +isReverse);
    if(index == 1)
    {
        if(hisComputerHit.get(index) == null)
            {
                return false;
            }
        if(prevCard.isSkip() == true)
        {
            return true;
        }
        return false;
    }
    if(index ==2)
    {
        if(Game.isReverse == true)
        {
            if(hisComputerHit.get(index) == null)
            {
                return true;
            }
           
            if(hisComputerHit.get(index).getRank().equals("Reverse"))
            {
                return false;
            }
            if(hisComputerHit.get(index).isSkip() == true)
            {
                return false;
            }
        }else{
            if(hisComputerHit.get(index) == null)
            {
                return false;
            }
            if(hisComputerHit.get(index).getRank().equals("Reverse"))
            {
                return true;
            }
        }    
    }
    if (index == 0)
    {
        if(Game.isReverse == true)
        {
            if(index ==0)
            {
                if(hisComputerHit.get(index) == null)
                {
                    return false;
                }
                if(hisComputerHit.get(index).getRank().equals("Reverse"))
                {
                    return true;
                } 
            }
        }else{
            if(hisComputerHit.get(index) == null)
            {
                return true;
            }
            if(hisComputerHit.get(index).getRank().equals("Reverse"))
            {
                return false;
            } 
            if(hisComputerHit.get(index).isSkip()== true)
            {
                return false;
            }
        }
    }
    return false;
}
   
   
    // Computer play card, pass next user, check reverse, skip this here
    public static void computerHit(int index) {
        // player.offFocus();
        if (com.get(index).getTurn() == false)
            return;
        try {
            hisComputerHit.put(index, com.get(index).computerTurn());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        com.get(index).checkUno();
        if(nextIsPlayer(index) == true)
        {
            player.offFocus();
            player.suggestedEffect();
            if(player.checkCard() == false)
            {
                System.out.println("HEEELOOOO");
                deck.suggestedEffect();
            }
        }  
        System.out.println(hisComputerHit.toString());
        updatePrevCard();
        if (com.get(index).endGame()) {
            Game.addToMainPanel(new EndGame());
        } else {
            
            // REVERSE
            if ((Game.prevCard.getRank() == "REVERSE") && (com.get(index).isUserHit != false)) {
                reverse();
            }
            updatePrevCard();
            if (com.get(index).getNextUser().isPlayer() == true && !com.get(index).checkSkip()) {
                player.suggestedEffect();
            }
            com.get(index).getNextUser().setTurn(true);
            com.get(index).setTurn(false);
            // SKIP
            if ((com.get(index).checkSkip()) && (com.get(index).isUserHit != false)) {
                com.get(index).skip();
                delaySkip(index);
                return;
            }
            delayReverse(index);
        }
    }
    
    // Turn`s computer 0
    public static void computer0Hit() {
        // updatePrevCard();
        computerHit(0);
    }

    // Turn`s computer 1
    public static void computer1Hit() {
        // updatePrevCard();
        computerHit(1);
    }

    // Turn`s computer 2
    public static void computer2Hit() {
        // updatePrevCard();
        computerHit(2);
    }

    public static boolean check(Card card) {
        return !(player.getTurn() == false) && player.checkValid(card);
    }

    public void start() {
        player.suggestedEffect();
        player.setTurn(true);
        if(player.checkCard() == false)
            {
                deck.suggestedEffect();
            }
    }
    //Update PrevCard
    public static void updatePrevCard()
    {
        vectorLeft.updateReverse("L");
        vectorRight.updateReverse("R");
    }
    public static boolean endGame() {
        return (player.sizeCards() == 0 || com.get(0).sizeCards() == 0 ||
                com.get(1).sizeCards() == 0 || com.get(2).sizeCards() == 0);
    }

    public static void displayNotification() {
        if (player.sizeCards() <= 2) {
            addToMainPanel(buttonUno);
        }
    }

    public static void displayText() {
        addToMainPanel(notiToUser);
    }

    public static void setButtonUno() {
        buttonUno.setUnoClicked();
    }

    // check player click button uno
    public static void checkUno() {
        if (player.sizeCards() == 1) {
            System.out.println(buttonUno.getUnoClicked());
            // wait player 3s to click button uno
            Timer timer = new Timer(3000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (buttonUno.getUnoClicked() == false) {
                        displayText();
                        notiToUser.setText("Successfully caught a missed UNO call. You needs to draw 2 cards.");
                        player.drawCard();
                        player.drawCard();
                        notiToUser.removeText();
                    }
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.start();
        }
    }
    // What computer number
    public static int computerNumber(Computer computer) {
        if (computer.equals(com.get(0))) return 0;
        else if (computer.equals(com.get(1))) return 1;
        else if (computer.equals(com.get(2))) return 2;
        else return -1;
    }
    // check the case is special
    public static void checkTheCase()
    {
        // REVERSE
        if ((Game.prevCard.getRank() == "REVERSE") && (Game.player.isUserHit != false)) {
            Game.reverse();
        }
        Game.player.getNextUser().setTurn(true);
        Game.player.setTurn(false);
        for (Card card : player.getCard()) {
            card.backDefaultCard();
        }
        // SKIP
        if (((Game.player.checkSkip() )&& (Game.player.isUserHit != false)) ) {
            Game.player.skip();
            Game.delaySkip(3);
        }       
        Game.delayReverse(3);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // : <
        // System.out.println("HI");
        // boolean isAction = false;
        // for(int i=0; i< player.getCard().size(); i++)
        // {
        //     if(player.getCard().get(i).isKey())
        //     {
        //         isAction = true;
        //     }
        // }
        // if(isAction == false)
        // {
        //     player.getCard().get(0).updateIsKey();
        //         player.offFocus();
        //         player.getCard().get(0).isClicked = true;
        //         player.effectArroundClickCard();               
        //         player.getCard().get(0).setLocation(player.getCard().get(0).getX(), MyPanel.HEIGHT - 150);
        //         player.getCard().get(0).setCursor(new Cursor(Cursor.HAND_CURSOR));  
        // }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    static void stop()
    {
        player.setTurn(false);
        for(int i=0; i< 3; i++)
        {
            com.get(i).setTurn(false);
        }
    }
}
