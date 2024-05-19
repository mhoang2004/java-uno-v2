import java.awt.Color;
import java.awt.Dimension;
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

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class HomePanel extends MyPanel {
    JLabel avatar;
    static JLabel name;
    JLayeredPane ButtonSolo;
    JLayeredPane Button2vs2;
    ImageIcon roundedIcon;
    ImageIcon roundedIcon2;
    JLabel setting;
    Timer timer;
    Timer timer2;
    static JLabel animationLabel;
    JLayeredPane goLabel;
    JLabel textJLabel;
    int count = 2;
    boolean isOut = false;
    // Tạo JLabel và thiết lập hình ảnh
    AccountUser accountUser;
    HomePanel(String path, AccountUser account) {
        super(path);
        createAvatar("avatar.png");
        accountUser = account;
        createName(accountUser.getUserName());
        createSetting();
        createButtonSolo();
        createButton2vs2();
        animationLabel = new JLabel(new ImageIcon(accountUser.getPathBackround()));
        animationLabel.setBounds(-MyPanel.WIDTH, 0, MyPanel.WIDTH + 50, MyPanel.HEIGHT);
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
        add(goLabel);
    }

    BufferedImage drawButtonNext(JLayeredPane label, int w, int h, int x) {
        BufferedImage roundedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(100, 70, 0, x));
        if (w < 450)
            g2d.fillRoundRect(0, 0, w, h, w - 300, h - 20);
        else
            g2d.fillRoundRect(0, 0, w, h, w - 400, h - 20);
        g2d.dispose();
        return roundedImage;
    }

    private void createAvatar(String pathAvatar) {

        avatar = new JLabel(new ImageIcon("../resources/images/avatar.png"));
        avatar.setBounds(200, 250, 200, 200);
        add(avatar);
    }

    private void createName(String nameString) {
        name = new JLabel(nameString);
        name.setText(nameString);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Harlow Solid Italic", Font.BOLD, 40));
        name.setBounds(50, 400, 500, 200);
        name.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        name.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        add(name);
    }

    void createSetting() {
        ImageIcon iconSetting = new ImageIcon("../resources/images/setting.png");
        setting = new JLabel(iconSetting);
        setting.setBounds(MyPanel.WIDTH - iconSetting.getIconWidth(), 0, iconSetting.getIconWidth(),
                iconSetting.getIconHeight());
        setting.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                App.frame.setVisible(false);
                App.frame.remove(App.homePanel);
                App.setting = new SettingPanel("../resources/images/BackroundBegin-1.jpg", accountUser);
                App.frame.add(App.setting);
                App.frame.setVisible(true);
            }
        });
        add(setting);
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

    void createButtonSolo() {
        roundedIcon = new ImageIcon(drawButtonNext(ButtonSolo, 500, 100, 120));
        JLabel backround = new JLabel(roundedIcon);
        backround.setBounds(0, 0, 800, 100);
        JLabel text = new JLabel("SOLO");
        text.setBounds(350, 25, 100, 50);
        text.setForeground(Color.YELLOW);
        text.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 30));
        ButtonSolo = new JLayeredPane();
        ButtonSolo.setPreferredSize(new Dimension(500, 50));
        ButtonSolo.setBounds(500, 250, 800, 100);
        ButtonSolo.setBackground(new Color(0, 0, 0, 100));
        ButtonSolo.setOpaque(false);
        ButtonSolo.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                backround.setIcon(new ImageIcon(drawButtonNext(ButtonSolo, 400, 80, 120)));
                text.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 27));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backround.setIcon(new ImageIcon(drawButtonNext(ButtonSolo, 500, 100, 150)));
                text.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 30));
                add(animationLabel);
                timer = new Timer(10, new ActionListener() {
                    int x = -MyPanel.WIDTH;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (x < -50) {
                            App.homePanel.removeComponent();
                            x += 50;
                            animationLabel.setBounds(x, 0, MyPanel.WIDTH + 50, MyPanel.HEIGHT);
                            // App.homePanel.setBounds(x+MyPanel.WIDTH, 0, MyPanel.WIDTH, MyPanel.HEIGHT);
                        } else {
                            timer.stop();
                            removeComponent();
                            animationLabel.setBounds(x, 0, MyPanel.WIDTH + 50, MyPanel.HEIGHT);
                            goLabel.setBounds(MyPanel.WIDTH / 2, MyPanel.HEIGHT / 2, 200, 200);
                            timer2 = new Timer(1000, new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (count >= 0) {
                                        textJLabel.setText(count + "");
                                        count--;
                                    } else {
                                        if (!isOut) {
                                            isOut = true;
                                            timer2.stop();
                                            App.frame.remove(App.homePanel);
                                            try {
                                                App.newGame(App.backroundGame, accountUser);
                                            } catch (UnsupportedAudioFileException | IOException
                                                    | LineUnavailableException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }

                                    }

                                }

                            });
                            timer2.start();
                        }
                    }

                });
                timer.start();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backround.setIcon(new ImageIcon(drawButtonNext(ButtonSolo, 500, 100, 150)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backround.setIcon(new ImageIcon(drawButtonNext(ButtonSolo, 500, 100, 120)));
            }

        });
        ButtonSolo.add(text);
        ButtonSolo.add(backround);
        add(ButtonSolo);
    }

    void createButton2vs2() {
        roundedIcon2 = new ImageIcon(drawButtonNext(Button2vs2, 500, 100, 120));
        JLabel backround2 = new JLabel(roundedIcon2);
        backround2.setBounds(0, 0, 800, 100);
        JLabel text = new JLabel("2 vs 2");
        text.setBounds(350, 25, 100, 50);
        text.setForeground(Color.YELLOW);
        text.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 30));
        Button2vs2 = new JLayeredPane();
        Button2vs2.setPreferredSize(new Dimension(500, 50));
        Button2vs2.setBounds(500, 400, 800, 100);
        Button2vs2.setBackground(new Color(0, 0, 0, 100));
        Button2vs2.setOpaque(false);
        Button2vs2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                backround2.setIcon(new ImageIcon(drawButtonNext(Button2vs2, 400, 80, 120)));
                text.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 27));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backround2.setIcon(new ImageIcon(drawButtonNext(Button2vs2, 500, 100, 150)));
                text.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 30));
                add(animationLabel);
                timer = new Timer(10, new ActionListener() {
                    int x = -MyPanel.WIDTH;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (x < -50) {
                            App.homePanel.removeComponent();
                            x += 50;
                            animationLabel.setBounds(x, 0, MyPanel.WIDTH + 50, MyPanel.HEIGHT);
                            // App.homePanel.setBounds(x+MyPanel.WIDTH, 0, MyPanel.WIDTH, MyPanel.HEIGHT);
                        } else {
                            timer.stop();
                            removeComponent();
                            animationLabel.setBounds(x, 0, MyPanel.WIDTH + 50, MyPanel.HEIGHT);
                            goLabel.setBounds(MyPanel.WIDTH / 2, MyPanel.HEIGHT / 2, 200, 200);
                            timer2 = new Timer(1000, new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (count >= 0) {
                                        textJLabel.setText(count + "");
                                        count--;
                                    } else {
                                        if (!isOut) {
                                            isOut = true;
                                            timer2.stop();
                                            App.frame.remove(App.homePanel);
                                            try {
                                                App.newGame(App.backroundGame, accountUser);
                                            } catch (UnsupportedAudioFileException | IOException
                                                    | LineUnavailableException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }

                                    }

                                }

                            });
                            timer2.start();
                        }
                    }

                });
                timer.start();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backround2.setIcon(new ImageIcon(drawButtonNext(ButtonSolo, 500, 100, 150)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backround2.setIcon(new ImageIcon(drawButtonNext(ButtonSolo, 500, 100, 120)));
            }

        });
        Button2vs2.add(text);
        Button2vs2.add(backround2);
        add(Button2vs2);
    }

    void removeComponent() {
        this.remove(avatar);
        this.remove(setting);
        this.remove(ButtonSolo);
        this.remove(name);
        this.remove(Button2vs2);
    }
}
