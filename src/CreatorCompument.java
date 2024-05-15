import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class CreatorCompument {
    static BufferedImage createRoundButton(JLayeredPane label,int flur)
    {
            BufferedImage roundedImage = new BufferedImage( BeginPanel.widthLogin+30,  BeginPanel.heigthLogin+30, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = roundedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(0, 0, 0,flur) );
             g2d.fillRoundRect(0, 0, BeginPanel.widthLogin+30,BeginPanel.heigthLogin+30,BeginPanel.widthLogin+30-300,BeginPanel.heigthLogin+30-20);
            g2d.dispose();  
            return roundedImage;
    }
    static BufferedImage drawButtonBack(int width, boolean isText) {
        int flur=0;
        if(isText == true)
        {
            flur = 100;
        }
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(width, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255, 255, 255, flur));
        g2d.fillRoundRect(3, 0, width, 50, 50, 50);
        g2d.setColor(Color.WHITE);
        if (isText) {
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            String text ;
            text ="BACK";
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            int x = (width - textWidth) / 2;
            int y = (50 - textHeight) / 2 + fm.getAscent();
             g2d.drawString(text, 70, y+5);
        }

        String pathIcon = new String("../resources/images/");
        pathIcon += "BackIMG"+".png";
        Image img = new ImageIcon(pathIcon).getImage();
            g2d.drawImage(img, 2, 0, null);
        g2d.dispose();
        return roundedImage;
    }
    static BufferedImage drawButtonNew(int width, boolean isText) {
        int flur=0;
        if(isText == true)
        {
            flur = 70;
        }
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(width, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255, 255, 255, flur));
        g2d.fillRoundRect(0, 0, width, 50, 50+10, 50+10);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.dispose();
        return roundedImage;
    }
    static BufferedImage drawButtonNext(int width, boolean isText, String path,  int flur) {
        // Tạo hình ảnh bo cong 4 góc
        BufferedImage roundedImage = new BufferedImage(width, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 200, flur));
        g2d.fillRoundRect(0, 0, width, 50, 50, 50);
        g2d.setColor(Color.WHITE);
        if (isText) {

            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            String text ;
            if(path.equals("backIMG")){
                text ="BACK";
            }else{
                text ="NEXT";
            }
            
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getHeight();
            int x = (width - textWidth) / 2;
            int y = (50 - textHeight) / 2 + fm.getAscent();
            if(path.equals("backIMG")){
                g2d.drawString(text, 70, y);
            }else{
                g2d.drawString(text, x, y);
            }
            
        }

        String pathIcon = new String("../resources/images/");
        pathIcon += path+".png";
        Image img = new ImageIcon(pathIcon).getImage();
        if(path.equals("backIMG")){
            g2d.drawImage(img, 10, 0, null);
        }else{
            g2d.drawImage(img, width - 40, 5, null);
        }
        
        g2d.dispose();
        return roundedImage;
    }
}
