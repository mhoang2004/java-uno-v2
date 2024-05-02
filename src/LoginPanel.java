import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;

public class LoginPanel extends MyPanel implements ActionListener{
    // paint
    Image signInImage ;
    int x = 270;
    int y = 550;
    int deltaX =7;
    int deltaY =10;
    // compunent of this
    boolean check = false;
    JTextArea scanMail ;
    JPasswordField scanPass ;
    Border myBorder;
    Border myBorderEffect;
    JLabel createAccount;
    static Color MAINCOLOR = new Color(255 ,48 ,48);
    static JLabel buttonNextGame;
    ImageIcon roundedIcon;
    JLayeredPane parentMail;
    JLayeredPane parentPass;
    boolean isHover = false;
    Graphics2D g2d;
    int xImg = 10;
    int yImg = 5;
    ImageIcon imgEyes; 
    boolean isEyes = true;

    LoginPanel()
    {
        super("../resources/images/backgroundmain-2.jpg");
        myBorder= BorderFactory.createMatteBorder(0, 0,  0, 0, Color.BLACK);
        myBorderEffect= BorderFactory.createMatteBorder(0, 0,  3, 0, Color.BLUE);
        signInImage = new ImageIcon("../resources/images/IMG-Sign-in.png").getImage();
        Timer timer = new Timer(10, this);
        timer.start();
    
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(signInImage,x, y,null);
    }

    BufferedImage drawBackroundScan(boolean isPassword)
    {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(500, 50, BufferedImage.TYPE_INT_ARGB);
        g2d= roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, 500, 50, 50, 50);
        g2d.setColor(Color.WHITE);
        // if(isText)
        // {
           
