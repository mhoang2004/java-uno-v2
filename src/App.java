public class App {
    static public MyFrame frame;
    static LoginPage panelLogin ;
    public static void main(String[] args) throws Exception {
        frame = new MyFrame();
        panelLogin = new LoginPage();
        frame.add(panelLogin);
        // button uno
        frame.pack();
        frame.setVisible(true);
    }
}
