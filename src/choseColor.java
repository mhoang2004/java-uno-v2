import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choseColor  implements ActionListener{
    MyFrame view;
    choseColor(MyFrame view)
    {
        this.view = view ;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("CLICKINGGGGGGG");
        String src;
        src = e.getActionCommand();
        src =src.charAt(0) +"";
        view.changePrevCard(src);
        System.out.println("test: " + Game.prevCard.toString());    
            
        view.setVisible(false);
    }
}
    