        //     g2d.setFont(new Font("Arial", Font.BOLD, 20));
        //     String text = "NEXT";
        //     FontMetrics fm = g2d.getFontMetrics();
        //     int textWidth = fm.stringWidth(text);
        //     int textHeight = fm.getHeight();
        //     int x = (width - textWidth) / 2;
        //     int y = (50 - textHeight) / 2 + fm.getAscent();
        //     g2d.drawString(text, x, y);
        // }

       
        g2d.dispose();
        return roundedImage;
    }
    

    void createScanMail()
    {

        // create input text
        scanMail = new JTextArea("EMAIL");
        scanMail.setFont(new  Font("Arial", Font.BOLD, 20));
        scanMail.setForeground(MAINCOLOR);
        scanMail.setBounds(20,10, 470, 40);
        // scanMail.setBorder(myBorder);
        scanMail.setOpaque(false);
        scanMail.setBackground(new Color(0, 0, 0, 0)); // Màu đen và có độ trong suốt
        scanMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                String getPass = new String(scanPass.getPassword());
                if(getPass.equals("PASSWORD") ||getPass.length() == 0)
                {
                    scanPass.setBorder(myBorder);
                    scanPass.setText("PASSWORD");
                    scanPass.setEchoChar((char)0);
                }
                if(scanMail.getText().equals("EMAIL") ||scanMail.getText().length() == 0)
                {
                    scanMail.setText("");
                }
                
            }   
        });
        scanMail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                effectValidBorder();
            }
        });
        parentMail.add(scanMail);
    }

    void createScanPass()
    {
        scanPass = new JPasswordField("PASSWORD");
        scanPass.setEchoChar((char) 0);
        scanPass.setFont(new  Font("Arial", Font.BOLD, 20));
        scanPass.setForeground(MAINCOLOR);
        scanPass.setBounds(20, 0, 400, 50);
        scanPass.setBorder(myBorder);
        scanPass.setOpaque(false);
        scanPass.setBackground(new Color(0, 0, 0, 0)); // Màu đen và có độ trong suốt
        scanPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // scanPass.setBorder(myBorderEffect);
                scanPass.setEchoChar('*');
                if(scanMail.getText().equals("EMAIL") ||scanMail.getText().length() == 0)
                {
                    scanMail.setBorder(myBorder);
                    scanMail.setText("EMAIL");
                }
                String getPass = new String(scanPass.getPassword());
                if(getPass.equals("PASSWORD") ||getPass.length() == 0)
                {
                    scanPass.setText("");
                }
            }
        });
        parentPass.add(scanPass);
    }
    
    void createAccount()
    {
        createAccount = new JLabel("Create Acount");
        createAccount.setFont(new  Font("Arial", Font.BOLD, 15));
        createAccount.setForeground(MAINCOLOR);
        createAccount.setBounds(400, 450, 110, 30);
        createAccount.setOpaque(false);
        createAccount.setBackground(new Color(0, 0, 0, 0)); // Màu đen và có độ trong suốt
        createAccount.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                createAccount.setOpaque(true);
                createAccount.setBackground(new Color(165 ,42 ,42	, 200));
                Timer timer = new Timer(10, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                       
                    }
                    
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {
                }

            @Override
            public void mouseReleased(MouseEvent e) {
                

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createAccount.setForeground(Color.RED);
                }

            @Override
            public void mouseExited(MouseEvent e) {
                // createAccount.setOpaque(true);
                createAccount.setForeground(MAINCOLOR);
            }
            
        });
        add(createAccount);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(y>150)
        {
            x +=deltaX;
            y -=deltaY;
            repaint();
        }else{
            if(!check)
            {
                check = true;
                JLabel subHeading = new JLabel("WITH   JUNO   ACCOUNT");
                subHeading.setFont(new Font("Arial", Font.BOLD, 22));
                subHeading.setForeground(Color.RED);
                subHeading.setBounds(x-30, 200, 400, 30);
                add(subHeading);
                //scan mail
                parentMail = new JLayeredPane();
                parentMail.setPreferredSize(new Dimension(500, 50));
                ImageIcon backroundMailIcon = new ImageIcon(drawBackroundScan(false));
                JLabel backroundMail = new JLabel(backroundMailIcon);
                createScanMail();
                parentMail.add(backroundMail);
                backroundMail.setBounds(0,0, 500, 50);
                parentMail.setBounds(400, 300, 500, 50);
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
                JLabel  iconEyes = new JLabel(imgEyes);
                iconEyes.setBounds(440, 0, 50, 50);
                iconEyes.setBorder(myBorder);
                iconEyes.setOpaque(false);
                iconEyes.setBackground(new Color(0, 0, 0, 0));
                iconEyes.addMouseListener(new  MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.err.println("HI");
                        if(!isEyes)
                        {
                            isEyes = true;
                            iconEyes.setIcon(new ImageIcon("../resources/images/Sleep.png"));
                            scanPass.setEchoChar((char) 0);
                           
                        }else{
                            isEyes = false;
                            iconEyes.setIcon(new ImageIcon("../resources/images/Show.png"));
                            String getPass= new String(scanPass.getPassword());
                            if(getPass.equals("PASSWORD"))
                            {
                                scanPass.setEchoChar((char) 0);
                            }else{
                                scanPass.setEchoChar('*');
                            }
                            
                        }
                    }
                    
                });
                parentPass.add(iconEyes);
                parentPass.add(backroundPass);
                backroundPass.setBounds(0,0, 500, 50);
                parentPass.setBounds(400, 380, 500, 50);
                // parentMail.setBackground(Color.BLACK);
                parentPass.setOpaque(false);
                add(parentPass);

                createAccount();
                createButtonNext(70, false);
                System.out.println(x+""+y);
            }    
        }
    }
    

    BufferedImage drawButtonNext( int width, boolean isText)
    {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(width, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLUE);
        g2d.fillRoundRect(0, 0, width, 50, 50, 50);
        g2d.setColor(Color.WHITE);
        if(isText)
        {
           
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            String text = "NEXT";
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            int x = (width - textWidth) / 2;
            int y = (50 - textHeight) / 2 + fm.getAscent();
            g2d.drawString(text, x, y);
        }
       
       
        Image img = new ImageIcon("../resources/images/nextIMG.png").getImage();
        g2d.drawImage(img,width- 40,5, null);
        g2d.dispose();
        return roundedImage;
    }
    
    void createButtonNext(int width, boolean isText)
    {
        
         // Tạo ImageIcon từ hình ảnh
        roundedIcon= new ImageIcon(drawButtonNext(width,isText));
        
        // Tạo JLabel và thiết lập hình ảnh
        buttonNextGame = new JLabel(roundedIcon);
        buttonNextGame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                App.frame.remove(App.loginPanel);
                App.newGame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                }

            @Override
            public void mouseReleased(MouseEvent e) {
                }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!isHover)
                {
                    isHover = true;
                    System.out.println("Hi");
                    remove(LoginPanel.buttonNextGame);
                    createButtonNext(150, true);
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
                if(isHover){
                    isHover = false;
                    
                    buttonNextGame.setIcon(null);
                    createButtonNext(70, false);
                }
               
            }
            
        });
        buttonNextGame.setBounds(800, 500, width, 50);
        buttonNextGame.setOpaque(false);
        add(buttonNextGame);
    }

    public boolean validateEmailStrict() {
        String regexPattern ="^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern).matcher(scanMail.getText()).matches();
    } 
    void effectValidBorder()
    {
        if(!validateEmailStrict())
        {
            Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.RED);
            scanMail.setBorder(myBorder);
        }else{
            Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.GREEN);
            scanMail.setBorder(myBorder);
        }
    }
}
