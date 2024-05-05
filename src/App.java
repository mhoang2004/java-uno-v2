import javax.swing.SwingUtilities;

public class App {
    static public MyFrame frame;
    static BeginPanel beginPage;
    static boolean isFirtGame = true;
    static Game game;
    static LoginPanel loginPanel;
    
    public static void newGame() {
        if (isFirtGame) {
            frame.remove(beginPage);
        }

        game = new Game();
        App.frame.add(Game.mainPanel);
        game.start();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game.mainPanel.remove(Game.buttonUno);
                if (Game.isEndGame == true) {
                    Game game = new Game();
                    game.start();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        FileHandler.addNewUserData("Giabaoonthcs123@gmail.com", "Giabao123@");
        frame = new MyFrame();
        // newGame();
        beginPage = new BeginPanel("../resources/images/backgroundmain-2.jpg");
        frame.add(beginPage);
        // button uno
        frame.pack();
        frame.setVisible(true);
    }
    
}
