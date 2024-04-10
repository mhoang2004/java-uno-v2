import javax.swing.SwingUtilities;

public class App {
    static MyFrame frame;

    public static void main(String[] args) throws Exception {
        frame = new MyFrame();
        Game game = new Game();

        game.start();
        // game.addToMainPanel(new EndGame());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (Game.isEndGame == true) {
                    Game game = new Game();
                    game.start();
                }
            }
        });

        frame.add(Game.mainPanel);
        
        // button uno
        frame.pack();
        frame.setVisible(true);
    }
}
