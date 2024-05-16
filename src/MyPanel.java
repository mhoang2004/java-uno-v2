import java.awt.*;

import javax.swing.*;

public class MyPanel extends JLayeredPane {
    static final Color BG_COLOR = new Color(3, 104, 63);
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    static int LAYER = 0;
    private Image backgroundImage;
    MyPanel myPanel = this;

    MyPanel(String path) {
        backgroundImage = new ImageIcon(path).getImage();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(BG_COLOR);
        this.setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, this);
    }

}
