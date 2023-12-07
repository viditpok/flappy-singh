package com.example.s1.gameplay;

import android.graphics.Canvas;

abstract class Drawable {
    abstract void draw(Canvas c);
    void tick() { };

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

    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;

    public boolean intersects(Drawable other) {
        int x1 = xPos;
        int x2 = xPos + width;
        int x3 = other.xPos;
        int x4 = other.xPos + other.width;

        int y1 = yPos;
        int y2 = yPos + height;
        int y3 = other.yPos;
        int y4 = other.yPos + other.height;

        boolean xIntersects = (x1 >= x3 && x1 <= x4) || (x2 >= x3 && x2 <= x4);
        boolean yIntersects = (y1 > y3 && y1 < y4) || (y2 > y3 && y2 < y4)
                              || (y1 == y3 || y2 == y4);


        return xIntersects && yIntersects;
    }
}
