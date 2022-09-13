import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    public final int screenWidth = 768;
    public final int screenHeight = 576;
    Thread gameThread;

    KeyEventClass keyC = new KeyEventClass();
    public CheckCollisions cc = new CheckCollisions(this);
    public ObjectSetter assetSetter = new ObjectSetter(this);
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public Player player = new Player(this, keyC);

    public SuperObject[] obj = new SuperObject[10];
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public UI ui = new UI(this);

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyC);
        setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObj();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1.0E9D / FPS;
        double delta = 0.0D;
        long lastTime = System.nanoTime();
        long timer = 0L;
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1.0D) {
                update();
                repaint();
                delta--;
            }
            if (timer >= 1000000000L)
                timer = 0L;
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        for (SuperObject superObject : obj) {
            if (superObject != null)
                superObject.draw(g2, this);
        }
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}