import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
public class EndGame extends JLabel {
    static int WIDTH = 600;
    static int HEIGHT = 300;
    JButton playAgainBtn;
    JButton rankBtn;
    JLabel gameResult;
    JLabel gameScore;
    JLabel gameBestScore;
    JPanel gameBtns;

    EndGame() {
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        Game.clip.stop();
        if(Game.player.getCard().size()<2)
        {
            try {
                SoundControler.soundVicroty();
            } catch (LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            try {
                SoundControler.soundLose();
            } catch (LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        playAgainBtn = new JButton("Back To Home");
        if(App.modeGuest == true)
        {
            playAgainBtn.setText("Play Again");
        }
        playAgainBtn.setBackground(new Color(30, 194, 235));
        playAgainBtn.setFont(new Font("Arial", Font.BOLD, 30));
        playAgainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(App.modeGuest == true){
                    App.frame.setVisible(false);
                    App.frame.remove(Game.mainPanel);
                     try {
                        App.newGame(App.backroundGame);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    App.frame.setVisible(true); 
                }else{
                    App.frame.setVisible(false);
                    App.frame.add(App.homePanel);
                    App.frame.remove(Game.mainPanel);
                    App.frame.setVisible(true);
                }
               
                
            }
        });

        rankBtn = new JButton("Show Rank");
        rankBtn.setBackground(new Color(30, 194, 235));
        rankBtn.setFont(new Font("Arial", Font.BOLD, 30));
        rankBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implement here
            }
        });

        gameBtns = new JPanel();
        gameBtns.setLayout(new FlowLayout());
        gameBtns.add(playAgainBtn);
        gameBtns.add(rankBtn);

        gameResult = new JLabel("END GAME", SwingConstants.CENTER);
        if(Game.player.getCard().size()<2)
        {
            gameResult.setText("VICTORY");
        }else{
            gameResult.setText("LOSE");
        }
        gameResult.setFont(new Font("Arial", Font.BOLD, 30));
        gameResult.setOpaque(true);

        String scoreString = "Score: " + Game.player.scores();
        gameScore = new JLabel(scoreString, SwingConstants.CENTER);
        gameScore.setFont(new Font("Arial", Font.BOLD, 30));
        gameScore.setOpaque(true);

        String bestScoreString = "Best Score: 17";
        gameBestScore = new JLabel(bestScoreString, SwingConstants.CENTER);
        gameBestScore.setFont(new Font("Arial", Font.BOLD, 30));
        gameBestScore.setOpaque(true);

        gameScore.setBackground(new Color(30, 194, 235));

        this.setLayout(new GridLayout(4, 1));
        this.add(gameResult);
        this.add(gameScore);
        this.add(gameBestScore);
        this.add(gameBtns);
    }
}
