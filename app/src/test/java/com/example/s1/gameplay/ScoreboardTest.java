package com.example.s1.gameplay;

import junit.framework.TestCase;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.example.s1.gameplay.Player;
import com.example.s1.gameplay.Road;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class ScoreboardTest extends TestCase {
    private Scoreboard myActivity;
    private Car car;
    private Looper mLooper;

    @Before
    public void setUp() throws Exception {
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        Player player = new Player(0,0);
        myActivity = new Scoreboard(player, 0, 0, 0, null);
        car = new Car(0,player, VehicleTypes.CAR);
        player.attachScoreboard(myActivity);
    }
    @Test
    public void testCrossBus() {
        Assert.assertEquals(myActivity.crossBus(), 5);
    }
    @Test
    public void testCrossCar() {
        Assert.assertEquals(myActivity.crossCar(), 10);
    }
    @Test
    public void testCrossMotorcycle() {
        Assert.assertEquals(myActivity.crossMotorcycle(), 15);
    }
    @Test
    public void testCrossRiver() {
        Assert.assertEquals(myActivity.crossRiver(), 20);
    }
    @Test
    public void testCrossNothing() {
        Assert.assertEquals(myActivity.crossNothing(), 0);
    }


    // For sprint 4
    @Test
    public void testDecrementLivesRemaining() {
        Assert.assertEquals(myActivity.checkDecrementLivesRemaining(10), 9);
    }
    @Test
    public void testSetLivesRemaining() {
        myActivity.setLivesRemaining(9);
        int lives = myActivity.getLivesRemaining().getValue();
        Assert.assertEquals(lives, 9);
    }
    @Test
    public void testPlayerHitsCar() {
        Assert.assertEquals(myActivity.checkPlayerHit(10), 9);
    }

    // SPRINT 5
    @Test
    public void testSetWin() {
        myActivity.setWin(false);
        boolean won = myActivity.getWin().getValue();
        Assert.assertEquals(false, won);
    }
    @Test
    public void testPlayerTouchesWater() {
        Assert.assertEquals(myActivity.checkPlayerWater(10), 9);
    }
    @Test
    public void testPlayerMovesOffscreen() {
        Assert.assertEquals(myActivity.checkPlayerMovesOffscreen(10), 9);
    }
    @Test
    public void testWinPoints() {
        int startScore = 100;
        Assert.assertEquals(startScore + 100, myActivity.checkWin(100));
    }

}