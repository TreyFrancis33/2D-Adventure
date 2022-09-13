public class CheckCollisions {
    GamePanel gp;

    public CheckCollisions(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int tileNum1, tileNum2, entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entityLeftX / 48;
        int entityRightCol = entityRightX / 48;
        int entityTopRow = entityTopY / 48;
        int entityBottomRow = entityBottomY / 48;
        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / 48;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if ((gp.tileM.tile[tileNum1]).collision || (gp.tileM.tile[tileNum2]).collision)
                    entity.collisionOn = true;
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / 48;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if ((gp.tileM.tile[tileNum1]).collision || (gp.tileM.tile[tileNum2]).collision)
                    entity.collisionOn = true;
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / 48;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if ((gp.tileM.tile[tileNum1]).collision || (gp.tileM.tile[tileNum2]).collision)
                    entity.collisionOn = true;
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / 48;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if ((gp.tileM.tile[tileNum1]).collision || (gp.tileM.tile[tileNum2]).collision)
                    entity.collisionOn = true;
            }
        }
    }

    public int checkObject(Entity e, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;
                (gp.obj[i]).solidArea.x = (gp.obj[i]).worldX + (gp.obj[i]).solidArea.x;
                (gp.obj[i]).solidArea.y = (gp.obj[i]).worldY + (gp.obj[i]).solidArea.y;
                switch (e.direction) {
                    case "up" -> {
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects((gp.obj[i]).solidArea)) {
                            if ((gp.obj[i]).collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                    }
                    case "down" -> {
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects((gp.obj[i]).solidArea)) {
                            if ((gp.obj[i]).collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                    }
                    case "left" -> {
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects((gp.obj[i]).solidArea)) {
                            if ((gp.obj[i]).collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                    }
                    case "right" -> {
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects((gp.obj[i]).solidArea)) {
                            if ((gp.obj[i]).collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                    }
                }
                e.solidArea.x = e.solidAreaX;
                e.solidArea.y = e.solidAreaY;
                (gp.obj[i]).solidArea.x = e.solidAreaX;
                (gp.obj[i]).solidArea.y = e.solidAreaY;
            }
        }
        return index;
    }
}
