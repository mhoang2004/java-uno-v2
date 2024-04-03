import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
    static final Color BG_COLOR = new Color(3, 104, 63);
    private JButton buttonBLUE;   
    private JButton buttonYELLOW;   
    private JButton buttonGREEN;   
    private JButton buttonRED;
    private User user;
    private Card card;
    MyFrame() {
        this.setTitle("Uno Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        ImageIcon image = new ImageIcon("../resources/images/logo.png");
        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(BG_COLOR);
    }
    public MyFrame(User user, Card card)
    {
        this.card = card;
        this.user = user;
        buttonBLUE = new JButton("BLUE");
        buttonGREEN = new JButton("GREEN");
        buttonRED = new JButton("RED");
        buttonYELLOW = new JButton("YELLOW");
        this.init();
        this.setVisible(true);
    }
    void init()
    {
        this.setTitle("Chose Color"); // set title
        this.setSize(500, 100); // x-dimension and y-dimension
        JLabel buts = new JLabel();
        buts.setLayout(new GridLayout());
        ActionListener ac = new choseColor(this);
        buttonRED.addActionListener(ac);
        buttonGREEN.addActionListener(ac);
        buttonBLUE.addActionListener(ac);
        buttonYELLOW.addActionListener(ac);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        buts.add(buttonBLUE);
        buts.add(buttonRED);
        buts.add(buttonGREEN);
        buts.add(buttonYELLOW);
        JTextField text = new JTextField("Hello ");
        this.add(text, BorderLayout.NORTH);
        this.add(buts, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    void changePrevCard(String src)
    {
        user.changePrevCard(src, card);
    }
}