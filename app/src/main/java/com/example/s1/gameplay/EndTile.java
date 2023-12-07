package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class EndTile extends Drawable {
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

    private Player player;
    private Paint paint = new Paint();
    EndTile(int xPos, int yPos, Player player) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.player = player;
        this.width = 120;
        this.height = 120;
        paint.setColor(Color.MAGENTA);
        paint.setStrokeWidth(3);
    }

    public void draw(Canvas c) {
        c.drawRect(xPos, yPos, xPos + width, yPos + height, paint);
    }

    @Override
    public void tick() {
        if (this.intersects(player)) {
            player.getScoreboard().setWin(true);
        }
    }
}