
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
                LoginPage.scanMail.backDefault();     
                if(sizeText() == 0)
                    scanText.setText(""); 
                ((JPasswordField) scanText).setEchoChar('*');     
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
}
