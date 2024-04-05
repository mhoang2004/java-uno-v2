public class App {
    static MyFrame frame;

    public static void main(String[] args) throws Exception {
        frame = new MyFrame();
        Game game = new Game();

        game.start();
        
        frame.add(Game.mainPanel);
        // button uno
        frame.pack();
        frame.setVisible(true);
    }
}
