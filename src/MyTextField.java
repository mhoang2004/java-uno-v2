
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class MyTextField extends MyText{
    MyTextField()
    {
        super();
        scanText = new JTextField();
        scanText.setBackground(Color.WHITE);
        scanText.setFont(new Font("Arial", ALLBITS, 20));
        this.setBackground(Color.WHITE); 
        backDefault();
        scanText.setPreferredSize(new Dimension(500, 50));
        scanText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {

                  
                if(LoginPage.scanPass.sizeText() == 0)
                    LoginPage.scanPass.backDefault(); 
                if(!isClick())  
                {
                    LoginPage.scanMail.setText("");
                    Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.BLUE);
                    scanText.setBorder(myBorder);   
                }else{
                    effectValidBorder();
                }
                   
                              
            }
        });
        scanText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                effectValidBorder();
            }
        }
        
        );
        this.add(scanText);
    }
    void effectValidBorder()
    {
        if(!MyTextField.validateEmailStrict())
        {
            Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.RED);
            scanText.setBorder(myBorder);
        }else{
            Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.GREEN);
            scanText.setBorder(myBorder);
        }
    }
    @Override
    boolean isPassword() {
       return false;
    }
    @Override
    void backDefault() {
        scanText.setText("Email");
        Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.BLACK);
        scanText.setBorder(myBorder);
    }  
    public static boolean validateEmailStrict() {
        String regexPattern ="^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern).matcher(LoginPage.scanMail.getText()).matches();
    } 
    int  sizeText()
    {
        if(!isClick())
        {
            return 0;
        }
        return LoginPage.scanMail.getText().length();      
    }
    boolean isClick()
    {
        return LoginPage.scanMail.getText().equals("Email") ? false:true;
    }
}
