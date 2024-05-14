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
    static SettingPanel setting;
    static String backroundGame;
    static boolean modeGuest = true;
    public static void newGame(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (isFirtGame) {
            frame.remove(beginPage);
        }

        game = new Game(path);
        App.frame.add(Game.mainPanel);
        game.start();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game.mainPanel.remove(Game.buttonUno);
                if (Game.isEndGame == true) {
                   
                    try {
                        game = new Game(path);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    game.start();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        backroundGame = new String("../resources/images/backgroundmain-0.jpg");
        frame = new MyFrame();
        frame.addKeyListener(game);
        homePanel = new HomePanel("../resources/images/BackroundBegin-1.jpg");
        // newGame();
        beginPage = new BeginPanel("../resources/images/BackroundBegin-1.jpg");
        setting = new SettingPanel("../resources/images/BackroundBegin-1.jpg");
        frame.add(beginPage);
        // button uno
        frame.pack();
        frame.setVisible(true);
    }
    static void setBackground(String path)
    {
        backroundGame = new String(path);
    }
}
