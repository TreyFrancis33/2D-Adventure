import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public int worldX,worldY,speed,spriteCounter = 0,spriteNum = 1,solidAreaX,solidAreaY;
    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2;
    public String direction;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}

class Player extends Entity {
    GamePanel gp;
    KeyEventClass keyC;
    public final int screenX, screenY;
    int hasKey = 0;
    Graphics2D g2;

    public Player(GamePanel gp, KeyEventClass keyC) {
        this.gp = gp;
        this.keyC = keyC;
        screenX = 768 / 2 - 48 / 2;
        screenY = 576 / 2 - 48 / 2;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaX = (int)solidArea.getX();
        solidAreaY = (int)solidArea.getY();
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 48 * 23;
        worldY = 48 * 21;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyC.upPressed || keyC.downPressed || keyC.leftPressed || keyC.rightPressed) {
            if (keyC.upPressed) {direction = "up";}
            else if (keyC.downPressed) {direction = "down";}
            else if (keyC.leftPressed) {direction = "left";}
            else {direction = "right";}

            collisionOn = false;
            gp.cc.checkTile(this);
            int objIndex = gp.cc.checkObject(this, true);
            pickUpObject(objIndex);

            if (!collisionOn)
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {spriteNum = 2;}
                else if (spriteNum == 2) {spriteNum = 1;}
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objName = (gp.obj[index]).name;
            switch (objName) {
                case "Key" -> {
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("This key might open something");
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.playSoundEffect(3);
                        gp.obj[index] = null;
                        hasKey--;
                        gp.ui.showMessage("The key worked!");
                    } else {
                        gp.ui.showMessage("This door seems to be locked");
                    }
                }
                case "Boots" -> {
                    gp.playSoundEffect(2);
                    speed += 2;
                    gp.obj[index] = null;
                    gp.ui.showMessage("Wow you got faster");
                }
                case "Chest" -> {
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(4);
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
            }
            case "down" -> {
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
            }
            case "left" -> {
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
            }
            case "right" -> {
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, 48, 48, null);
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        } catch (IOException e) {e.printStackTrace();}
    }
}
