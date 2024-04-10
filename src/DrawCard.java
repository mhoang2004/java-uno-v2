import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;


public class DrawCard extends JLabel implements ActionListener{
    Card cardDrawn;
    static int WIDTH = 250;
    static int HEIGHT = 50;
    private  JButton buttonTrue ;

    private  JButton buttonFalse;
    DrawCard(Card cardDrawn)
    {
        // Game.mainPanel.remove(Game.buttonUno);
        this.cardDrawn = cardDrawn;
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2+150, WIDTH, HEIGHT);
        buttonTrue = new JButton("KEEP");
        buttonFalse = new JButton("PLAY");
        buttonTrue.setBackground(new Color(30, 194, 235));
        buttonFalse.setBackground(new Color(30, 194, 235));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel button = new JLabel();
        button.setLayout(new GridLayout(1,2));
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
        // while(true)
        // {
            
            
        // }
        }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String src = e.getActionCommand();
        if(src.equals("PLAY"))
        {
                Game.player.setTurn(true);
                cardDrawn.hitCard();
                if(cardDrawn.getColor() == null)
                {
                    ChooseColor chooseColor = new ChooseColor();
                    Game.addToMainPanel(chooseColor);
                }else{
                    Game.checkTheCase();
                }
        }
        Game.deck.setEnabled(true);
        Game.mainPanel.setEnabled(true);
        Game.mainPanel.remove(this);
        Game.mainPanel.repaint();
        // Game.mainPanel.remove(Game.vector);        
        Game.addToMainPanel(Game.buttonUno);    
    }
        
}
