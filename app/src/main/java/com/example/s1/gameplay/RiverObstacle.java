package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class RiverObstacle extends Drawable {
    RiverObstacle() { };

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }

    private Paint paint = new Paint();
    private boolean toDelete = false;

    private int speed;

    private VehicleTypes type;

    public RiverObstacle(int yPos, Player player, VehicleTypes type) {
        this.player = player;
        this.xPos = -100;
        this.yPos = yPos;
        this.width = 120;
        this.height = 120;
        this.type = type;
        if (type == VehicleTypes.LOG1) {
            speed = 3;
        }
        if (type == VehicleTypes.LOG2) {
            speed = 5;
        }
        paint.setStrokeWidth(3);
        paint.setColor(Color.GRAY);
    }

    public void draw(Canvas c) {
        if (!toDelete) { // don't bother rendering it if it's been marked for deletion
            c.drawRect(xPos, yPos, xPos + width, yPos + height, paint);
        }
    }

    public void tick() {
        xPos += speed;
        if (xPos > 50 * 21) {
            toDelete = true; // mark for deletion
        }
    }

    public void movePlayerX() {
        player.moveX(speed);
    }

    public VehicleTypes getType() {
        return type;
    }
    public int getXPos() {
        return xPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean movesOffScreen() {
        xPos = 50 * 21 + 1;
        tick();
        return toDelete;
    }

    public boolean staysOnScreen() {
        xPos = (int) (50 * 20 * Math.random());
        return !toDelete;
    }
}
