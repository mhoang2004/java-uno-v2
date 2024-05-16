import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.image.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LoginPanel extends MyPanel implements ActionListener {
    // paint
    Image signInImage;
    int x = 270;
    int y = 550;
    int deltaX = 7;
    int deltaY = 10;
    // compunent of this
    boolean check = false;
    JTextField scanMail;
    JPasswordField scanPass;
    JPasswordField scanConfirm;
    Border myBorder;
    Border myBorderEffect;
    JLabel createAccount;
    static Color MAINCOLOR = new Color(0, 50, 200);
    static JLabel buttonNextGame;
    static JLabel buttonBackGame;
    ImageIcon roundedIcon;
    JLayeredPane parentMail;
    JLayeredPane parentPass;
    JLayeredPane parentConfirm;
    boolean isHover = false;
    boolean isHoverSignOut = false;
    Graphics2D g2d;
    int xImg = 10;
    int yImg = 5;
    ImageIcon imgEyes;
    boolean isEyes = true;
    JLabel subHeading;
    boolean isLogIn = true;
    JLabel label;
    JLabel mailImgWarnings;
    JLabel passImgWarnings;
    JLabel confirmImgWarnings;
    JLayeredPane parentNew;
    static AccountUser accountUser;
    LoginPanel() {
        super("../resources/images/BackroundBegin-1.jpg");
        myBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK);
        myBorderEffect = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLUE);
        signInImage = new ImageIcon("../resources/images/IMG-Sign-in.png").getImage();
        Timer timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(signInImage, x, y, null);
    }

    BufferedImage drawBackroundScan(boolean isPassword) {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(500, 50, BufferedImage.TYPE_INT_ARGB);
        g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, 500, 50, 50, 50);
        g2d.setColor(Color.WHITE);

        g2d.dispose();
        return roundedImage;
    }

    void createScanMail() {
        // create input text
        scanMail = new JTextField("EMAIL");
        // setSingleLine(scanMail);
        scanMail.setFont(new Font("Arial", Font.BOLD, 20));
        scanMail.setForeground(MAINCOLOR);
        scanMail.setFocusable(false);
        scanMail.setBounds(20, 5, 470, 40);
        scanMail.setBorder(myBorder);
        setSingleLine(scanMail);
        scanMail.setText("EMAIL");
        scanMail.setOpaque(false);
        scanMail.setBackground(new Color(0, 0, 0, 0)); // Màu đen và có độ trong suốt
        scanMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scanMail.setFocusable(true);

                String getPass = new String(scanPass.getPassword());
                if (getPass.equals("PASSWORD") || getPass.length() == 0) {
                    scanPass.setBorder(myBorder);
                    scanPass.setText("PASSWORD");
                    scanPass.setEchoChar((char) 0);
                }
                String getCoFirm = new String(scanConfirm.getPassword());
                if (getCoFirm.equals("CONFIRM") || getCoFirm.length() == 0) {
                    scanConfirm.setBorder(myBorder);
                    scanConfirm.setText("CONFIRM");
                    scanConfirm.setEchoChar((char) 0);
                }
                if (scanMail.getText().equals("EMAIL") || scanMail.getText().length() == 0) {
                    scanMail.setText("");
                }

            }
        });
        scanMail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            
                if (!isLogIn) {
                    effectSuccess();
                    effectWarn();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (scanMail.getText().equals("EMAIL") || scanMail.getText().length() == 0) {
                        scanMail.setText("EMAIL");
                    }
                    scanMail.setFocusable(false);
                    scanPass.setFocusable(true);
                    String getPass = new String(scanPass.getPassword());
                    if (getPass.equals("PASSWORD") || getPass.length() == 0) {
                        scanPass.setBorder(myBorder);
                        scanPass.setText("");
                        scanPass.setEchoChar('*');
                    }
                    e.consume(); // Ngăn không cho JTextArea thêm dòng mới
                }

            }
        });

        parentMail.add(scanMail);
    }

    public static void setSingleLine(JTextField textField) {
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null) {
                    return;
                }
                if (str.indexOf("\n") == -1) {
                    super.insertString(offs, str, a);
                }
            }
        });
    }

    void createScanPass() {
        scanPass = new JPasswordField("PASSWORD");
        scanPass.setEchoChar((char) 0);
        scanPass.setFont(new Font("Arial", Font.BOLD, 20));
        scanPass.setForeground(MAINCOLOR);
        scanPass.setBounds(20, 0, 400, 50);
        scanPass.setBorder(myBorder);
        scanPass.setOpaque(false);
        scanPass.setBackground(new Color(0, 0, 0, 0)); // Màu đen và có độ trong suốt
        scanPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // scanPass.setBorder(myBorderEffect);
                // String getCoFirm= new String(scanComfirm.getPassword());
                scanPass.setFocusable(true);
                scanConfirm.setEchoChar('*');
                if (!isEyes) {
                    scanConfirm.setEchoChar('*');
                }
                if (scanMail.getText().equals("EMAIL") || scanMail.getText().length() == 0) {
                    scanMail.setBorder(myBorder);
                    scanMail.setText("EMAIL");
                }
                String getCoFirm = new String(scanConfirm.getPassword());

                if (getCoFirm.equals("CONFIRM") || getCoFirm.length() == 0) {
                    scanConfirm.setBorder(myBorder);
                    scanConfirm.setText("CONFIRM");
                    scanConfirm.setEchoChar((char) 0);
                }
                String getPass = new String(scanPass.getPassword());
                if (getPass.equals("PASSWORD") || getPass.length() == 0) {
                    scanPass.setText("");
                }
            }
        });
        scanPass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (isLogIn) {
                        actionNext();
                    } else {
                        String getPass = new String(scanPass.getPassword());
                        if (getPass.equals("PASSWORD") || getPass.length() == 0) {
                            scanPass.setBorder(myBorder);
                            scanPass.setText("PASSWORD");
                            scanPass.setEchoChar((char) 0);
                        }
                        String getCoFirm = new String(scanConfirm.getPassword());
                        scanPass.setFocusable(false);
                        scanConfirm.setFocusable(true);
                        if (getCoFirm.equals("CONFIRM") || getCoFirm.length() == 0) {
                            scanConfirm.setBorder(myBorder);
                            scanConfirm.setText("");
                            scanConfirm.setEchoChar('*');
                        }
                    }

                    e.consume(); // Ngăn không cho JTextArea thêm dòng mới
                }
            }

        });
        parentPass.add(scanPass);
    }

    void createAccount() {
        parentNew = new JLayeredPane();
        parentNew.setBounds(400, 480, 150, 30);
        ImageIcon img = new ImageIcon(CreatorCompument.drawButtonNew(130, false));
        JLabel backroundNew = new JLabel();
        backroundNew.setIcon(img);
        backroundNew.setBounds(0, 0, 150, 30);
        createAccount = new JLabel("Create Acount");
        createAccount.setFont(new Font("Arial", Font.BOLD, 15));
        createAccount.setForeground(MAINCOLOR);
        createAccount.setBounds(10, 0, 110, 30);
        createAccount.setOpaque(false);
        createAccount.setBackground(new Color(0, 0, 0, 0)); // Màu đen và có độ trong suốt
        createAccount.addMouseListener(new MouseListener() {
            int fontsize = 15;

            @Override
            public void mouseClicked(MouseEvent e) {
                scanConfirm.setText("CONFIRM");
                scanConfirm.setFocusable(false);
                scanPass.setText("PASSWORD");
                scanPass.setFocusable(false);
                scanMail.setText("EMAIL");
                scanMail.setFocusable(false);
                isLogIn = false;
                parentNew.remove(backroundNew);
                MouseListener a = this;
                Timer timer = new Timer(10, new ActionListener() {
                    int x = 400;
                    int y = 450;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (y > 150) {

                            x += 4;
                            y -= 10;
                            fontsize += 1;
                            createAccount.setFont(new Font("Arial", Font.BOLD, fontsize));
                            createAccount.setBounds(0, 0, 510, 60);
                            parentNew.setBounds(x, y, 510, 60);
                        } else {
                            remove(subHeading);
                            scanPass.addKeyListener(new KeyAdapter() {
                                @Override
                                public void keyReleased(KeyEvent e) {
                                    if (!isLogIn) {
                                        effectSuccess();
                                        effectWarn();
                                    }
                                }
                            });

                            scanMail.addKeyListener(new KeyAdapter() {
                                @Override
                                public void keyReleased(KeyEvent e) {
                                    if (!isLogIn) {
                                        effectSuccess();
                                        effectWarn();
                                    }
                                }
                            });

                            buttonNextGame.addMouseListener(new MouseListener() {

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
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                }

                            });

                            signInImage = null;
                            repaint();
                            parentConfirm.setBounds(400, 460, 510, 60);
                            createAccount.removeMouseListener(a);
                        }

                    }
                });

                timer.start();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backroundNew.setIcon(new ImageIcon(CreatorCompument.drawButtonNew(130, true)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backroundNew.setIcon(new ImageIcon(CreatorCompument.drawButtonNew(130, false)));
            }

        });
        parentNew.add(backroundNew);
        parentNew.add(createAccount);
        add(parentNew);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (y > 150) {
            x += deltaX;
            y -= deltaY;
            repaint();
        } else {
            if (!check) {
                check = true;
                subHeading = new JLabel("WITH   JUNO   ACCOUNT");

                subHeading.setFont(new Font("Harlow Solid Italic", Font.BOLD, 22));
                subHeading.setForeground(MAINCOLOR);
                subHeading.setBounds(x - 50, 200, 400, 30);
                add(subHeading);
                // scan mail
                parentMail = new JLayeredPane();
                parentMail.setPreferredSize(new Dimension(500, 50));
                ImageIcon backroundMailIcon = new ImageIcon(drawBackroundScan(false));
                JLabel backroundMail = new JLabel(backroundMailIcon);
                createScanMail();
                parentMail.add(backroundMail);
                backroundMail.setBounds(0, 0, 500, 50);
                parentMail.setBounds(400, 320, 500, 50);
                // parentMail.setBackground(Color.BLACK);
                parentMail.setOpaque(false);
                add(parentMail);
                // scan pass
                parentPass = new JLayeredPane();
                parentPass.setPreferredSize(new Dimension(500, 50));
                ImageIcon backroundPassIcon = new ImageIcon(drawBackroundScan(true));
                JLabel backroundPass = new JLabel(backroundPassIcon);
                createScanPass();
                imgEyes = new ImageIcon("../resources/images/Show.png");
                JLabel iconEyes = new JLabel(imgEyes);
                iconEyes.setBounds(440, 0, 50, 50);
                iconEyes.setBorder(myBorder);
                iconEyes.setOpaque(false);
                iconEyes.setBackground(new Color(0, 0, 0, 0));
                iconEyes.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            SoundControler.soundClick();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                            e1.printStackTrace();
                        }
                        if (!isEyes) {
                            isEyes = true;
                            iconEyes.setIcon(new ImageIcon("../resources/images/Sleep.png"));
                            scanPass.setEchoChar((char) 0);

                        } else {
                            isEyes = false;
                            iconEyes.setIcon(new ImageIcon("../resources/images/Show.png"));
                            String getPass = new String(scanPass.getPassword());
                            if (getPass.equals("PASSWORD")) {
                                scanPass.setEchoChar((char) 0);
                            } else {
                                scanPass.setEchoChar('*');
                            }

                        }
                    }

                });
                parentPass.add(iconEyes);
                parentPass.add(backroundPass);
                backroundPass.setBounds(0, 0, 500, 50);
                parentPass.setBounds(400, 390, 500, 50);
                // parentMail.setBackground(Color.BLACK);
                parentPass.setOpaque(false);
                add(parentPass);

                createAccount();
                // scan confirm
                parentConfirm = new JLayeredPane();
                parentConfirm.setPreferredSize(new Dimension(500, 50));
                ImageIcon backround = new ImageIcon(drawBackroundScan(false));
                JLabel backroundConFirm = new JLabel(backround);
                // creat();
                imgEyes = new ImageIcon("../resources/images/Show.png");
                iconEyes.setBounds(440, 0, 50, 50);
                iconEyes.setBorder(myBorder);
                iconEyes.setOpaque(false);
                iconEyes.setBackground(new Color(0, 0, 0, 0));
                scanConfirm = new JPasswordField("CONFIRM");

                scanConfirm.setEchoChar((char) 0);
                scanConfirm.setFont(new Font("Arial", Font.BOLD, 20));
                scanConfirm.setForeground(MAINCOLOR);
                scanConfirm.setBounds(20, 10, 400, 50);
                scanConfirm.setBorder(myBorder);
                scanConfirm.setOpaque(false);
                scanConfirm.setBackground(new Color(0, 0, 0, 0)); // Màu đen và có độ trong suốt
                scanConfirm.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            SoundControler.soundClick();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {

                            e1.printStackTrace();
                        }
                        scanConfirm.setFocusable(true);
                        effectSuccess();
                        effectWarn();
                        String getCoFirm = new String(scanConfirm.getPassword());
                        String getPass = new String(scanPass.getPassword());
                        if (!isEyes) {
                            scanConfirm.setEchoChar('*');
                        }

                        if (scanMail.getText().equals("EMAIL") || scanMail.getText().length() == 0) {
                            scanMail.setBorder(myBorder);
                            scanMail.setText("EMAIL");
                        }
                        if (getPass.equals("PASSWORD") || getPass.length() == 0) {
                            scanPass.setBorder(myBorder);
                            scanPass.setText("PASSWORD");
                            scanPass.setEchoChar((char) 0);
                        }
                        if (getCoFirm.equals("CONFIRM") || getCoFirm.length() == 0) {
                            scanConfirm.setText("");
                        }
                    }
                });
                scanConfirm.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        effectSuccess();
                        effectWarn();
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            if (checkSignOut().size() == 0) {
                                Notification noti2 = new Notification(5);
                                noti2.setText("SUCCESS");
                                String getPass = new String(scanPass.getPassword());
                                addToMainPanel(noti2);
                                noti2.removeTextByBao3(scanMail.getText(), getPass);
                            } else {
                                Notification noti2 = new Notification(5);
                                noti2.setText("Email and password do not match");
                                addToMainPanel(noti2);
                                noti2.removeTextByBao();
                            }

                            e.consume(); // Ngăn không cho JTextArea thêm dòng mới
                        }
                    }
                });
                imgEyes = new ImageIcon("../resources/images/Show.png");
                JLabel iconEyesComfirm = new JLabel(imgEyes);
                iconEyesComfirm.setBounds(440, 10, 50, 50);
                iconEyesComfirm.setBorder(myBorder);
                iconEyesComfirm.setOpaque(false);
                iconEyesComfirm.setBackground(new Color(0, 0, 0, 0));
                iconEyesComfirm.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            SoundControler.soundClick();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {

                            e1.printStackTrace();
                        }
                        if (!isEyes) {
                            isEyes = true;
                            iconEyesComfirm.setIcon(new ImageIcon("../resources/images/Sleep.png"));
                            scanConfirm.setEchoChar((char) 0);

                        } else {
                            isEyes = false;
                            iconEyesComfirm.setIcon(new ImageIcon("../resources/images/Show.png"));
                            String getPass = new String(scanConfirm.getPassword());
                            if (getPass.equals("CONFIRM")) {
                                scanConfirm.setEchoChar((char) 0);
                            } else {
                                scanConfirm.setEchoChar('*');
                            }

                        }
                    }

                });
                parentConfirm.add(scanConfirm);
                parentConfirm.add(iconEyesComfirm);

                parentConfirm.add(backroundConFirm);

                backroundConFirm.setBounds(0, 8, 500, 50);
                parentConfirm.setBounds(400, 800, 500, 40);
                parentConfirm.setOpaque(false);
                add(parentConfirm);
                createButtonNext(70, false, 530, "nextIMG");
                createButtonBack(70, false, "backIMG");
            }
        }
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
                if (isLogIn) {
                    App.frame.setVisible(false);
                    App.frame.remove(App.loginPanel);
                    App.beginPage = new BeginPanel("../resources/images/BackroundBegin-1.jpg");
                    App.frame.add(App.beginPage);
                    App.frame.setVisible(true);
                } else {
                    App.frame.setVisible(false);
                    App.frame.remove(App.loginPanel);
                    App.loginPanel = new LoginPanel();
                    App.frame.add(App.loginPanel);
                    App.frame.setVisible(true);
                }

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

    JLabel createImgWarnings(JLayeredPane x, boolean isWarn) {
        int y = x.getY();
        System.out.println(y);
        if (y == 390) {
            passImgWarnings = drawImgWarnings(y, isWarn);
            return passImgWarnings;
        } else if (y == 320) {
            mailImgWarnings = drawImgWarnings(y, isWarn);
            return mailImgWarnings;
        }
        confirmImgWarnings = drawImgWarnings(y, isWarn);
        return confirmImgWarnings;

    }

    JLabel drawImgWarnings(int y, boolean isWarn) {
        JLabel imgLabel;
        if (isWarn) {
            imgLabel = new JLabel(new ImageIcon("../resources/images/Warnings.png"));
        } else {
            imgLabel = new JLabel(new ImageIcon("../resources/images/Success.png"));
        }
        imgLabel.setBounds(920, y + 15, 30, 30);
        imgLabel.setBorder(myBorder);
        imgLabel.setOpaque(false);
        imgLabel.setBackground(new Color(0, 0, 0, 0));
        add(imgLabel);
        return imgLabel;
    }

    BufferedImage drawButtonNext(int width, boolean isText, String path, int flur) {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(width, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 200, flur));
        g2d.fillRoundRect(0, 0, width, 50, 50, 50);
        g2d.setColor(Color.WHITE);
        if (isText) {

            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            String text;
            if (path.equals("backIMG")) {
                text = "BACK";
            } else {
                text = "NEXT";
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

    boolean checkLogin() {
        String getPass = new String(scanPass.getPassword());
        return FileHandler.checkValidLogIn(scanMail.getText(), getPass);
    }

    boolean checkDuplicateEmail(String email) {
        List<Map<String, String>> data = FileHandler.getAllUsersData();

        for (Map<String, String> row : data) {
            if (row.get("email").equals(email)) {
                return true;
            }
        }
        return false;
    }

    ArrayList<Integer> checkSignOut() {
        ArrayList<Integer> result = new ArrayList<>();
        String getCoFirm = new String(scanConfirm.getPassword());
        String getPass = new String(scanPass.getPassword());

        if (!validateEmailStrict()) {
            result.add(1);
        }
        if (!validatePassword(getPass)) {
            result.add(2);
        }
        if (!checkLogin()) {
            if (!getPass.equals(getCoFirm) || getPass.length() == 0) {
                result.add(3);
            }
        }
        return result;
    }

    void effectSuccess() {
        String getCoFirm = new String(scanConfirm.getPassword());
        String getPass = new String(scanPass.getPassword());
        if (validateEmailStrict()) {
            if (mailImgWarnings != null)
                mailImgWarnings.setIcon(new ImageIcon("../resources/images/Success.png"));
            else
                mailImgWarnings = createImgWarnings(parentMail, true);
        }
        if (validatePassword(getPass)) {
            if (passImgWarnings != null)
                passImgWarnings.setIcon(new ImageIcon("../resources/images/Success.png"));
            else
                passImgWarnings = createImgWarnings(parentPass, true);
        }
        if (getPass.equals(getCoFirm)) {
            if (confirmImgWarnings != null)
                confirmImgWarnings.setIcon(new ImageIcon("../resources/images/Success.png"));
            else
                confirmImgWarnings = createImgWarnings(parentConfirm, true);

        }
    }

    void effectWarn() {
        for (int i = 0; i < checkSignOut().size(); i++) {
            switch (checkSignOut().get(i)) {
                case 1: {
                    if (mailImgWarnings != null) {
                        mailImgWarnings.setIcon(new ImageIcon("../resources/images/Warnings.png"));
                    } else {
                        mailImgWarnings = createImgWarnings(parentMail, true);
                    }
                    break;
                }
                case 2: {

                    if (passImgWarnings != null) {
                        passImgWarnings.setIcon(new ImageIcon("../resources/images/Warnings.png"));
                    } else {
                        passImgWarnings = createImgWarnings(parentPass, true);

                    }
                    break;
                }
                default: {
                    if (confirmImgWarnings != null) {
                        confirmImgWarnings.setIcon(new ImageIcon("../resources/images/Warnings.png"));
                    } else {
                        confirmImgWarnings = createImgWarnings(parentConfirm, true);

                    }
                    break;
                }
            }

        }
    }

    void createButtonNext(int width, boolean isText, int y, String path) {
        // Tạo ImageIcon từ hình ảnh
        roundedIcon = new ImageIcon(drawButtonNext(width, isText, path, 200));

        // Tạo JLabel và thiết lập hình ảnh
        buttonNextGame = new JLabel(roundedIcon);
        buttonNextGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SoundControler.soundClick();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    e1.printStackTrace();
                }
                actionNext();
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
                    remove(LoginPanel.buttonNextGame);
                    createButtonNext(150, true, y, path);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (isHover) {
                    isHover = false;
                    // remove(LoginPanel.buttonNextGame);
                    buttonNextGame.setIcon(null);
                    createButtonNext(70, false, y, path);
                }

            }

        });
        buttonNextGame.setBounds(800, y, width, 50);

        buttonNextGame.setOpaque(false);
        add(buttonNextGame);

    }

    public boolean validateEmailStrict() {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern).matcher(scanMail.getText()).matches();
    }

    public boolean validatePassword(String password) {
        // Regex pattern to validate password
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return Pattern.compile(pattern).matcher(password).matches();
    }

    public void addToMainPanel(JLabel card) {
        this.add(card, Integer.valueOf(MyPanel.LAYER++));
    }
    void actionNext()
    {
        if (isLogIn) {
            if (checkLogin() == true) {
                accountUser = new AccountUser(scanMail.getText(), scanPass.getPassword());
                App.modeGuest = false;
                remove(buttonBackGame);
                Notification noti2 = new Notification(20);
                noti2.setText("SUCCESS");
                addToMainPanel(noti2);
                noti2.removeTextByBao2(accountUser);
            } else {
                Notification noti1 = new Notification(20);
                noti1.setText("Email and password do not match");
                addToMainPanel(noti1);
                noti1.removeTextByBao();
            }
        } else {
            if (checkSignOut().size() == 0 ) {
                if(!FileHandler.checkValidSingOut(scanMail.getText()))
                {
                    Notification noti1 = new Notification(20);
                    noti1.setText("Email already exists");
                    addToMainPanel(noti1);
                    noti1.removeTextByBao();
                }else{
                    String getPass = new String(scanPass.getPassword());
                    FileHandler.addNewUserData(scanMail.getText(), getPass);
                    Notification noti1 = new Notification(20);
                    noti1.setText("Success");
                    addToMainPanel(noti1);
                    noti1.removeTextByBao();
                    App.frame.setVisible(false);
                    App.frame.remove(App.loginPanel);
                    App.loginPanel = new LoginPanel();
                    App.frame.add(App.loginPanel);
                    App.frame.setVisible(true);
                }
                
            } else {
                Notification noti2 = new Notification(5);
                noti2.setText("Invalid SignIn");
                addToMainPanel(noti2);
                noti2.removeTextByBao();
            }
        }
    }
}