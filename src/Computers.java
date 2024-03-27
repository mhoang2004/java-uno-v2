import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Computers {
    final int COMPUTER_NUM = 3;
    private ArrayList<Computer> com;
    private boolean isTurnPlayer;

    Computers(Deck deck) {
        com = new ArrayList<>();
        com.add(new Computer(deck, "WEST"));
        com.add(new Computer(deck, "NORTH"));
        com.add(new Computer(deck, "EAST"));

        // demo
        // com.get(0).drawCard();
        // com.get(1).drawCard();
        // com.get(2).drawCard();

        // set next user
        com.get(0).setNextUser(com.get(1));
        com.get(1).setNextUser(com.get(2));
    }

    public Computer getComputer(int index) {
        return com.get(index);
    }

    // REVERSE
    public void reverse() {
        if (Game.isReverse == true) {
            com.get(2).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(0));
            Game.isReverse = false; // nguoc chieu kim dong ho
        } else {
            com.get(0).setNextUser(com.get(1));
            com.get(1).setNextUser(com.get(2));
            Game.isReverse = true; // dung chieu kim dong ho
        }
    }

    // Check skip
    public boolean checkSkip() {
        if (Game.prevCard.getRank() == "SKIP") {
            return true;
        } else if (Game.prevCard.getRank() == "DRAWTWO") {
            return true;
        } else if (Game.prevCard.getRank() == "DRAWFOUR") {
            return true;
        }
        return false;
    }

    public void nextComputer(int index) {
        if (Game.isReverse == true) {
            if (index == 0) {
                computer1Played();
            } else if (index == 1) {
                computer2Played();
            }
        } else if (Game.isReverse == false) {
            if (index == 2) {
                computer1Played();
            } else if (index == 1) {
                computer0Played();
            }
        }
    }

    public void oppositeComputer(int index) {
        if (index == 0) {
            computer2Played();
        } else if (index == 2) {
            computer0Played();
        }
    }

    public void delaySkip(int index) {
        Timer timer = new Timer(5000, new ActionListener() {
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
        // com.get(index).nextUser.setTurn(true);
        com.get(index).setTurn(false);

        // SKIP
        if ((checkSkip()) && (com.get(index).isPlayedCard != false)) {
            com.get(index).skip();
            delaySkip(index);
            return;
        }

        if (Game.isReverse == true) {
            delayReverse(index);
        } else {
            delayReverse(index);
        }
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
}
