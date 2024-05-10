import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ButtonColor extends JLabel implements MouseListener{
    String color;
    static final int WIDTH = 150;
    static final  int HEIGHT = 150;
    int x; 
    int y;
    int x1=-MyPanel.WIDTH ;
    int gap =10;
    ChooseColorPanel controler;
    boolean isHover = false;
    JLabel animationJLabel = new JLabel();
    Timer timer ;
    ButtonColor(String color, ChooseColorPanel controler)
    {
        this.color = color;
        this.controler = controler;
        setText(color);
        this.setOpaque(true);
        animationJLabel.setOpaque(true);
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa ngang
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setFont(new Font("Arial", Font.BOLD, 20));
         setLocation(color, WIDTH, HEIGHT);
       this.addMouseListener(this);
       animationJLabel.setBounds(-MyPanel.WIDTH, 0, MyPanel.WIDTH, MyPanel.HEIGHT);
         Game.addToMainPanel(animationJLabel);
        Game.addToMainPanel(this);
    }
    void  setLocation(String color, int WIDTH, int HEIGHT)
    {
        if (color.equals("RED"))
        {
            this.setBackground(Color.RED);
            animationJLabel.setBackground(Color.RED);

            x = (MyPanel.WIDTH - WIDTH) / 2 -30 ;
            y =(MyPanel.HEIGHT - HEIGHT) / 2-30 ;
            this.setBounds(x,y , WIDTH, HEIGHT);
            this.setForeground(new Color(139,0,0));

        }
        else if (color.equals("BLUE"))
        {
            this.setBackground(Color.BLUE);
            animationJLabel.setBackground(Color.BLUE);
            x = ChooseColorPanel.redButtonColor.X() + ChooseColorPanel.redButtonColor.getWidth() + gap;
            y = ChooseColorPanel.redButtonColor.Y() ;
            this.setBounds(x, y , WIDTH, HEIGHT);
            this.setForeground(new Color(0,0,100));
        }
        else if (color.equals("YELLOW"))
        {
            this.setBackground(Color.YELLOW);
            animationJLabel.setBackground(Color.YELLOW);
            x = ChooseColorPanel.redButtonColor.X() ;
            y = ChooseColorPanel.redButtonColor.Y() +  ChooseColorPanel.redButtonColor.getWidth() + gap;
            this.setBounds(x , y, WIDTH, HEIGHT);
            this.setForeground(new Color(205,173,0));
        }
        else if (color.equals("GREEN"))
        {
            this.setBackground(Color.GREEN);
            animationJLabel.setBackground(Color.GREEN);
            x = ChooseColorPanel.redButtonColor.X() + ChooseColorPanel.redButtonColor.getWidth() +  gap ;
            y = ChooseColorPanel.redButtonColor.Y() +  ChooseColorPanel.redButtonColor.getWidth() + gap;
            this.setBounds( x, y, WIDTH, HEIGHT);
            this.setForeground(new Color(0,100,0));
        }
    }
    void removeButton()
    {
        Game.mainPanel.remove(this);
    }
    String getColor()
    {
        return color.charAt(0) +"";
    }
   void  zoomIn()
   {
        this.setBounds(x,y , WIDTH+30, HEIGHT+30);
   }
   void  zoomOut()
   {
    if (color.equals("RED"))
        {
            this.setBounds(x,y , WIDTH-30, HEIGHT-30);
        }
        else if (color.equals("BLUE"))
        {
            this.setBounds(x, y , WIDTH-30, HEIGHT-30);
        }
        else if (color.equals("YELLOW"))
        {
            this.setBounds(x , y-20, WIDTH-30, HEIGHT-30);
        }
        else if (color.equals("GREEN"))
        {
            this.setBounds( x +30, y+30, WIDTH-30, HEIGHT-30);
        }
   }
   void backDefault()
   {
        this.setBounds(x,y, WIDTH, HEIGHT);
   }
  int X()
  {
    return x;
  }
  int Y()
  {
    return y;
  }
  boolean isHover()
  {
    return isHover;
  }
@Override
public void mouseClicked(MouseEvent e) {
    controler.removeAllButton();
     timer = new Timer(1, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(x1 <MyPanel.WIDTH*2 )
            {
                x1+= gap*4;
                animationJLabel.setBounds(x1, 0,MyPanel.WIDTH, MyPanel.HEIGHT);
                if(x1 > MyPanel.WIDTH-500)
                {
                    Game.vectorLeft.updateReverse2("L", getColor());
                    Game.vectorRight.updateReverse2("R", getColor());
                }
            }else{
                timer.stop();
                Game.mainPanel.remove(animationJLabel);
                Game.prevCard.setColor(getColor());
                Game.mainPanel.repaint();
                Game.delayReverse(3);
                Game.checkTheCase();
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
    isHover = true;

    this.setLocation(color, WIDTH +10, HEIGHT+10);
    // controler.zoomIn(this);
    
}
@Override
public void mouseExited(MouseEvent e) {
    isHover = false;
   controler.exited();
}
}
