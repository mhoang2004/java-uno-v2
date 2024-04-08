import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EndGame extends JLabel implements ActionListener{
    static int WIDTH = 400;
    static int HEIGHT =100;
    JButton buttonOK;
    JLabel labelEndGame ;
    EndGame()
    {
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        buttonOK = new JButton("OK");
        buttonOK.setFont(new Font("Arial", Font.BOLD, 30));
        buttonOK.setBackground(new Color(30, 194, 235));
        labelEndGame = new JLabel("END GAME", SwingConstants.CENTER);
        
        labelEndGame.setFont(new Font("Arial", Font.BOLD, 50));
        labelEndGame.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        labelEndGame.setOpaque(true);
        labelEndGame.setBackground(new Color(30, 194, 235));
        this.setLayout(new GridLayout(2, 1));
        this.add(labelEndGame);
        this.add(buttonOK);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Game.mainPanel.setVisible(false);
    }
    
}
