public class App {
    static MyFrame frame;

    public static void main(String[] args) throws Exception {
        frame = new MyFrame();
        Game game = new Game();

        game.isMouseClicked();

        frame.add(Game.mainPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
