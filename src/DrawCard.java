import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawCard extends JLabel implements ActionListener {
    Card cardDrawn;
    static int WIDTH = 400;
    static int HEIGHT = 60;
    private JButton buttonTrue;

    private JButton buttonFalse;

    DrawCard(Card cardDrawn) {
        this.cardDrawn = cardDrawn;
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        buttonTrue = new JButton("KEEP");
        buttonFalse = new JButton("PLAY");
        buttonTrue.setBackground(new Color(30, 194, 235));
        buttonFalse.setBackground(new Color(30, 194, 235));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel button = new JLabel();
        button.setLayout(new GridLayout(1, 2));
        button.add(buttonTrue);
        button.add(buttonFalse);
        buttonTrue.addActionListener(this);
        buttonFalse.addActionListener(this);
        buttonTrue.setFont(new Font("Arial", Font.BOLD, 20));
        buttonFalse.setFont(new Font("Arial", Font.BOLD, 20));
        this.setLayout(new GridLayout(1, 2));
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        // this.add(noti);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("PLAY")) {
            if (Game.player.checkValid(cardDrawn) == true) {
                Game.player.setTurn(true);
                cardDrawn.hitCard();

            }
        }
        Game.deck.setEnabled(true);
        Game.mainPanel.setEnabled(true);
        Game.mainPanel.remove(this);
        Game.mainPanel.repaint();
        if (cardDrawn.getColor() != null) {
            Game.player.getNextUser().setTurn(true);
            // Game.player.setTurn(false);
            Game.delayReverse(3);
        }

    }

}
