package com.example.s1.gameplay;

import junit.framework.TestCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Looper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RiverObstacleTest extends TestCase {
    private RiverObstacle myActivity;
    private Player player;
    private Looper mLooper;

    @Before
    public void setUp() throws Exception {
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        player = new Player(0,0);
        myActivity = new RiverObstacle(500, player, VehicleTypes.LOG1);
    }

    // For sprint 4:
    @Test
    public void checkHeight() {
        Assert.assertEquals(120, myActivity.getHeight());
    }
    @Test
    public void checkWidth() {
        Assert.assertEquals(120, myActivity.getWidth());
    }
    @Test
    public void checkSpeeds() {
        if (myActivity.getType() == VehicleTypes.LOG1) {
            Assert.assertEquals(3, myActivity.getSpeed());
        } else if (myActivity.getType() == VehicleTypes.LOG2) {
            Assert.assertEquals(5, myActivity.getSpeed());
        }
    }
    @Test
    public void testLogMovement() {
        myActivity.tick();
        Assert.assertNotEquals(-100, myActivity.getXPos());
    }
    @Test
    public void checkLogMovesOffscreen() {
        Assert.assertEquals(myActivity.movesOffScreen(), true);
    }
    @Test
    public void checkLogStaysOnscreen() {
        Assert.assertEquals(myActivity.staysOnScreen(), true);
    }
    @Test
    public void testPlayerOnLogMoved() {
        int ogPos = player.getxPos();
        myActivity.movePlayerX();
        Assert.assertNotEquals(ogPos, player.getxPos());
    }

}