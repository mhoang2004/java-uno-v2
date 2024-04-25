
public class Main {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        LoginPage panelLogin = new LoginPage();
        frame.add(panelLogin);
        frame.pack();
        frame.setVisible(true);
    }
}
