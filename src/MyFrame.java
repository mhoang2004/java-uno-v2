import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    static final Color BG_COLOR = new Color(3, 104, 63);

    MyFrame() {
        this.setTitle("Uno Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        ImageIcon image = new ImageIcon("../resources/images/logo.png");
        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(BG_COLOR);
    }
}