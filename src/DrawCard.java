import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DrawCard extends JLabel implements ActionListener{
    Card cardDrawn;
    static int WIDTH = 500;
    static int HEIGHT = 50;
    private  JButton buttonTrue ;
    private JTextArea noti;
    private  JButton buttonFalse;
    DrawCard()
    {
        cardDrawn = Game.player.drawCard();
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
         noti = new JTextArea("Bạn muốn giữ lá bài vừa rút hay đánh nó ?????");
         buttonTrue = new JButton("OK");
         buttonFalse = new JButton("NO");
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel button = new JLabel();
        button.setLayout(new GridLayout(1,2));
        button.add(buttonTrue);
        button.add(buttonFalse);
        buttonTrue.addActionListener(this);
        buttonFalse.addActionListener(this);

        this.setLayout(new GridLayout(2, 1));
        this.add(noti);
        this.add(button);
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        


        if(src.equals("OK"))
        {
            if(Game.player.checkValid(cardDrawn) == true){
                Game.player.setTurn(true);
                cardDrawn.hitCard();
                
            }
        }
        Game.player.getNextUser().setTurn(true);
        Game.player.setTurn(false);
        Game.nextUser(3);
    }
    
    
}
