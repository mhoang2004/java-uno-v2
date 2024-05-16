import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    MyFrame() {
        this.setTitle("Uno Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        ImageIcon image = new ImageIcon("../resources/images/logo.png");
        this.setIconImage(image.getImage());
    }
}