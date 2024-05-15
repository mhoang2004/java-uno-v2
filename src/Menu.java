import javax.swing.*;
import java.awt.*;

public class Menu extends JLabel {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    private Image backgroundImage = new ImageIcon("../resources/images/background.png").getImage();

    Menu() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton loginBtn = new JButton("Login");
        loginBtn.setLocation(0, 0);
        loginBtn.setPreferredSize(new Dimension(150, 50));
        loginBtn.setBackground(Color.GREEN);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 153), 2));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton guestBtn = new JButton("Continue as Guest");
        guestBtn.setLocation(200, 200);
        guestBtn.setPreferredSize(new Dimension(150, 50));
        guestBtn.setBackground(Color.BLUE);
        guestBtn.setForeground(Color.WHITE);
        guestBtn.setFont(new Font("Arial", Font.BOLD, 16));
        guestBtn.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 153), 2));
        guestBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.add(loginBtn);
        this.add(guestBtn);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Menu());
        frame.setVisible(true);
    }
}
