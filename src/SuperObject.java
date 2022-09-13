import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public int solidAreaX = 0;
    public int solidAreaY = 0;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + 48 > gp.player.worldX - gp.player.screenX &&
                worldX - 48 < gp.player.worldX + gp.player.screenX &&
                worldY + 48 > gp.player.worldY - gp.player.screenY &&
                worldY - 48 < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, 48, 48, null);
        }
    }
}

class Key extends SuperObject {
    public Key() {
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch (IOException e) {e.printStackTrace();}
    }
}

class Door extends SuperObject {
    public Door() {
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        } catch (IOException e) {e.printStackTrace();}
        collision = true;
    }
}

class Chest extends SuperObject {
    public Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        } catch (IOException e) {e.printStackTrace();}
    }
}

class Boots extends SuperObject {
    public Boots() {
        name = "Boots";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



class ObjectSetter {
    GamePanel gp;
    public ObjectSetter(GamePanel gp) {this.gp = gp;}

    public void setObj() {
        gp.obj[0] = new Key();
        (gp.obj[0]).worldX = 23 * 48;
        (gp.obj[0]).worldY = 7 * 48;
        gp.obj[1] = new Key();
        (gp.obj[1]).worldX = 23 * 48;
        (gp.obj[1]).worldY = 40 * 48;
        gp.obj[2] = new Key();
        (gp.obj[2]).worldX = 38 * 48;
        (gp.obj[2]).worldY = 10 * 48;
        gp.obj[3] = new Door();
        (gp.obj[3]).worldX = 10 * 48;
        (gp.obj[3]).worldY = 11 * 48;
        gp.obj[4] = new Door();
        (gp.obj[4]).worldX = 8 * 48;
        (gp.obj[4]).worldY = 28 * 48;
        gp.obj[5] = new Door();
        (gp.obj[5]).worldX = 12 * 48;
        (gp.obj[5]).worldY = 22 * 48;
        gp.obj[6] = new Chest();
        (gp.obj[6]).worldX = 10 * 48;
        (gp.obj[6]).worldY = 7 * 48;
        gp.obj[7] = new Boots();
        (gp.obj[7]).worldX = 35 * 48;
        (gp.obj[7]).worldY = 40 * 48;
    }
}

