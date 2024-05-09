
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.Timer;

public class ChooseColorPanel extends JLabel  {
    static int WIDTH = 500;
    static int HEIGHT = 50;
    static ButtonColor redButtonColor;
    static ButtonColor blueButtonColor;
    static ButtonColor yellowButtonColor;
    static ButtonColor greenButtonColor;
    Timer timer2 ;
    ChooseColorPanel() {
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        this.setLayout(new GridLayout());
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        redButtonColor= createButton(redButtonColor, "RED");
        blueButtonColor = createButton(blueButtonColor, "BLUE");
        yellowButtonColor = createButton(yellowButtonColor, "YELLOW");
        greenButtonColor= createButton(greenButtonColor, "GREEN");

    }

    private ButtonColor createButton(ButtonColor buttonColor , String color) {
        buttonColor = new ButtonColor(color, this);
        ButtonColor buttonColor2 = buttonColor;
        return buttonColor2;
    }

    void removeAllButton()
    {
        Game.mainPanel.remove(ChooseColorPanel.redButtonColor);
        Game.mainPanel.remove(ChooseColorPanel.blueButtonColor);
        Game.mainPanel.remove(ChooseColorPanel.yellowButtonColor);
        Game.mainPanel.remove(ChooseColorPanel.greenButtonColor);
    }

    void zoomIn(ButtonColor buttonColor)
    {
        if(buttonColor.isHover)
        {
            buttonColor.setLocation(buttonColor.getText(), WIDTH-30, HEIGHT-30);
        }
    }
    void exited()
    {
        redButtonColor.backDefault();
        blueButtonColor.backDefault();
        yellowButtonColor.backDefault();
        greenButtonColor.backDefault();
    }
}
