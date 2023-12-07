package com.example.s1;

import junit.framework.TestCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;

import com.example.s1.gameplay.Player;

import junit.framework.TestCase;

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
        myActivity = new Player(0,0);
    }

    @Test
    public void testCheckValidPosition() {
        Assert.assertEquals(true, myActivity.checkValidPosition(0,0));
        Assert.assertEquals(false, myActivity.checkValidPosition(120 * 8 + 1,0));
        Assert.assertEquals(false, myActivity.checkValidPosition(0,120 * 13 + 1));
    }

    @Test
    public void testCanGoUp() {
        Assert.assertEquals(true, myActivity.canGoUp(120*4));
    }
    @Test
    public void testCanGoDown() {
        Assert.assertEquals(true, myActivity.canGoDown(0));
    }
    @Test
    public void testCanGoLeft() {
        Assert.assertEquals(true, myActivity.canGoLeft(120*4));
    }
    @Test
    public void testCanGoRight() {
        Assert.assertEquals(true, myActivity.canGoRight(0));
    }

}