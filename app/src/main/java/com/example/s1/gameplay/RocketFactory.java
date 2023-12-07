package com.example.s1.gameplay;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.HashSet;


public class RocketFactory extends Drawable {
    private ArrayList<Rocket> rockets = new ArrayList<>();
    private HashSet<Integer> rocketSet = new HashSet<>();
    private Player player;

    public RocketFactory(Player player) {
        this.player = player;
    }
    void draw(Canvas c) {
        for (Rocket rocket : rockets) {
            rocket.draw(c);
        }
    }

    void tick() {
        if (Math.random() < 0.01) {
            int rocketPos = (int) (Math.random() * 13) * 120;
            if (!rocketSet.contains(rocketPos)) {
                rockets.add(new Rocket(rocketPos, player));
                rocketSet.add(rocketPos);
            }
        }
        for (Rocket rocket : rockets) {
            rocket.tick();
        }
        for (int i = 0; i < rockets.size();) {
            if (rockets.get(i).isToDelete()) {
                rocketSet.remove(rockets.get(i).getyPos());
                rockets.remove(rockets.get(i));
            } else {
                i++;
            }
        }
    }
}
