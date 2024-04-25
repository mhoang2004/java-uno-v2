import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class LoginPage extends JPanel{
    static final Color BG_COLOR = new Color(3, 104, 63);
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    JPanel logoGame ;
    LoginPage x = this;
    JPanel locginGame;
    // compunent input mail
    JPanel inputMail; // parent
    JLabel messageMail;
    static MyText scanMail;
    // compunent input password
    JPanel inputPassword;// parent
    JLabel messagePassword; 
     static public JLabel forgotAccount;
    //Color main
    static public Color MAINCOLOR = new Color(30 ,144 ,255);
    static MyText scanPass; 
    LoginPage()
    {
        this.setLayout(new FlowLayout());
        createLogoGame();
        createLoginGame();
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                scanMail.backDefault();
                scanPass.backDefault();
            }
        });
        
    }
    // page login 
    void createLoginGame()
    {
        // setting page 
        locginGame = new JPanel();
        locginGame.setLayout(new BorderLayout());
        locginGame.setBackground(Color.WHITE);
        JPanel mainLogin = new JPanel();
        mainLogin.setLayout(new GridLayout(2, 1, 20, 20));
        mainLogin.setSize(WIDTH/2 , HEIGHT -200);
        mainLogin.setBackground(Color.WHITE);
        locginGame.setSize(new Dimension(WIDTH/2, HEIGHT));
        // new input mail
        inputMail = new JPanel();
        inputMail.setBackground(Color.WHITE);
        // inputMail.setPreferredSize(new Dimension(WIDTH/2,100));
        // inputMail.setLayout(new GridLayout(2, 1) );
        // new massage mail
        JPanel nullPanel = new JPanel();
        nullPanel.setSize(new Dimension(1080/2, HEIGHT/4));
        nullPanel.setPreferredSize(new Dimension(1080/2, HEIGHT/4));
        scanMail = new MyTextField();    
        scanMail.setLocation(0, 30); 
        inputMail.add(scanMail);
        // new input password
        inputPassword = new JPanel();
        inputPassword.setBackground(Color.WHITE);
        inputPassword.setSize(new Dimension(WIDTH/2, 100));
        // new massage password
        scanPass  =new MyPassword(); 
        inputPassword.add(scanPass);
        //add compunent
        mainLogin.add(inputMail);
        mainLogin.add(inputPassword);
        locginGame.add(mainLogin, BorderLayout.CENTER);
        myButton myButton = new  myButton("NEXT");
        myButton.setPreferredSize(new Dimension(30, 40));
        myButton.setBounds(1000, 300, 40, 40);
        myButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                App.frame.remove(x);
                Game game = new Game();
                App.frame.add(Game.mainPanel);
                game.start();
                // game.addToMainPanel(new EndGame());
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (Game.isEndGame == true) {
                            Game game = new Game();
                            game.start();
                        }
                    }
                });
            }
        });
        JPanel buttonLogin = new JPanel();
        buttonLogin.setLayout(new GridLayout());
        forgotAccount = new JLabel("Forgot Account");
        forgotAccount.setFont(new Font(null, Font.BOLD, 15));
        forgotAccount.setForeground(MAINCOLOR);
        forgotAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                    LoginPage.forgotAccount.setForeground(new Color(0, 0, 128));
        
            }
            @Override
            public void mouseExited(MouseEvent e) {
                LoginPage.forgotAccount.setForeground(LoginPage.MAINCOLOR);
            }
        });
        buttonLogin.add(forgotAccount);
        buttonLogin.add(new JLabel());
        buttonLogin.setBackground(Color.WHITE);
        buttonLogin.add(myButton);
        locginGame.add(buttonLogin, BorderLayout.SOUTH);
        this.add(locginGame);    
    }
    // page logo
    void createLogoGame()
    {
        logoGame = new JPanel();
        logoGame.setBackground(Color.WHITE);
        logoGame.setLayout(new GridLayout());
        logoGame.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        // Maggese
        JLabel label = new JLabel("SIGN IN");
        label.setFont(new Font("Arial", ALLBITS, ABORT));
        
        // add compunent
        logoGame.add(label);
        this.add(logoGame);  
    }
}
