package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private GamePanel gamePanel;
    private SurfaceHolder surfaceHolder;
    private boolean running;

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public boolean isRunning() {
        return running;
    }

    GameThread(SurfaceHolder holder, GamePanel gamePanel) {
        super();
        this.gamePanel = gamePanel;
        this.surfaceHolder = holder;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        while (running) {
            Canvas c = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                gamePanel.draw(c);
            }
            surfaceHolder.unlockCanvasAndPost(c);
            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(0);
    }
}