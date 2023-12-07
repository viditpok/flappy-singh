package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class TileBackground extends Drawable {
    private Paint paint = new Paint();

    public TileBackground() {
        this.width = 9 * 120;
        this.height = 14 * 120;
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
    }

    public void draw(Canvas c) {
        c.drawRect(0, 0, width, height, paint);
    }
}