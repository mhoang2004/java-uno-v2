import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Two-Sided JLabel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a JLabel with HTML formatting
        JLabel label = new JLabel(
                "<html><div style='text-align: center;'><font color='red'>Left Side</font><br><font color='blue'>Right Side</font></div></html>");

        // Add the label to the frame
        frame.add(label);

        frame.setVisible(true);
    }
}
