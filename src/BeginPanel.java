
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
        next2.setBounds(900,480 , 78, 88);
        add(next1);
        add(next2);
    }
    void createLabel()
    {
        
        JLabel welcomJLabel = new JLabel("WELCOM");
        JLabel toJLabel = new JLabel("TO");
        JLabel subWelcomJLabel = new JLabel("");
        welcomJLabel.setForeground(Color.RED);
        welcomJLabel.setFont(new Font(null, Font.BOLD, 70));
        welcomJLabel.setBounds(470, 70,500, 70);
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
       
        toJLabel.setForeground(Color.RED);
        toJLabel.setFont(new Font(null, Font.BOLD, 45));
        toJLabel.setBounds(600, 150,500, 50);
        addToMainPanel(toJLabel);
        // animation
       
        subWelcomJLabel.setForeground(Color.RED);
        subWelcomJLabel.setFont(new Font(null, Font.BOLD, 45));
        subWelcomJLabel.setBounds(480, 200,500, 50);
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
                
                System.out.println(count);
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
        buttonLogin = new JLabel("SIGN - IN");
        // buttonLogin.setBackground(Color.RED); -- fail
        buttonLogin.setForeground(Color.RED);
        buttonLogin.setFont(new Font(null, Font.BOLD, 45));
        buttonLogin.setBounds(270, 550, widthLogin-20, heigthLogin);
        
        addToMainPanel(buttonLogin);
        addListener(buttonLogin);
    }
    private void createButtonNoLogin()
    {
        buttonNoLogin = new JLabel("PLAY AS GUEST");
        buttonNoLogin.setForeground(Color.RED);
        buttonNoLogin.setFont(new Font(null, Font.BOLD, 45));       
        buttonNoLogin.setBounds(750, 550, widthLogin + 150, heigthLogin);   
        addToMainPanel(buttonNoLogin);   
        addListener(buttonNoLogin);
    }

    public void addListener(JLabel button) {
        button.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            
                if (button.getText().equals("SIGN - IN")) {
                    System.out.println("Clicking   ");
                    App.frame.setVisible(false);
                    App.frame.remove(App.beginPage);
                    App.loginPanel=new LoginPanel();
                    App.frame.add(App.loginPanel);
                    // App.frame = new MyFrame();
                    App.frame.setVisible(true);
                } else if(buttonLogin.getText().length()> 1){
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
                if(buttonLogin.getText().length() > 1)
                {
                    if (button.getText().equals("SIGN - IN"))
                    {
                        next1.setIcon(new ImageIcon("../resources/images/here.png"));
                    }else{
                        next2.setIcon(new ImageIcon("../resources/images/here.png"));
                    }
                    button.setFont(new Font(null, Font.BOLD, 47));
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button.getText().equals("SIGN - IN"))
                {
                    next1.setIcon(null);
                }else{
                    next2.setIcon(null);
                }
                if(buttonLogin.getText().length() > 1)
                {
                button.setFont(new Font(null, Font.BOLD, 45));
                button.setBorder(BorderFactory.createMatteBorder(0, 0,  0, 0, Color.RED));
                }    
            }

        });

    }

    public void addToMainPanel(JLabel card) {
        this.add(card, Integer.valueOf(MyPanel.LAYER++));
    }

}