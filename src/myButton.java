import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class myButton extends JLabel implements MouseListener {

    String text;

    myButton(String text) {

        setOpaque(true);
        this.text = text;

        this.setSize(20, 60);
        this.setText(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setForeground(Color.WHITE);
        this.addMouseListener(this);
        this.setBorder(new LineBorder(null, 0, true));
        this.setBackground(new Color(30, 144, 255));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("HI");
        this.setBackground(new Color(0, 0, 128));
        setOpaque(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // this.setBackground(LoginPage.MAINCOLOR);
        setOpaque(true);
    }

}
