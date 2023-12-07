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
public class PlayerTest extends TestCase {

    private Player myActivity;
    private Looper mLooper;

    @Before
    public void setUp() throws Exception {
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        myActivity = new Player(500, 500);
    }

    //For sprint 4:
    @Test
    public void testMoveRight() {
        myActivity.right();
        Assert.assertEquals(myActivity.getxPos(), 500 + 120);
    }
    @Test
    public void testMoveDown() {
        myActivity.down();
        Assert.assertEquals(myActivity.getyPos(), 500 + 120);
    }

    // SPRINT 5
    @Test
    public void testMoveOffscreenRight() {
        Assert.assertEquals(myActivity.moveOffscreen(10, 8*120), 4*120);
    }
    @Test
    public void testMoveOffscreenLeft() {
        Assert.assertEquals(myActivity.moveOffscreen(0, -120), 4 * 120);
    }

}