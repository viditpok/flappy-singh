package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Player extends Drawable {
    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    private Paint paint = new Paint();


    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    private Scoreboard scoreboard;

    public Player(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 120;
        this.height = 120;
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
    }

    public void attachScoreboard(Scoreboard s) {
        scoreboard = s;
    }
    public void draw(Canvas c) {
        c.drawRect(xPos, yPos, xPos + width, yPos + height, paint);
    }

    public void tick() {

    }

    public void up() {
        // check if this is possible first
        if (!canGoUp(yPos)) {
            // make not allowed animation
            assert true;
        } else {
            yPos -= 120;
        }
        scoreboard.updateOnPlayerMove();
    }

    public void down() {
        if (!canGoDown(yPos)) {
            // make not allowed animation
            assert true;
        } else {
            yPos += 120;
        }
    }

    public void left() {
        if (!canGoLeft(xPos)) {
            // make not allowed animation
            assert true;
        } else {
            xPos -= 120;
        }
    }

    public void right() {
        if (!canGoRight(xPos)) {
            // make not allowed animation
            assert true;
        } else {
            xPos += 120;
        }
    }

    public void moveX(int distance) {
        if (distance > 0) {
            if (xPos + distance > 8 * 120) {
                if (this.getScoreboard() != null) {
                    this.getScoreboard().decrementLivesRemaining();
                }
                this.setxPos(4 * 120);
                this.setyPos(13 * 120);
            } else {
                xPos += distance;
            }
        }
        if (distance < 0) {
            if (xPos + distance < 0) {
                if (this.getScoreboard() != null) {
                    this.getScoreboard().decrementLivesRemaining();
                }
                this.setxPos(4 * 120);
                this.setyPos(13 * 120);
            } else {
                xPos += distance;
            }
        }
    }

    public boolean checkValidPosition(int xPos, int yPos) {
        return xPos <= 8 * 120
                && xPos >= 0
                && yPos <= 13 * 120
                && yPos >= 0;
    }
    public boolean canGoUp(int yPos) {
        return yPos - 120 >= 0;
    }
    public boolean canGoDown(int yPos) {
        return yPos + 120 <= 13 * 120;
    }
    public boolean canGoLeft(int xPos) {
        return xPos - 120 >= 0;
    }
    public boolean canGoRight(int xPos) {
        return xPos + 120 <= 8 * 120;
    }
    public int moveOffscreen(int start, int dist) {
        xPos = start;
        moveX(dist);
        return xPos;
    }
}