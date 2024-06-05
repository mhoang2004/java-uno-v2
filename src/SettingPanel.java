import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SettingPanel extends MyPanel {
    JLabel iconSetting;
    JLabel label;
    JLayeredPane settingBackround;
    JLayeredPane settingName;
    JLayeredPane scanNameLabel;
    JLayeredPane settingSound;
    JTextField scanName;
    JLabel backround1;
    ArrayList<ChooseBackroundPanel> backroundList;
    String chooseBackround;
    JLabel buttonBackGame;
    ImageIcon roundedIcon;
    boolean isHover = false;
    AccountUser account ;
    ButtonOnOff myButton;
    
    SettingPanel(String path , AccountUser accountUser) {
        super(path);
        account = accountUser;
        backroundList = new ArrayList<>();
        iconSetting = new JLabel(new ImageIcon("../resources/images/IconSetting.png"));
        iconSetting.setBounds(500, 0, 300, 300);
        // add(iconSetting);
        label = new JLabel("CONTROL PANEL");
        label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50));
        label.setForeground(Color.RED);
        label.setBounds(200, 10, 700, 100);
        add(label);
        createButtonBack(70, false, "backIMG");
        createBackroundSetting();
        createNameSetting();
        createLogout();
    }
    void createLogout()
    {
        ImageIcon imgIcon = new ImageIcon(App.path+ "LogoutIcon.png");
        JLabel logoutImg = new JLabel();
        logoutImg.setIcon(imgIcon);
        logoutImg.setBounds(150, 550,70, 70);
        add(logoutImg);
        JLabel logoutLabel = new JLabel("Log - out");
        logoutLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
        logoutLabel.setForeground(Color.WHITE);
        logoutLabel.setBounds(250, 550,500, 70);
        logoutLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SoundControler.soundClick();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                App.modeGuest = true;
                LoginPanel.accountUser = null;
                App.frame.setVisible(false);
                App.frame.remove(App.setting);
                App.beginPage = new BeginPanel(App.getBackroundBeginPanel());
                App.frame.add(App.beginPage);
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
                logoutLabel.setForeground(Color.RED);
                }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutLabel.setForeground(Color.WHITE);
               }
            
        });
        add(logoutLabel);
    }
    void createBackroundSetting() {
        settingBackround = new JLayeredPane();
        // add backround
        settingBackround.setBounds(50, 200,MyPanel.WIDTH - 100, 150);
        ImageIcon imgIcon = new ImageIcon(drawRound(settingBackround));
        JLabel backroundSetting = new JLabel(imgIcon);
        backroundSetting.setBounds(0, 0, MyPanel.WIDTH - 100, 150);
        settingBackround.add(backroundSetting);
        // add label
        JLabel label = new JLabel("(?) CHOSE BACKROUND : ");
        label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBounds(50, 50, 300, 60);
        settingBackround.add(label);
        // add chose backround
        backroundList.add(new ChooseBackroundPanel(this, "Iconbackgroundmain-0", 400, 45));
        settingBackround.add(backroundList.get(0));
        backroundList.add(new ChooseBackroundPanel(this, "Iconbackgroundmain-1", 600, 45));
        settingBackround.add(backroundList.get(1));
        backroundList.add(new ChooseBackroundPanel(this, "Iconbackgroundmain-2", 800, 45));
        settingBackround.add(backroundList.get(2));
        for (ChooseBackroundPanel chooseBackroundPanel : backroundList) {
            if(account.getPathBackround().equals(chooseBackroundPanel.getBackround()))
            {
                chooseBackroundPanel.setBorder(new LineBorder(Color.RED, 3));
            }
        }
        add(settingBackround);
    }

    void createNameSetting() {
        settingName = new JLayeredPane();
        // add backround
        settingName.setBounds(50, 370, MyPanel.WIDTH - 100, 150);
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
        scanNameLabel.setBounds(250, 60, 500, 60);
        // scanNameLabel.setOpaque(false);
        ImageIcon img = new ImageIcon(drawBackroundScan());
        JLabel backround = new JLabel(img);
        backround.setIcon(img);
        backround.setBounds(0, 0, 470, 40);

        scanName = new JTextField();
        
        scanName.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                scanName.setText("");
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
            
        });
        scanName.setFont(new Font("Arial", Font.BOLD, 20));
        scanName.setForeground(LoginPanel.MAINCOLOR);
        scanName.setText(account.getUserName());
        scanName.setBounds(0, 0, 470, 40);
        scanNameLabel.add(scanName);
        scanNameLabel.add(backround);
        settingName.add(scanNameLabel);
        JLabel label_1 = new JLabel("(?) ON / OFF SOUND : ");
        label_1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        label_1.setForeground(Color.WHITE);
        label_1.setBounds(800, 50, 300, 60);
        settingName.add(label_1);
        myButton = new ButtonOnOff(1050, 60,account.getIsOn(), account);
        settingName.add(myButton);
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

    void createButtonBack(int w, boolean isText, String path) {
        // Tạo ImageIcon từ hình ảnh
        roundedIcon = new ImageIcon(CreatorCompument.drawButtonBack(w, isText));

        // Tạo JLabel và thiết lập hình ảnh
        buttonBackGame = new JLabel(roundedIcon);
        buttonBackGame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SoundControler.soundClick();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {

                    e1.printStackTrace();
                }
                App.frame.setVisible(false);
                App.frame.remove(App.setting);
                HomePanel.animationLabel = new JLabel(new ImageIcon(App.backroundGame));
                if (scanName.getText().length() > 2) {
                    FileHandler.setUsername(account.getMail(), scanName.getText());
                }
                System.out.println(myButton.getValue());
                // account.setUsername(scanName.getText());
                App.homePanel = new HomePanel(App.getBackroundBeginPanel(), account);
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
                    remove(LoginPanel.buttonBackGame);
                    createButtonBack(150, true, path);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (isHover) {
                    isHover = false;
                    // remove(LoginPanel.buttonNextGame);
                    buttonBackGame.setIcon(null);
                    createButtonBack(70, false, path);
                }

            }

        });
        buttonBackGame.setBounds(10, 15, w, 50);

        buttonBackGame.setOpaque(false);
        add(buttonBackGame);

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
        g2d.fillRoundRect(0, 0, MyPanel.WIDTH - 100, panel.getHeight(), 100, panel.getHeight() +100);
        g2d.dispose();
        return roundedImage;
    }

    ArrayList<ChooseBackroundPanel> getListBackround() {
        return backroundList;
    }
    void setBackroundAccount(String path)
    {
        account.setBackground(path);
    }
}
