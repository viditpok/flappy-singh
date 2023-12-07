package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;

class River extends Drawable {
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public VehicleTypes getType() {
        return vehicletype;
    }

    private ArrayList<RiverObstacle> riverObstacles = new ArrayList<RiverObstacle>();
    private Paint paint = new Paint();

    private VehicleTypes vehicletype;

    public River(int yPos, int height, Player player, VehicleTypes vehicletype) {
        this.xPos = 0;
        this.yPos = yPos;
        this.player = player;
        this.width = 9 * 120;
        this.height = height;
        this.vehicletype = vehicletype;
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(3);
    }

    public void draw(Canvas c) {
        c.drawRect(xPos, yPos, xPos + width, yPos + height, paint);
        for (RiverObstacle ob : riverObstacles) {
            ob.draw(c);
        }
    }

    public void tick() {
        if (player.intersects(this)) {
            boolean onLog = false;
            for (RiverObstacle ob : riverObstacles) {
                if (ob.intersects(player)) {
                    onLog = true;
                    ob.movePlayerX();
                    break;
                }
            }
            if (!onLog) {
                player.getScoreboard().decrementLivesRemaining();
                player.setxPos(4 * 120);
                player.setyPos(13 * 120);
            }
        }
        for (RiverObstacle ob : riverObstacles) {
            ob.tick();
        }
        if (Math.random() < 0.01) {
            for (int i = 0; i < riverObstacles.size();) {
                if (riverObstacles.get(i).isToDelete()) {
                    riverObstacles.remove(riverObstacles.get(i));
                } else {
                    i++;
                }
            }
            riverObstacles.add(new RiverObstacle(yPos, player, vehicletype));
        }
    }
}