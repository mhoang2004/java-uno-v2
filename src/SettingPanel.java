import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class SettingPanel extends MyPanel {
    JLabel iconSetting;
    JLabel label;
    JLayeredPane settingBackround;
    JLayeredPane settingName;
    JLayeredPane scanNameLabel;
    JTextField scanName;
    JLabel backround1;
    ArrayList<ChooseBackroundPanel> backroundList;
    String chooseBackround;
    JLabel butBackGame;
    ImageIcon roundedIcon;
    boolean isHover = false;

    SettingPanel(String path) {
        super(path);
        backroundList = new ArrayList<>();
        iconSetting = new JLabel(new ImageIcon("../resources/images/IconSetting.png"));
        iconSetting.setBounds(500, 0, 300, 300);
        add(iconSetting);
        label = new JLabel("SETTING");
        label.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 40));
        label.setForeground(LoginPanel.MAINCOLOR);
        label.setBounds(550, 250, 470, 60);
        add(label);
        createButtonBack(70, false, 530, "backIMG");
        createBackroundSetting();
        createNameSetting();
    }

    void createBackroundSetting() {
        settingBackround = new JLayeredPane();
        // add backround
        settingBackround.setBounds(50, 310, MyPanel.WIDTH - 100, 200);
        ImageIcon imgIcon = new ImageIcon(drawRound(settingBackround));
        JLabel backroundSetting = new JLabel(imgIcon);
        backroundSetting.setBounds(0, 0, MyPanel.WIDTH - 100, 200);
        settingBackround.add(backroundSetting);
        // add label
        JLabel label = new JLabel("(?) CHOSE BACKROUND : ");
        label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBounds(50, 80, 300, 60);
        settingBackround.add(label);
        // add chose backround

        backroundList.add(new ChooseBackroundPanel(this, "Iconbackgroundmain-0", 400, 45));
        settingBackround.add(backroundList.get(0));
        backroundList.add(new ChooseBackroundPanel(this, "Iconbackgroundmain-1", 600, 45));
        settingBackround.add(backroundList.get(1));
        backroundList.add(new ChooseBackroundPanel(this, "Iconbackgroundmain-2", 800, 45));
        settingBackround.add(backroundList.get(2));
        add(settingBackround);
    }

    void createNameSetting() {
        settingName = new JLayeredPane();
        // add backround
        settingName.setBounds(50, 520, MyPanel.WIDTH - 100, 150);
        ImageIcon imgIcon = new ImageIcon(drawRound(settingName));
        JLabel backroundSetting = new JLabel(imgIcon);
        backroundSetting.setBounds(0, 0, MyPanel.WIDTH - 100, 150);
        settingName.add(backroundSetting);
        // add label
        JLabel label = new JLabel("(?) USERS NAME : ");
        label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBounds(50, 50, 300, 60);
        settingName.add(label);
        // add setname
        scanNameLabel = new JLayeredPane();
        scanNameLabel.setBounds(400, 50, 500, 60);
        // scanNameLabel.setOpaque(false);
        ImageIcon img = new ImageIcon(drawBackroundScan());
        JLabel backround = new JLabel(img);
        backround.setIcon(img);
        backround.setBounds(0, 0, 470, 40);

        scanName = new JTextField();
        scanName.setFont(new Font("Arial", Font.BOLD, 20));
        scanName.setForeground(LoginPanel.MAINCOLOR);
        scanName.setBounds(0, 0, 470, 40);
        LoginPanel.setSingleLine(scanName);
        ;
        scanNameLabel.add(scanName);
        scanNameLabel.add(backround);
        settingName.add(scanNameLabel);
        add(settingName);
    }

    void setBackground() {
        for (ChooseBackroundPanel jLabel : backroundList) {
            if (jLabel.isChoose()) {
                App.setBackground(jLabel.getBackround());
                System.out.println(App.backroundGame);
            }
        }
    }

    BufferedImage drawBackroundScan() {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(470, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setColor(Color.WHITE);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, 400, 40, 50, 50);

        g2d.dispose();
        return roundedImage;
    }

    BufferedImage drawButtonNext(int width, boolean isText, String path) {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(width, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLUE);
        g2d.fillRoundRect(0, 0, width, 50, 50, 50);
        g2d.setColor(Color.WHITE);
        if (isText) {

            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            String text;
            if (path.equals("backIMG")) {
                text = "NEXT";
            } else {
                text = "BACK";
            }

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            int x = (width - textWidth) / 2;
            int y = (50 - textHeight) / 2 + fm.getAscent();
            if (path.equals("backIMG")) {
                g2d.drawString(text, 70, y);
            } else {
                g2d.drawString(text, x, y);
            }

        }

        String pathIcon = new String("../resources/images/");
        pathIcon += path + ".png";
        Image img = new ImageIcon(pathIcon).getImage();
        if (path.equals("backIMG")) {
            g2d.drawImage(img, 10, 0, null);
        } else {
            g2d.drawImage(img, width - 40, 5, null);
        }

        g2d.dispose();
        return roundedImage;
    }

    void createButtonBack(int w, boolean isText, int deltaY, String path) {
        // Tạo ImageIcon từ hình ảnh
        roundedIcon = new ImageIcon(drawButtonNext(w, isText, path));

        // Tạo JLabel và thiết lập hình ảnh
        butBackGame = new JLabel(roundedIcon);
        butBackGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                App.frame.setVisible(false);
                App.frame.remove(App.setting);
                HomePanel.animationLabel = new JLabel(new ImageIcon(App.backroundGame));
                if (scanName.getText().length() > 2)
                    HomePanel.name.setText("HI" + scanName.getText());
                App.frame.add(App.homePanel);
                App.frame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isHover) {
                    isHover = true;
                    remove(butBackGame);
                    createButtonBack(150, true, 550, path);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (isHover) {
                    isHover = false;
                    // remove(LoginPanel.buttonNextGame);
                    butBackGame.setIcon(null);
                    createButtonBack(70, false, 550, path);
                }

            }

        });
        butBackGame.setBounds(50, 50, w, 50);

        butBackGame.setOpaque(false);
        add(butBackGame);

    }

    BufferedImage drawRoundImage(String path) {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(MyPanel.WIDTH - 100, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 0, 80));
        g2d.fillRoundRect(0, 0, MyPanel.WIDTH - 100, 200, 100, 100);
        Image img = new ImageIcon(path).getImage();
        g2d.drawImage(img, 10, 0, null);
        g2d.dispose();
        return roundedImage;
    }

    BufferedImage drawRound(JLayeredPane panel) {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(MyPanel.WIDTH - 100, panel.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 0, 80));
        g2d.fillRoundRect(0, 0, MyPanel.WIDTH - 100, panel.getHeight(), 100, panel.getHeight() - 100);
        g2d.dispose();
        return roundedImage;
    }

    ArrayList<ChooseBackroundPanel> getListBackround() {
        return backroundList;
    }
}
