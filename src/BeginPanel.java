
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class BeginPanel extends MyPanel {
    int widthLogin = 230;
    int heigthLogin = 40;
    Timer timer2;
    JLabel welcomJLabel;
    JLabel toJLabel;
    JLabel subWelcomJLabel;
    // Properti for BeginPanel
    JLabel buttonLogin;
    JLabel buttonNoLogin;
    ArrayList<String> stringList;
    JLabel next1;
    JLabel next2;
    Timer timer;
    static String linkImg ;
    String pathBackround;
    JLayeredPane goLabel;
    JLabel textJLabel;
    JLabel animationLabel;
    int count =2;
    boolean isOut = false;
    BeginPanel(String path) {
        super(path);
        linkImg = new String(path);
        createButtonLogin();
        createButtonNoLogin();
        createLabel();
        next1 = new JLabel();
        next1.setBounds(330, 480, 78, 88);
        next2 = new JLabel();
        next2.setBounds(830,480 , 78, 88);
        add(next1);
        add(next2);
        animationLabel = new JLabel(new ImageIcon(pathBackround));
        animationLabel.setBounds(-MyPanel.WIDTH,0, MyPanel.WIDTH+50, MyPanel.HEIGHT);
        //add(animationLabel);
        goLabel = new JLayeredPane();
        goLabel.setBounds(-200,MyPanel.HEIGHT/2, 100, 100);

        textJLabel= new JLabel(count+1+"");
        textJLabel.setBounds(35,20,50, 50 );
        textJLabel.setForeground(Color.YELLOW);
        textJLabel.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 30));
        goLabel.add(textJLabel);
        ImageIcon roundedIconx = new ImageIcon(drawButtonNext());
        JLabel backround = new JLabel(roundedIconx);
        backround.setBounds(0,0,100, 100 );
        goLabel.add(backround);
        //add(goLabel);
    }
    BufferedImage drawButtonNext() {
        BufferedImage roundedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D  g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(100, 70, 0, 200) );
        g2d.fillRoundRect(0, 0,100, 100, 50,50);

        g2d.dispose();  
        return roundedImage;
}  
    void createLabel()
    {
        
        welcomJLabel = new JLabel();
        
        // welcomJLabel.setIcon(new ImageIcon("../resources/images/IMG-Sign-in.png"));
         toJLabel = new JLabel("TO");
        toJLabel.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 40));
         subWelcomJLabel = new JLabel("");
        welcomJLabel.setIcon(new ImageIcon("../resources/images/WELCOME.png"));
        welcomJLabel.setBounds(350, 70,600, 70);
        addToMainPanel(welcomJLabel);
        Timer timer2 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random numbeRandom = new Random();
                // welcomJLabel.setForeground(new Color(numbeRandom.nextInt(256), numbeRandom.nextInt(256), numbeRandom.nextInt(256)));
                // toJLabel.setForeground(new Color(numbeRandom.nextInt(256), numbeRandom.nextInt(256), numbeRandom.nextInt(256)));
                subWelcomJLabel.setForeground(new Color(numbeRandom.nextInt(256), numbeRandom.nextInt(256), numbeRandom.nextInt(256)));
           
           
            }
            
        });
        timer2.start();
       
        toJLabel.setForeground(Color.WHITE);
        toJLabel.setBounds(620, 150,500, 50);
        addToMainPanel(toJLabel);
        // animation
       
        subWelcomJLabel.setForeground(Color.RED);
        subWelcomJLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 60));
        subWelcomJLabel.setBounds(430, 200,700, 70);
        addToMainPanel(subWelcomJLabel);
        
        stringList = new ArrayList<String>(); 
        stringList.add("J"); 
        stringList.add("JU");
        stringList.add("JUN");
        stringList.add("JUNO");
        stringList.add("JUNO G");
        stringList.add("JUNO GA");
        stringList.add("JUNO GAM");
        stringList.add("JUNO GAME");
      timer = new Timer(100, new ActionListener() {
            int count =0;
            boolean isEnd = false;
            boolean isBegin = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count == -1)
                    {
                        subWelcomJLabel.setText("");
                        count ++;
                        isEnd= false;
                    } else{
                        if(count< stringList.size()&& isEnd == false)
                        {
         
                             subWelcomJLabel.setText(stringList.get(count));
                             count++;
                        }else{
                         isEnd = true; 
                         if(count == stringList.size())
                         {
                             count--;
                             
                         }
                         
                         subWelcomJLabel.setText(stringList.get(count));
                         count--;
                         
         
                        }    
                    }      
                
            }

        });
        timer.start();
        
    }

    private void createButtonLogin()
    {
        buttonLogin = new JLabel();
        buttonLogin.setIcon(new ImageIcon("../resources/images/IMG-Sign-in.png"));
        buttonLogin.setBounds(270, 550, widthLogin, heigthLogin+30);
        
        addToMainPanel(buttonLogin);
        addListener(buttonLogin);
    }
    private void createButtonNoLogin()
    {
        buttonNoLogin = new JLabel();  
        buttonNoLogin.setIcon(new ImageIcon("../resources/images/IMG-No-Sign-in.png"));    
        buttonNoLogin.setBounds(750, 550,  widthLogin+30, heigthLogin+30);   
        addToMainPanel(buttonNoLogin);   
        addListener(buttonNoLogin);
    }

    public void addListener(JLabel button) {
        button.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            
                if (button.getLocation().getX() ==270 ) {
                    timer.stop();
                    System.out.println("Clicking   ");
                    App.frame.setVisible(false);
                    App.frame.remove(App.beginPage);
                    App.loginPanel=new LoginPanel();
                    App.frame.add(App.loginPanel);
                    // App.frame = new MyFrame();
                    App.frame.setVisible(true);
                } else{
                    App.frame.remove(App.beginPage);
                    try {
                        App.newGame(App.backroundGame);
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    // timer.stop();
                    // timer= new Timer(10, new ActionListener() {
                    //     int x= -MyPanel.WIDTH;
                    //     int y=0;
                    //     @Override
                    //     public void actionPerformed(ActionEvent e) {
                    //         if(x <-50)
                    //         {
                    //             App.beginPage.removeComponent();
                    //             x+=50;
                    //             animationLabel.setBounds(x, 0, MyPanel.WIDTH+50, MyPanel.HEIGHT);
                    //             // App.homePanel.setBounds(x+MyPanel.WIDTH, 0, MyPanel.WIDTH, MyPanel.HEIGHT);
                    //         }else {
                               
                    //             App.beginPage.removeComponent();
                    //             animationLabel.setBounds(x, 0, MyPanel.WIDTH+50, MyPanel.HEIGHT);
                              
                    //             goLabel.setBounds(0,0, 200, 200);
                               
                    //             timer2 = new Timer(1000, new ActionListener() {
                                    
                    //                 @Override
                    //                 public void actionPerformed(ActionEvent e) {
                    //                    if(count >=0)
                    //                    {
                    //                     textJLabel.setText(count+"");
                                      
                    //                     count --;
                    //                    }else{
                    //                     if(!isOut)
                    //                     {
                    //                         isOut = true;
                    //                         timer2.stop();
                    //                         App.frame.remove(App.beginPage);
                    //                         App.newGame(pathBackround);
                    //                     }
                                        
                    //                    }
    
                    //                 }
                                    
                    //             });
                    //             timer2.start();
                    //         }
                    //     }
                        
                    // });
                    // timer.start();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
 

                    if (button.getLocation().getX() ==270)
                    {
                        next1.setIcon(new ImageIcon("../resources/images/here.png"));
                        
                    }else{
                        next2.setIcon(new ImageIcon("../resources/images/here.png"));
                    }
                 
                
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button.getLocation().getX() ==270)
                {
                    next1.setIcon(null);
                }else{
                    next2.setIcon(null);
                }
                if(buttonLogin.getText().length() > 1)
                {
                
                }    
            }

        });

    }

    public void addToMainPanel(JLabel card) {
        this.add(card, Integer.valueOf(MyPanel.LAYER++));
    }
    void removeComponent()
    {
        this.remove(buttonLogin);
        this.remove(buttonNoLogin);
        this.remove(welcomJLabel);
        this.remove(toJLabel);
        this.remove(subWelcomJLabel);
        this.remove(next1);
        this.remove(next2);
    }
}
