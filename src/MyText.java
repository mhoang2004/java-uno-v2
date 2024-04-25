
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
public abstract class MyText extends JPanel {
    // compunent of this
    protected JTextField scanText ;
    private JLabel labelText;
    // properti for mytext;
    boolean isClick ;
    boolean isKeyed = false;
    MyText()
    {
        super(); 
        
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(1080/2, 60));
        this.setLayout(new FlowLayout());
    }
    abstract boolean isPassword();
    abstract void backDefault();
    String  getText() { return scanText.getText();}

    void setText(String text)
    {
        scanText.setText(text) ;
    }
    int  sizeText()
    {
        if(!isKeyed)
        {
            return 0;
        }
        return getText().length();
    }
}
