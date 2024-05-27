import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JLayeredPane;

public class EndGamePanel extends JLayeredPane{
    static BufferedImage drawButtonNew() {
       
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(600, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRoundRect(0, 0, 600, 300,300,200);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.dispose();
        return roundedImage;
    }

}
