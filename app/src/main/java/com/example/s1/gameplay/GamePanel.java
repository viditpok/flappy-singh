package com.example.s1.gameplay;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private ArrayList<Drawable> items = new ArrayList<Drawable>();
    private GameThread thread;

    private Player player;

    public ArrayList<Drawable> getItems() {
        return items;
    }

    public void setItems(ArrayList<Drawable> items) {
        this.items = items;
    }

    public GameThread getThread() {
        return thread;
    }

    public void setThread(GameThread thread) {
        this.thread = thread;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GamePanel(Context context) {
        super(context);
        player = new Player(4 * 120, 13 * 120);
        getHolder().addCallback(this);

        items.add(
                new TileBackground()
        );
        items.add(
                new River(600, 120,  player, VehicleTypes.LOG1)
        );
        items.add(
                new River(720, 120,  player, VehicleTypes.LOG2)
        );
        items.add(
                new Road(1080, player, VehicleTypes.BUS)
        );
        items.add(
                new Road(1320, player, VehicleTypes.CAR)
        );
        items.add(
                new Road(360, player, VehicleTypes.MOTORCYCLE)
        );
        items.add(
                new EndTile(3 * 240, 0, player)
        );
        items.add(
                player
        );
        items.add(
                new RocketFactory(player)
        );
        items.add(
                new Scoreboard(player, 13 * 120, 100, 100, items)
        );

        thread = new GameThread(getHolder(), this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (Drawable item : items) {
            item.tick();
        }
        for (Drawable item : items) {
            item.draw(canvas);
        }
    }
}
