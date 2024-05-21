
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;



public class DrawCardPanel{
    Card cardDrawn;
    static int WIDTH = 150;
    static int HEIGHT = 40;
    private  JLabel buttonTrue ;
    private  JLabel buttonFalse;
    int steps =1;
    double x2;
    int y2;
    Timer timer;
    DrawCardPanel(Card cardDrawn)
    {
        // Game.mainPanel.remove(Game.buttonUno);
        this.cardDrawn = cardDrawn;
        x2 = cardDrawn.getUser().getXPos() + ((cardDrawn.getUser().sortCard(cardDrawn)) * User.GAP_CARD_HORIZONTAL)-50;
        y2 = cardDrawn.getUser().getYPos();
        buttonTrue = new JLabel();
        buttonFalse = new JLabel();
       createButton(buttonTrue, true);
       createButton(buttonFalse, false);
       Game.addToMainPanel(buttonTrue);
       Game.addToMainPanel(buttonFalse);
    }
    private void createButton(JLabel button, boolean isPlay)
    {
        ImageIcon img;
        if(isPlay)
        {
            button.setBounds((MyPanel.WIDTH - WIDTH) / 2 -100, (MyPanel.HEIGHT - HEIGHT) / 2+150, WIDTH, HEIGHT);
             img = new ImageIcon(App.path + "Play.png");
            
        }else{
            button.setBounds((MyPanel.WIDTH - WIDTH) / 2 + WIDTH +20-100, (MyPanel.HEIGHT - HEIGHT) / 2+150, WIDTH, HEIGHT);

             img = new ImageIcon(App.path + "Keep.png");
        }
        button.setIcon(img);
        button.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        button.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
       JLabel buttonTmp = button;
      
        button.addMouseListener(new MouseAdapter() {
        int x= (MyPanel.WIDTH - WIDTH) / 2 + WIDTH +20-100;
        int y=(MyPanel.HEIGHT - HEIGHT) / 2+150;
            @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
         if(buttonTmp.getX()==(MyPanel.WIDTH - WIDTH) / 2-100)
            {
                Timer timer = new Timer(20, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(steps ==1)
                        {
                            if(x > (MyPanel.WIDTH - WIDTH) / 2 -100)
                            {
                              x -=10;
                             buttonFalse.setBounds(x, (MyPanel.HEIGHT - HEIGHT) / 2+150, WIDTH, HEIGHT);
                            }else
                            {
                                x= (MyPanel.WIDTH - WIDTH) / 2 -100;
                                steps++;
                                buttonFalse.setIcon(null);
                                buttonTrue.setIcon(new ImageIcon(App.path+"Set.png"));
                                buttonTrue.setBounds(x, y,30 , 30);
                                ((Timer) e.getSource()).stop();
                                Timer timer2 = new Timer(10, new ActionListener() {
                                   int  x3= (MyPanel.WIDTH - WIDTH) / 2 -100;
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(x3< x2)
                                        {
                                        x3+=10;
                                        double a = (130/(x2 -465))*x3 + (490-60450/(x2-465));
                                        y = (int) a;
                                        buttonTrue.setBounds(x3,y, WIDTH, HEIGHT);
                                        }else{
                                            steps++;
                                            buttonTrue.setIcon(null);
                                            buttonTrue.setBounds((MyPanel.WIDTH - WIDTH) / 2 -100, (MyPanel.HEIGHT - HEIGHT) / 2+150, WIDTH, HEIGHT);
                                            buttonFalse.setBounds((MyPanel.WIDTH - WIDTH) / 2 + WIDTH +20-100, (MyPanel.HEIGHT - HEIGHT) / 2+150, WIDTH, HEIGHT);
                                            ((Timer) e.getSource()).stop();
                                            Game.player.setTurn(true);
                                            Game.deck.setEnabled(true);
                                            try {
                                                cardDrawn.processing();
                                            } catch (UnsupportedAudioFileException | IOException
                                                    | LineUnavailableException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                            }
                                    }
                                    
                                });
                                timer2.start();
                            }
                        }
                }
                    
                });
                timer.start();
                    
            }else{
                Game.deck.setEnabled(true);
                
                remove();
               Game.checkTheCase();
            }
               
        }
       
        });
        
    }
    void remove()
    {
        buttonTrue.setIcon(null);
        buttonFalse.setIcon(null);
    }
        
}
