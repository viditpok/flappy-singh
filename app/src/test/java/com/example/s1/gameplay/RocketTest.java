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
public class RocketTest extends TestCase {

    private Rocket myActivity;
    private Looper mLooper;

    @Before
    public void setUp() throws Exception {
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        Player player = new Player(0,0);
        myActivity = new Rocket(500, player);
    }

    // SPRINT 5
    @Test
    public void checkExists() {
        Assert.assertNotNull(myActivity);
    }
    @Test
    public void checkDimensions() {
        Assert.assertEquals(120, myActivity.getHeight());
        Assert.assertEquals(120, myActivity.getWidth());
    }
    @Test
    public void checkSpeed() {
        Assert.assertEquals(myActivity.getSpeed(), -30);
    }
    @Test
    public void testTick() {
        int ogXPos = myActivity.getXPos();
        myActivity.tick();
        Assert.assertNotEquals(ogXPos, myActivity.getXPos());
    }
    @Test
    public void testMovesOffscreen() {
        Assert.assertEquals(myActivity.movesOffScreen(), true);
    }
    @Test
    public void testStaysOnscreen() {
        Assert.assertEquals(myActivity.staysOnScreen(), true);
    }
}