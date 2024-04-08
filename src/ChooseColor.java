import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ChooseColor extends JLabel implements ActionListener {
    static int WIDTH = 500;
    static int HEIGHT = 50;

    ChooseColor() {
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        this.setLayout(new GridLayout());
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        createButton("BLUE");
        createButton("YELLOW");
        createButton("GREEN");
        createButton("RED");
    }

    private void createButton(String color) {
        JButton button = new JButton(color);

        if (color.equals("RED"))
            button.setBackground(Color.RED);
        else if (color.equals("BLUE"))
            button.setBackground(Color.BLUE);
        else if (color.equals("YELLOW"))
            button.setBackground(Color.YELLOW);
        else if (color.equals("GREEN"))
            button.setBackground(Color.GREEN);

        button.setForeground(Color.WHITE);
        button.setSize(WIDTH / 4, HEIGHT);

        button.addActionListener(this);
        
        this.add(button);
    }

    @Override
    // player click choose color
    public void actionPerformed(ActionEvent e) {
        // get the color
        String src = e.getActionCommand().charAt(0) + "";
        Game.prevCard.setColor(src);

        // remove this
        Game.mainPanel.remove(this);
        Game.mainPanel.repaint();

        // someone will draw card
        // if ((Game.prevCard.getRank() == "REVERSE") && (Game.player.isUserHit != false)) {
        //     Game.reverse();
        // }
        // Game.player.getNextUser().setTurn(true);
        // Game.player.setTurn(false);

        // // SKIP
        // if ((Game.player.checkSkip()) && (Game.player.isUserHit != false)) {
        //     Game.player.skip();
        //     Game.delaySkip(3);
        // }
        Game.delayReverse(3);
        if (Game.prevCard.getRank() == "DRAWFOUR") {
            Game.player.getNextUser().drawCard();
            Game.player.getNextUser().drawCard();
            Game.player.getNextUser().drawCard();
            Game.player.getNextUser().drawCard();

            Game.player.passTurn();
            Game.player.skip();
            Game.delaySkip(3);
        } else if (Game.prevCard.getRank() == "WILD"){ // WILD CARD
            Game.player.getNextUser().setTurn(true);
            Game.player.setTurn(false);
            Game.delayReverse(3);
        }
    }
}
