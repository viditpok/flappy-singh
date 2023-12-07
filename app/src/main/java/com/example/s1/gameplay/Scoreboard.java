package com.example.s1.gameplay;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;

public class Scoreboard extends Drawable {
    public int getScore() {
        return score;
    }

    private int score;

    public MutableLiveData<Boolean> getWin() {
        return win;
    }

    public void setWin(Boolean bool) {
        if (bool) {
            score += 100;
        }
        this.win.postValue(bool);
    }

    private MutableLiveData<Boolean> win = new MutableLiveData<Boolean>(false);

    public MutableLiveData<Integer> getLivesRemaining() {
        return livesRemaining;
    }

    public void setLivesRemaining(Integer livesRemaining) {
        this.livesRemaining = new MutableLiveData<Integer>(livesRemaining);
        this.livesRemainingValue = livesRemaining;
    }

    public void decrementLivesRemaining() {
        this.livesRemaining.postValue(livesRemainingValue - 1);
        livesRemainingValue--;
        this.score = 0;
    }

    private MutableLiveData<Integer> livesRemaining;
    private int livesRemainingValue;
    
    private Player player;

    private int startingY;

    private Paint paint = new Paint();

    private ArrayList<Drawable> arr;

    public Scoreboard(Player player, int startingY, int xPos, int yPos, ArrayList<Drawable> arr) {
        this.player = player;
        this.startingY = startingY;
        this.xPos = xPos;
        this.yPos = yPos;
        paint.setTextSize(48f);
        player.attachScoreboard(this);
        this.arr = arr;
    }

    @Override
    void draw(Canvas c) {
        c.drawText("Score: " + score, xPos, yPos, paint);
        c.drawText("Lives: " + livesRemaining.getValue(), xPos, yPos + 50, paint);
    }

    public void updateOnPlayerMove() {
        int s = 0;
        for (Drawable item : arr) {
            System.out.println(item);
            if (item.yPos > player.yPos) {
                if (item instanceof Road) {
                    if (((Road) item).getType() == VehicleTypes.BUS) {
                        s += 5;
                    } else if (((Road) item).getType() == VehicleTypes.CAR) {
                        s += 10;
                    } else if (((Road) item).getType() == VehicleTypes.MOTORCYCLE) {
                        s += 15;
                    }
                } else if (item instanceof River) {
                    s += 20;
                }
            }
        }
        score = Math.max(s, score);
    }

    //Test helper methods
    public int crossBus() {
        score = 0;
        arr = new ArrayList<>();
        Player player = new Player(0, 0);
        arr.add(new Road(100, player, VehicleTypes.BUS));
        updateOnPlayerMove();
        return score;
    }
    public int crossCar() {
        score = 0;
        arr = new ArrayList<>();
        Player player = new Player(0, 0);
        arr.add(new Road(100, player, VehicleTypes.CAR));
        updateOnPlayerMove();
        return score;
    }
    public int crossMotorcycle() {
        score = 0;
        arr = new ArrayList<>();
        Player player = new Player(0, 0);
        arr.add(new Road(100, player, VehicleTypes.MOTORCYCLE));
        updateOnPlayerMove();
        return score;
    }
    public int crossRiver() {
        score = 0;
        arr = new ArrayList<>();
        Player player = new Player(0, 0);
        arr.add(new River(100, 1, player, VehicleTypes.LOG1));
        updateOnPlayerMove();
        return score;
    }
    public int crossNothing() {
        score = 0;
        arr = new ArrayList<>();
        Player player = new Player(0, 0);
        arr.add(new Road(0, player, VehicleTypes.BUS));
        updateOnPlayerMove();
        return score;
    }
    public int checkDecrementLivesRemaining(int startingLives) {
        setLivesRemaining(startingLives);
        decrementLivesRemaining();
        return livesRemainingValue;
    }
    public int checkPlayerHit(int startingLives) {
        setLivesRemaining(startingLives);
        Car car = new Car(0, player, VehicleTypes.CAR);
        car.tick();
        return livesRemainingValue;
    }
    public int checkPlayerWater(int startingLives) {
        setLivesRemaining(startingLives);
        River river = new River(0, 1, player, VehicleTypes.LOG1);
        river.tick();
        return livesRemainingValue;
    }
    public int checkPlayerMovesOffscreen(int startingLives) {
        setLivesRemaining(startingLives);
        player.moveX(20 * 120);
        return livesRemainingValue;
    }
    public int checkWin(int score) {
        this.score = score;
        setWin(true);
        return this.score;
    }
}
