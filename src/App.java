import javax.swing.SwingUtilities;

public class App {
    static public MyFrame frame;
    static LoginPage panelLogin;
    static BeginPanel beginPage;
    static boolean isFirtGame = true;
    static Game game;

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
        frame = new MyFrame();
        // newGame();
        panelLogin = new LoginPage();
        beginPage = new BeginPanel("../resources/images/BeginBackround.jpg");
        frame.add(beginPage);
        // button uno
        frame.pack();
        frame.setVisible(true);
    }
}
