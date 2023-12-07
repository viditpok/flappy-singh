package com.example.s1;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Looper;
import org.robolectric.RobolectricTestRunner;

import java.util.HashMap;


@RunWith(RobolectricTestRunner.class)
public class GameScreenTest extends TestCase {
    private GameScreen myActivity;
    private Looper mLooper;

    @Before
    public void setUp() throws Exception {
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        myActivity = new GameScreen();
    }

    @Test
    public void testCheckDifficultyLives() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Easy", 3);
        map.put("Medium", 2);
        map.put("Hard", 1);
        Assert.assertEquals(true, myActivity.checkDifficultyLives(map));
    }
    @Test
    public void testCheckEasyLives() {
        Assert.assertEquals(true, myActivity.checkEasyLives(4));
    }
    @Test
    public void testCheckMediumLives() {
        Assert.assertEquals(true, myActivity.checkMediumLives(3));
    }
    @Test
    public void testCheckHardLives() {
        Assert.assertEquals(true, myActivity.checkHardLives(2));
    }
    @Test
    public void testCheckScore() {
        Assert.assertEquals(true, myActivity.checkScore(0));
    }
}