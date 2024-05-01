
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class BeginPanel extends MyPanel {
    int widthLogin = 200;
    int heigthLogin = 40;
    // Properti for BeginPanel
    JLabel buttonLogin;
    JLabel buttonNoLogin;

    BeginPanel(String path) {
        super(path);
        buttonLogin = new JLabel("SIGN - IN");
        // buttonLogin.setBackground(Color.RED); -- fail
        buttonLogin.setForeground(Color.RED);
        buttonLogin.setFont(new Font(null, Font.BOLD, 40));
        buttonNoLogin = new JLabel("PLAY AS GUEST");
        buttonNoLogin.setForeground(Color.RED);
        buttonNoLogin.setFont(new Font(null, Font.BOLD, 40));
        buttonLogin.setBounds(270, 550, widthLogin, heigthLogin);
        buttonNoLogin.setBounds(750, 550, widthLogin + 200, heigthLogin);
        addToMainPanel(buttonLogin);
        addToMainPanel(buttonNoLogin);
        addListener(buttonLogin);
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
                    // App.frame = new MyFrame();
                    App.frame.add(App.panelLogin);
                    App.frame.setVisible(true);
                } else {
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
                button.setFont(new Font(null, Font.BOLD, 45));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(new Font(null, Font.BOLD, 40));
            }

        });

    }

    public void addToMainPanel(JLabel card) {
        this.add(card, Integer.valueOf(MyPanel.LAYER++));
    }
}
