package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

class Road extends Drawable {
    public Road.RoadTile[] getRoadTiles() {
        return roadTiles;
    }

    private VehicleTypes vehicletype;
    public void setRoadTiles(Road.RoadTile[] roadTiles) {
        this.roadTiles = roadTiles;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    private Road.RoadTile[] roadTiles = new Road.RoadTile[9];
    private Player player;
    private ArrayList<Car> cars = new ArrayList<Car>();
    public VehicleTypes getType() {
        return vehicletype;
    }

    private double[] randomnessThresholds = {0.004, 0.01, 0.03};
    public Road(int yPos, Player player, VehicleTypes vehicletype) {
        this.player = player;
        this.yPos = yPos;
        this.vehicletype = vehicletype;
        for (int i = 0; i < roadTiles.length; i++) {
            roadTiles[i] = new Road.RoadTile(i * 120, yPos);
        }
    }

    public void draw(Canvas c) {
        for (int i = 0; i < roadTiles.length; i++) {
            roadTiles[i].draw(c);
        }
        for (Car car : cars) {
            car.draw(c);
        }
    }

    public void tick() {
        for (Car car : cars) {
            car.tick();
        }
        if (Math.random() < randomnessThresholds[vehicletype.ordinal()]) {
            for (int i = 0; i < cars.size();) {
                if (cars.get(i).isToDelete()) {
                    cars.remove(cars.get(i));
                } else {
                    i++;
                }
            }
            cars.add(new Car(yPos, player, vehicletype));
        }
    }

    class RoadTile {
        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getxPos() {
            return xPos;
        }

        public void setxPos(int xPos) {
            this.xPos = xPos;
        }

        public int getyPos() {
            return yPos;
        }

        public void setyPos(int yPos) {
            this.yPos = yPos;
        }

        public Paint getPaint() {
            return paint;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }
        private int width = 120;
        private int height = 120;
        private int xPos;
        private int yPos;
        private Paint paint = new Paint();

        RoadTile(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(3);
        }

        public void draw(Canvas c) {
            c.drawRect(xPos, yPos, xPos + width, yPos + height, paint);
        }
    }
}
