import javax.swing.JLabel;

public class Game {
    static int COMPUTER_NUM = 4;

    static MyPanel mainPanel; // display users
    static Card prevCard;
    static Deck deck;
    static Player player;
    static Computers com;
    static boolean isReverse;

    Game() {
        mainPanel = new MyPanel();

        deck = new Deck();

        player = new Player(deck, "SOUTH");

        prevCard = deck.getOneCard();
        prevCard.setLocation(Deck.X + Card.WIDTH * 2, Deck.Y);

        addToMainPanel(deck);
        addToMainPanel(prevCard);

        com = new Computers(deck);

        isReverse = true; // chieu kim dong ho
    }

    public static void addToMainPanel(JLabel card) {
        mainPanel.add(card, Integer.valueOf(MyPanel.LAYER++));
    }

    public void playedCard() {
        // player.setTurn(true);
        com.getComputer(0).setTurn(true);

        // ? com.computerPlayed(0) ?
        com.computer0Played();
    }
}
