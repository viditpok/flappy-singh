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
public class RoadTest extends TestCase {
    private Car myActivity;
    private Looper mLooper;

    @Before
    public void setUp() throws Exception {
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        Player player = new Player(0,0);
        myActivity = new Car(500, player, VehicleTypes.BUS);
    }

    @Test
    public void testMovement() {
        myActivity.tick();
        Assert.assertNotEquals(-100, myActivity.getXPos());
    }
    @Test
    public void checkHeight() {
        Assert.assertEquals(120, myActivity.getHeight());
    }
    @Test
    public void checkWidth() {
        Assert.assertEquals(200, myActivity.getWidth());
    }
    @Test
    public void checkExists() {
        Assert.assertNotNull(myActivity);
    }
    @Test
    public void checkSpeed() {
        Assert.assertEquals(myActivity.getSpeed(), 3);
    }
    @Test
    public void checkMovesOffscreen() {
        Assert.assertEquals(myActivity.movesOffScreen(), true);
    }
    @Test
    public void checkStaysOnscreen() {
        Assert.assertEquals(myActivity.staysOnScreen(), true);
    }
}