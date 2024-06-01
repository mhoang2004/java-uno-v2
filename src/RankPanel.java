import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyJLabel extends JLabel {
    MyJLabel(String text, Boolean isHeader) {
        this.setText(text);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setSize(RankPanel.WIDTH, RankPanel.HEIGHT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);

        if (isHeader) {
            this.setFont(this.getFont().deriveFont(Font.BOLD, 14f));
            this.setBackground(Color.YELLOW);
            this.setForeground(Color.RED);
        }
    }
}

public class RankPanel extends JPanel {
    static int WIDTH = MyPanel.WIDTH / 5;
    static int HEIGHT = 20;

    RankPanel() {
        List<Map<String, String>> data = FileHandler.getAllUsersData();
        int numberOfRows = data.size() + 2;
        int numberOfColumns = 4;

        this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        this.setBounds((MyPanel.WIDTH - WIDTH) / 2, (MyPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);

        // back btn
        ImageIcon backIcon = new ImageIcon("../resources/images/backImg.png");
        JLabel backLable = new JLabel(backIcon);
        backLable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                App.frame.setVisible(false);
                App.frame.remove(App.rankPanel);
                App.homePanel = new HomePanel("../resources/images/BackroundBegin-1.jpg", LoginPanel.accountUser);
                App.frame.add(App.homePanel);
                App.frame.setVisible(true);

                System.out.println("Hello from back bnt in rankPanel!");
            }
        });

        this.add(backLable);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));

        this.add(new MyJLabel("ID", true));
        this.add(new MyJLabel("Username", true));
        this.add(new MyJLabel("Best Score", true));
        this.add(new MyJLabel("Register Date", true));

        for (Map<String, String> row : data) {
            this.add(new MyJLabel(row.get("id"), false));
            this.add(new MyJLabel(row.get("username"), false));
            this.add(new MyJLabel(row.get("bestScore"), false));
            this.add(new MyJLabel(row.get("date"), false));
        }
    }

    public static void main(String[] args) {
        RankPanel res = new RankPanel();
        MyFrame f = new MyFrame();
        f.add(res);
        f.setSize(1000, 500);
        f.setVisible(true);
    }
}
