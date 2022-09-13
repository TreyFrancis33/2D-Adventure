import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font timesNR, timesNR2;
    BufferedImage keyImg;
    public boolean messageOn = false;
    public boolean gameFinished = false;
    public String message = "";
    int msgCount = 0;
    double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");
    Color color = new Color(0,0,0,200);

    public UI(GamePanel gp) {
        this.gp = gp;
        timesNR = new Font("Times New Roman", Font.BOLD, 40);
        timesNR2 = new Font("Times New Roman", Font.BOLD, 80);
        Key key = new Key();
        keyImg = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setColor(color);
            g2.fillRoundRect(60,90,650,320,35,35);
            g2.setFont(timesNR);
            g2.setColor(Color.WHITE);
            String text = "You found the treasure!";
            int textLen = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = 768/2 - textLen/2;
            int y = 576/2 - 48*3;
            g2.drawString(text, x, y);
            text = "Time: " + df.format(playTime);
            textLen = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = 768/2 - textLen/2;
            y = 576/2 - 48;
            g2.drawString(text, x, y);
            g2.setFont(timesNR2);
            g2.setColor(Color.WHITE);
            text = "Congratulations";
            textLen = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = 768/2 - textLen/2;
            y = 576/2 + 48*2;
            g2.drawString(text, x, y);
            gp.gameThread = null;
        } else {
            g2.setColor(color);
            g2.fillRoundRect(10,10,90,70,35,34);
            g2.fillRoundRect(275,10,220,70,35,35);
            g2.fillRoundRect(550,10,200,100,35,35);
            g2.setFont(timesNR);
            g2.drawImage(keyImg,18,24,48,48, null);
            g2.setColor(Color.WHITE);
            g2.drawString("x" + gp.player.hasKey,50,68);
            g2.drawString("Time: " + df.format(playTime += (double)1/60),285,60);
            g2.drawString("Controls:",570,55);
            g2.drawString("WASD", 590, 100);
            if (messageOn) {
                g2.setColor(Color.WHITE);
                g2.setFont(g2.getFont().deriveFont(28.0F));
                g2.drawString(message,30,180);
                msgCount++;
                if (msgCount > 120) {
                    msgCount = 0;
                    messageOn = false;
                }
            }
        }
    }
}
