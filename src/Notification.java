import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Notification extends JLabel {
    static final int WIDTH = 1000; 
    static final int HEIGHT = 50; 
    Notification() {
        super();
        // this.setText("Hi everyone");
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2 - 180, WIDTH, HEIGHT);
        this.setBackground(new Color(0, 0, 0, 100)); 
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setOpaque(true); 
        this.setForeground(Color.WHITE); 
        this.setFont(new Font("Arial", Font.BOLD, 16));
    }   

    public void removeText() {
        JLabel text = this;
        Timer timer = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.mainPanel.remove(text);
                Game.mainPanel.repaint();
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
        
    }
    public void removeTextByBao() {
        JLabel text = this;
        Timer timer = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.loginPanel.remove(text);
                App.loginPanel.repaint();
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
        
    }
}