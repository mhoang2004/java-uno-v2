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
        this.setLayout(new GridLayout(4, 1));
        Game.clip.stop();
        if (Game.player.sizeCards() == 1) {
            try {
                SoundControler.soundVicroty();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        } else {
            try {
                SoundControler.soundLose();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        if (App.modeGuest == true) {
            playAgainBtn = new JButton("Play Again");
        } else {
            playAgainBtn = new JButton("Back To Home");
        }
        Game.stop();
        playAgainBtn.setBackground(new Color(30, 194, 235));
        playAgainBtn.setFont(new Font("Arial", Font.BOLD, 30));
        playAgainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.frame.setVisible(false);
                App.frame.remove(Game.mainPanel);
                if (App.modeGuest) {
                    try {
                        App.newGame(App.backroundGame, Game.accountUser);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    App.homePanel = new HomePanel("../resources/images/BackroundBegin-1.jpg", LoginPanel.accountUser);
                    App.frame.add(App.homePanel);
                }
                App.frame.setVisible(true);
            }
        });

        rankBtn = new JButton("Show Rank");
        rankBtn.setBackground(new Color(30, 194, 235));
        rankBtn.setFont(new Font("Arial", Font.BOLD, 30));
        rankBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.frame.setVisible(false);
                App.frame.remove(Game.mainPanel);
                App.rankPanel = new RankPanel();
                App.frame.add(App.rankPanel);
                App.frame.setVisible(true);
            }
        });

        gameBtns = new JPanel();
        gameBtns.setLayout(new FlowLayout());
        gameBtns.add(playAgainBtn);
        gameBtns.add(rankBtn);

        int currentScore = 0;
        gameResult = new JLabel("END GAME", SwingConstants.CENTER);

        if (Game.player.sizeCards() == 1) {
            gameResult.setText("VICTORY");
            currentScore = Game.player.scores();
        } else {
            gameResult.setText("LOSE");
        }

        gameResult.setFont(new Font("Arial", Font.BOLD, 30));
        gameResult.setOpaque(true);
        this.add(gameResult);

        String scoreString = "Score: " + currentScore;
        gameScore = new JLabel(scoreString, SwingConstants.CENTER);
        gameScore.setFont(new Font("Arial", Font.BOLD, 30));
        gameScore.setOpaque(true);
        gameScore.setBackground(new Color(30, 194, 235));
        this.add(gameScore);

        if (App.modeGuest != true) {
            String emailString = Game.accountUser.getMail();
            int bestScore = FileHandler.getBestScore(emailString);
            System.out.println("bestScore: " + bestScore + "currentScore: " + currentScore);
            if (currentScore > bestScore) {
                FileHandler.updateBestScoreByEmail(emailString, currentScore);
            }

            bestScore = FileHandler.getBestScore(emailString);
            String bestScoreString = "Best Score: " + bestScore;
            gameBestScore = new JLabel(bestScoreString, SwingConstants.CENTER);
            gameBestScore.setFont(new Font("Arial", Font.BOLD, 30));
            gameBestScore.setOpaque(true);
            this.add(gameBestScore);
        }

        this.add(gameBtns);
    }
}
