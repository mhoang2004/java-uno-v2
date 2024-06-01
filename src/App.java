import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

public class App {
    static public MyFrame frame;
    static BeginPanel beginPage;
    static boolean isFirtGame = true;
    static Game game;
    static LoginPanel loginPanel;
    static HomePanel homePanel;
    static RankPanel rankPanel;
    static SettingPanel setting;
    static String backroundGame;
    static boolean modeGuest = true;
    static String path = new String("../resources/images/");

    public static void newGame(String path, AccountUser accountUser)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (isFirtGame) {
            frame.remove(beginPage);
        }

        game = new Game(path, accountUser);
        App.frame.add(Game.mainPanel);
        game.start();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game.mainPanel.remove(Game.buttonUno);
                if (Game.isEndGame == true) {
                    try {
                        game = new Game(path, accountUser);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    game.start();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        backroundGame = new String("../resources/images/backgroundmain-2.jpg");
        frame = new MyFrame();
        frame.addKeyListener(game);
        // newGame();
        beginPage = new BeginPanel("../resources/images/BackroundBegin-2.jpg");

        frame.add(beginPage);
        // button uno
        frame.pack();
        frame.setVisible(true);
    }

    static void setBackground(String path) {
        backroundGame = new String(path);
    }

}
