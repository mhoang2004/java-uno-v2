import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class ButtonOnOff extends JLayeredPane implements MouseListener{
    JLabel backround;
    boolean isOn;
    AccountUser accountUser;
    ButtonOnOff(int x, int y, boolean isOn, AccountUser accountUser)
    {
        this.accountUser = accountUser;
        this.isOn = isOn;
        this.setBounds(x, y,60, 35);
        ImageIcon img = new ImageIcon(createRoundButton(isOn));
        backround = new JLabel(img);
        backround.setBounds(0,0, 60,35);
        this.add(backround);
        this.addMouseListener(this);
        
    }
    BufferedImage createRoundButton(boolean isOn) {
        int x=0;
        BufferedImage roundedImage = new BufferedImage(60, 35,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(isOn)
        {
            x= 25;
            g2d.setColor(Color.RED);
            g2d.fillRoundRect(0, 0,60,35, 30, 35);
            Image img = new ImageIcon("../resources/images/circleBack.png").getImage();
            g2d.drawImage(img, x, 3, null);
        }else{
            g2d.setColor(Color.BLACK);
            g2d.fillRoundRect(0, 0,60,35, 30, 35);
            Image img = new ImageIcon("../resources/images/circleRed.png").getImage();
            g2d.drawImage(img, x, 3, null);
        }
        g2d.dispose();
        return roundedImage;
    }
    boolean getValue()
    {
        return isOn;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(isOn)
        {
            isOn = false;
        }else{
            isOn = true;
        }
        accountUser.setIsOn(isOn);
        remove(backround);
        ImageIcon img = new ImageIcon(createRoundButton(isOn));
        backround = new JLabel(img);
        backround.setBounds(0,0, 60,35);
        add(backround);                
         
    }
    @Override
    public void mousePressed(MouseEvent e) {
        }
    @Override
    public void mouseReleased(MouseEvent e) {
        }
    @Override
    public void mouseEntered(MouseEvent e) {
        }
    @Override
    public void mouseExited(MouseEvent e) {
        }
}
