import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        GamePanel gamePanel = new GamePanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(400, 125);
        setResizable(false);
        setTitle("2D Adventure");
        add(gamePanel);
        pack();
        setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    public static void main(String[] args) {new GameFrame();}
}