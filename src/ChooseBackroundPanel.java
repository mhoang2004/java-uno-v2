import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class ChooseBackroundPanel extends JLabel {
    private String path;
    private boolean isChoose;
    private SettingPanel controler;
    boolean isClicked = false;
    public ChooseBackroundPanel(SettingPanel controler, String path, int x, int y) {
        isChoose = false;
        this.path = "../resources/images/" + path;
        create("../resources/images/" + path + ".png", x, y);
        this.controler = controler;
    }

    private void create(String path, int x, int y) {
        this.setIcon(new ImageIcon(path));
        this.setBounds(x, y-20, 170, 107);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                setClick();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {

            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {

            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // setBorder(new LineBorder(Color.RED, 3));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                // if(!isClicked)
                // setBorder(new LineBorder(Color.RED,0));
            }

        });
    }
    void setClick()
    {
        for (ChooseBackroundPanel panel : controler.getListBackround()) {
                panel.setBorder(new LineBorder(Color.RED, 0));
        }
        isClicked = true;
        isChoose = true;
        controler.setBackroundAccount(getBackround());
        setBorder(new LineBorder(Color.RED, 3));
        controler.setBackground();
    }
    String getBackround() {
        return path.replaceAll("Iconb", "b") + ".jpg";
    }

    boolean isChoose() {
        return isChoose;
    }
}
