
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class BeginPanel extends MyPanel {
    int widthLogin = 230;
    int heigthLogin = 40;
    // Properti for BeginPanel
    JLabel buttonLogin;
    JLabel buttonNoLogin;

    BeginPanel(String path) {
        super(path);
        createButtonLogin();
        createButtonNoLogin();
        
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
                    button.setFont(new Font(null, Font.BOLD, 46));
                    button.setBorder(BorderFactory.createMatteBorder(0, 0,  2, 0, Color.RED));
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
