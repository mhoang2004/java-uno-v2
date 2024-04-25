
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                Border myBorder= BorderFactory.createMatteBorder(0, 0,  2, 0, Color.BLUE);
                scanText.setBorder(myBorder);
                    LoginPage.scanPass.backDefault();     
                if(sizeText() == 0)
                scanText.setText("");      
            }
        });
        this.add(scanText);
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
}
