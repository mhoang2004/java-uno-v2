import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;
import javax.swing.border.LineBorder;


public class MyText extends JPanel {
    public JTextField input ;
    String type;
    boolean isClick ;
    boolean isPassword = false;
    MyText(String type)
    {
        super(); 
        this.type = type;
        if(type.trim().length() > 5)
        {
            isPassword = true;
        }
        this.setPreferredSize(new Dimension(1080/2, 60));
        this.setLayout(new BorderLayout());
        // setting text
        input = new JTextField();
        input.setPreferredSize(new Dimension(1080/2, 60));
        this.backDefault();
        input.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                input.setBorder(new LineBorder(LoginPage.MAINCOLOR, 3));
                if(isPassword)
                {
                    if(LoginPage.scanMail.getText().trim().length() < 1)
                        LoginPage.scanMail.backDefault();
                }  
                else
                {
                    if(LoginPage.scanPass.getText().trim().length()  < 1)
                        LoginPage.scanPass.backDefault();     
                }
                 input.setText("");
                
            }
        });
        this.add(input, BorderLayout.CENTER);

    }
    void backDefault()
    {
        input.setText(type);
        input.setBorder(new LineBorder(Color.black, 1));
    }
    String  getText()
    {
        return input.getText();
    }
    void setText(String text)
    {
        type = text;
        input.setText(type);;
    }
}
