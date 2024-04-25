
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

public class MyPassword extends MyText {
    MyPassword()
    {
        super();
        scanText = new JPasswordField("Password");
        scanText.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
        scanText.setFont(new Font("Arial", ALLBITS, 20));
        scanText.setPreferredSize(new Dimension(500, 50));      
        backDefault();
        
        scanText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.BLUE);
                scanText.setBorder(myBorder);
                
                if(LoginPage.scanMail.sizeText() == 0)
                {
                    LoginPage.scanMail.backDefault();     
                }
                if(!isClick())  
                    LoginPage.scanPass.setText("");

                ((JPasswordField) scanText).setEchoChar('*');     
            }
        });
        scanText.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                }

            @Override
            public void keyPressed(KeyEvent e) {
                }

            @Override
            public void keyReleased(KeyEvent e) {
               
            }
            
        });
        this.add(scanText);
    }
    @Override
    boolean isPassword() {
       return true;
    }   
    @Override
    void backDefault() {
        scanText.setText("Password");
        Border myBorder= BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);
        scanText.setBorder(myBorder);
        ((JPasswordField) scanText).setEchoChar((char) 0);
    }
    int  sizeText()
    {
        if(!isClick())
        {
            return 0;
        }
        return getText().length();      
    }
    boolean isClick()
    {
        return LoginPage.scanPass.getText().equals("Password") ? false:true;
    }
}
