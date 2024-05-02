import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RoundedLabelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rounded JLabel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Tạo hình ảnh bo cong 4 góc
            BufferedImage roundedImage = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = roundedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLUE);
            g2d.fillRoundRect(0, 0, 100, 50, 10, 10);

            // Vẽ hình ảnh lên hình ảnh bo cong
            Image img = new ImageIcon("../resources/images/nextIMG.png").getImage();
            g2d.drawImage(img, 10, 10, null);

            g2d.dispose();

            // Tạo ImageIcon từ hình ảnh
            ImageIcon roundedIcon = new ImageIcon(roundedImage);

            // Tạo JLabel và thiết lập hình ảnh
            JLabel myLabel = new JLabel(roundedIcon);
            myLabel.setHorizontalAlignment(SwingConstants.CENTER);
            myLabel.setVerticalAlignment(SwingConstants.CENTER);

            // Thêm JLabel vào frame
            frame.add(myLabel);

            frame.setVisible(true);
        });
    }
}
