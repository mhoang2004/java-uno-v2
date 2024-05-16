import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Reverse extends JLabel {
    static final int WIDTH = 330;
    static final int HEIGHT = 160;
    String path;
    ImageIcon icon;
    Reverse img = this;
    int x = 0;
    int y = 0;

    Reverse(String pos) {
        if (Game.isReverse == true) {
            path = "../resources/images/" + pos + "-" + Game.prevCard.getColor() + "-" + Game.isReverse + ".png";
            ImageIcon icon = new ImageIcon(path);
            this.setIcon(icon);
            if (pos.equals("L")) {
                this.setBounds(((MyPanel.WIDTH - Card.WIDTH) / 2) - 2 * Card.WIDTH - 180,
                        (MyPanel.HEIGHT - Card.HEIGHT) / 2 - Card.WIDTH - 30, WIDTH, HEIGHT);
            } else {
                this.setBounds(((MyPanel.WIDTH - Card.WIDTH) / 2) + 2 * Card.WIDTH - 50, 370, WIDTH, HEIGHT);
            }
        } else {
            // if(pos.equals("L"))
            // {
            // pos = "R";
            // }else{
            // pos = "L";
            // }
            path = "../resources/images/" + pos + "-" + Game.prevCard.getColor() + "-" + Game.isReverse + ".png";
            ImageIcon icon = new ImageIcon(path);
            this.setIcon(icon);
            if (pos.equals("L")) {
                this.setBounds(((MyPanel.WIDTH - Card.WIDTH) / 2) - 2 * Card.WIDTH - 180,
                        (MyPanel.HEIGHT - Card.HEIGHT) / 2 - Card.WIDTH - 30, WIDTH, HEIGHT);
            } else {
                this.setBounds(((MyPanel.WIDTH - Card.WIDTH) / 2) + 2 * Card.WIDTH - 50, 370, WIDTH, HEIGHT);
            }
        }
        this.setHorizontalAlignment(JLabel.CENTER); // Center the image horizontally
        this.setVerticalAlignment(JLabel.CENTER); // Center the image vertically
        this.setOpaque(false);
        // this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setLayout(null);
        setVisible(true);
    }

    public void updateReverse(String pos) {
        path = "../resources/images/" + pos + "-" + Game.prevCard.getColor() + "-" + Game.isReverse + ".png";
        ImageIcon icon = new ImageIcon(path);
        this.setIcon(icon);
    }

    public void updateReverse2(String pos, String coloString) {
        path = "../resources/images/" + pos + "-" + coloString + "-" + Game.isReverse + ".png";
        ImageIcon icon = new ImageIcon(path);
        this.setIcon(icon);
    }
}
