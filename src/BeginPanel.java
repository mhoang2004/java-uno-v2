
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class BeginPanel extends MyPanel {
    int widthLogin = 230;
    int heigthLogin = 40;
    // Properti for BeginPanel
    JLabel buttonLogin;
    JLabel buttonNoLogin;
    ArrayList<String> stringList;
    JLabel next1;
    JLabel next2;
    Timer timer;
    BeginPanel(String path) {
        super(path);
        createButtonLogin();
        createButtonNoLogin();
        createLabel();
        next1 = new JLabel();
        next1.setBounds(330, 480, 78, 88);
        next2 = new JLabel();
        next2.setBounds(800,480 , 78, 88);
        add(next1);
        add(next2);
    }
    void createLabel()
    {
        
        JLabel welcomJLabel = new JLabel();
        
        // welcomJLabel.setIcon(new ImageIcon("../resources/images/IMG-Sign-in.png"));
        JLabel toJLabel = new JLabel("TO");
        toJLabel.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 40));
        JLabel subWelcomJLabel = new JLabel("");
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
                    timer.stop();
                    App.newGame();
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

}
