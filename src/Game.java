public class Game {
    static int COMPUTER_NUM = 4;

    static MyPanel mainPanel; // display users
    static Card prevCard;
    static Computer com1;

    Game() {
        mainPanel = new MyPanel();

        Deck deck = new Deck();

        Player player = new Player(deck, "SOUTH");

        prevCard = deck.getOneCard();
        prevCard.setLocation(Deck.X + Card.WIDTH * 2, Deck.Y);

        mainPanel.add(deck);
        mainPanel.add(prevCard);

        com1 = new Computer(deck, "NORTH");

        Computer com2 = new Computer(deck, "WEST");
        Computer com3 = new Computer(deck, "EAST");

        com1.computerHitCard();
        com2.computerHitCard();
        com3.computerHitCard();
    }
}
