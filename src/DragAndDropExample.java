import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragAndDropExample extends JFrame {
    private JLabel label;

    public DragAndDropExample() {
        setTitle("Drag and Drop Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Drag me");
        label.setBounds(50, 50, 100, 50);
        label.setOpaque(true);
        label.setBackground(Color.RED);

        label.addMouseMotionListener(new MouseAdapter() {
            int x, y;

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = label.getX() + e.getX() - x;
                int newY = label.getY() + e.getY() - y;
                label.setLocation(newX, newY);
            }
        });

        add(label);
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DragAndDropExample();
    }
}
