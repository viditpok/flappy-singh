package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class Rocket extends Drawable {
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

    private int speed = -30;

    public Rocket(int yPos, Player player) {
        this.player = player;
        this.xPos = 2000;
        this.yPos = yPos;
        this.width = 120;
        this.height = 120;
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
    }

    public void draw(Canvas c) {
        if (!toDelete) { // don't bother rendering it if it's been marked for deletion
            if (xPos > 9 * 120) {
                c.drawRect(9 * 120 - 30, yPos + 20, 9 * 120 - 10, yPos + 70, paint);
                c.drawRect(9 * 120 - 30, yPos + 90, 9 * 120 - 10, yPos + 110, paint);
            }
            c.drawRect(xPos, yPos, xPos + width, yPos + height, paint);
        }
    }

    public void tick() {
        xPos += speed;
        if (xPos < -100) {
            toDelete = true; // mark for deletion
        }
        if (player.intersects(this)) {
            player.getScoreboard().decrementLivesRemaining();
            player.setxPos(4 * 120);
            player.setyPos(13 * 120);
        }
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
        xPos = -80;
        tick();
        return toDelete;
    }

    public boolean staysOnScreen() {
        xPos = (int) (50 * 20 * Math.random());
        return !toDelete;
    }
}

