
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Arrow extends JLabel {
    static final int WIDTH = 80;
    static final int HEIGHT = 75;

    Arrow() {
        super();
        // get button
        ImageIcon icon = new ImageIcon("../resources/images/vector.png");
        this.setIcon(icon);
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setBounds((MyPanel.WIDTH - Card.WIDTH) / 2 - 250, (MyPanel.HEIGHT - Card.HEIGHT) / 2 -20, WIDTH, HEIGHT);
        // this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setLayout(null);
        setVisible(true);
        // JLabel img = this;
        // int x = img.getX();
        // int y = img.getY();

        // addKeyListener(new KeyAdapter() {
        // @Override
        // public void keyPressed(KeyEvent e) {
        //
        // switch (e.getKeyCode())
        // {
        // case KeyEvent.VK_UP:
        // {
        // img.setLocation(x, y - 10); // Di chuyển lên
        // break;
        // }

        // case KeyEvent.VK_DOWN:
        // {
        // img.setLocation(x, y + 10); // Di chuyển xuống
        // break;
        // }

        // }
        // }
        // });
    }

}
