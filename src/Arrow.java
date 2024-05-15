
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Arrow extends JLabel {
    static final int WIDTH = 80;
    static final int HEIGHT = 75;

    Arrow() {
        super();
        removeComponent();
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setBounds((MyPanel.WIDTH - Card.WIDTH) / 2 - 250, (MyPanel.HEIGHT - Card.HEIGHT) / 2 +35, WIDTH, HEIGHT);
        // this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setLayout(null);
        setVisible(true);
        
    }
    void add()
    {
        ImageIcon icon = new ImageIcon("../resources/images/vector.png");
        this.setIcon(icon);
    }
    void removeComponent()
    {
        ImageIcon icon = new ImageIcon("");
        this.setIcon(icon);
    }
}
