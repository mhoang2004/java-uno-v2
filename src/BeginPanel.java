
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class BeginPanel extends MyPanel {
    static int widthLogin = 230;
    static int heigthLogin = 40;
    Timer timer2;
    JLabel welcomJLabel;
    JLabel toJLabel;
    JLabel subWelcomJLabel;
    // Properti for BeginPanel
    JLayeredPane buttonLogin;
    JLabel backroundButtonLogin;
    JLayeredPane buttonNoLogin;
    JLabel backroundButtonNoLogin;
    ArrayList<String> stringList;
    JLabel next1;
    JLabel next2;
    static Timer timer;
    static String linkImg;
    String pathBackround;
    JLayeredPane goLabel;
    JLabel textJLabel;
    JLabel animationLabel;
    int count = 2;
    boolean isOut = false;
    static int x0 =MyPanel.WIDTH-100;
    static int y0=0;
    BeginPanel(String path) {
        super(path);
        linkImg = new String(path);
        createButtonLogin();
        createButtonNoLogin();
        createLabel();
        next1 = new JLabel();
        next1.setBounds(330, 480, 78, 88);
        next2 = new JLabel();
        next2.setBounds(830, 480, 78, 88);
        add(next1);
        add(next2);
        animationLabel = new JLabel(new ImageIcon(pathBackround));
        animationLabel.setBounds(-MyPanel.WIDTH, 0, MyPanel.WIDTH + 50, MyPanel.HEIGHT);
        // add(animationLabel);
        goLabel = new JLayeredPane();
        goLabel.setBounds(-200, MyPanel.HEIGHT / 2, 100, 100);

        textJLabel = new JLabel(count + 1 + "");
        textJLabel.setBounds(35, 20, 50, 50);
        textJLabel.setForeground(Color.YELLOW);
        textJLabel.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 30));
        goLabel.add(textJLabel);
        ImageIcon roundedIconx = new ImageIcon(drawButtonNext());
        JLabel backround = new JLabel(roundedIconx);
        backround.setBounds(0, 0, 100, 100);
        goLabel.add(backround);
        
    }

    BufferedImage drawButtonNext() {
        BufferedImage roundedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(100, 70, 0, 200));
        g2d.fillRoundRect(0, 0, 100, 100, 50, 50);

        g2d.dispose();
        return roundedImage;
    }

    void createLabel() {

        welcomJLabel = new JLabel();

        // welcomJLabel.setIcon(new ImageIcon("../resources/images/IMG-Sign-in.png"));
        toJLabel = new JLabel("TO");
        toJLabel.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 40));
        subWelcomJLabel = new JLabel("");
        welcomJLabel.setIcon(new ImageIcon("../resources/images/WELCOME.png"));
        welcomJLabel.setBounds(350, 70, 600, 70);
        addToMainPanel(welcomJLabel);
        Timer timer2 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random numbeRandom = new Random();
                // welcomJLabel.setForeground(new Color(numbeRandom.nextInt(256),
                // numbeRandom.nextInt(256), numbeRandom.nextInt(256)));
                // toJLabel.setForeground(new Color(numbeRandom.nextInt(256),
                // numbeRandom.nextInt(256), numbeRandom.nextInt(256)));
                subWelcomJLabel.setForeground(
                        new Color(numbeRandom.nextInt(256), numbeRandom.nextInt(256), numbeRandom.nextInt(256)));

            }

        });
        timer2.start();

        toJLabel.setForeground(Color.WHITE);
        toJLabel.setBounds(620, 150, 500, 50);
        addToMainPanel(toJLabel);
        // animation

        subWelcomJLabel.setForeground(Color.RED);
        subWelcomJLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 60));
        subWelcomJLabel.setBounds(430, 200, 700, 75);
        addToMainPanel(subWelcomJLabel);

        stringList = new ArrayList<String>();
        stringList.add("J");
        stringList.add("JU");
        stringList.add("JUN");
        stringList.add("JUNO");
        stringList.add("JUNO G");
        stringList.add("JUNO GA");
        stringList.add("JUNO GAM");
        stringList.add("JUNO GAME");
        timer = new Timer(100, new ActionListener() {
            int count = 0;
            boolean isEnd = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == -1) {
                    subWelcomJLabel.setText("");
                    count++;
                    isEnd = false;
                } else {
                    if (count < stringList.size() && isEnd == false) {

                        subWelcomJLabel.setText(stringList.get(count));
                        count++;
                    } else {
                        isEnd = true;
                        if (count == stringList.size()) {
                            count--;

                        }

                        subWelcomJLabel.setText(stringList.get(count));
                        count--;

                    }
                }

            }

        });
        timer.start();

    }

    private void createButtonLogin() {
        // Create Button
        buttonLogin = new JLayeredPane();
        buttonLogin.setBounds(270, 550, widthLogin + 50, heigthLogin + 50);
        // Create Backround
        JLabel labelLogin = new JLabel();
        labelLogin.setIcon(new ImageIcon("../resources/images/IMG-Sign-in.png"));
        labelLogin.setBounds(10, 10, widthLogin, heigthLogin + 30);
        addListener(labelLogin);

        // Create backroundButtonLogin
        ImageIcon img = new ImageIcon(CreatorCompument.createRoundButton(buttonLogin, 0));
        backroundButtonLogin = new JLabel();
        backroundButtonLogin.setIcon(img);
        backroundButtonLogin.setBounds(0, 0, widthLogin + 50, heigthLogin + 50);
        buttonLogin.add(backroundButtonLogin);
        buttonLogin.add(labelLogin);
        add(buttonLogin);
    }

    private void createButtonNoLogin() {
        // buttonNoLogin = new JLabel();
        // buttonNoLogin.setIcon(new
        // ImageIcon("../resources/images/IMG-No-Sign-in.png"));
        // buttonNoLogin.setBounds(750, 550, widthLogin+30, heigthLogin+30);
        // addToMainPanel(buttonNoLogin);
        // addListener(buttonNoLogin);
        buttonNoLogin = new JLayeredPane();
        buttonNoLogin.setBounds(750, 550, widthLogin + 70, heigthLogin + 50);
        // Create Backround
        JLabel labelLogin = new JLabel();
        labelLogin.setIcon(new ImageIcon("../resources/images/IMG-No-Sign-in.png"));
        labelLogin.setBounds(9, 10, widthLogin + 20, heigthLogin + 30);
        addListener(labelLogin);

        // Create backroundButtonLogin
        ImageIcon img = new ImageIcon(CreatorCompument.createRoundButton(buttonLogin, 0));
        backroundButtonNoLogin = new JLabel();
        backroundButtonNoLogin.setIcon(img);
        backroundButtonNoLogin.setBounds(0, 0, widthLogin + 50, heigthLogin + 50);
        buttonNoLogin.add(backroundButtonNoLogin);
        buttonNoLogin.add(labelLogin);
        add(buttonNoLogin);
    }

    public void addListener(JLabel button) {
        button.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SoundControler.soundClick();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                Timer timer3 = new Timer(10, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (button.getX() == 10) {
                            BeginPanel.timer.stop();
                            App.frame.setVisible(false);
                            App.frame.remove(App.beginPage);
                            App.loginPanel = new LoginPanel();
                            App.frame.add(App.loginPanel);
                            App.frame.setVisible(true);
                        } else {
                            ((Timer) e.getSource()).stop();
                            App.frame.remove(App.beginPage);
                            try {           
                                App.newGame(App.backroundGame, null);
                            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                });
                timer3.start();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon img = new ImageIcon(CreatorCompument.createRoundButton(buttonLogin, 50));
                if (button.getX() == 10) {
                    backroundButtonLogin.setIcon(img);
                } else {
                    backroundButtonNoLogin.setIcon(img);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon img = new ImageIcon(CreatorCompument.createRoundButton(buttonLogin, 0));
                if (button.getX() == 10) {
                    backroundButtonLogin.setIcon(img);
                } else {
                    backroundButtonNoLogin.setIcon(img);
                }
            }

        });

    }

    public void addToMainPanel(JLabel card) {
        this.add(card, Integer.valueOf(MyPanel.LAYER++));
    }

    void removeComponent() {
        this.remove(buttonLogin);
        this.remove(buttonNoLogin);
        this.remove(welcomJLabel);
        this.remove(toJLabel);
        this.remove(subWelcomJLabel);
        this.remove(next1);
        this.remove(next2);
    }
}
