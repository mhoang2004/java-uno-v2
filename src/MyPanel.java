import java.awt.*;
import javax.swing.*;

public class MyPanel extends JLayeredPane {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    static int LAYER = 0;

    MyPanel() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
