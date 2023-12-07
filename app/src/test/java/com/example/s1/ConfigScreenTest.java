package com.example.s1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class ConfigScreenTest extends TestCase {
    private ConfigScreen myActivity;
    private Looper mLooper;

    @Before
    public void setUp() throws Exception {
        mLooper = mock(Looper.class);
        Context ctx = mock(Context.class);
        when(ctx.getMainLooper()).thenReturn(mLooper);
        myActivity = new ConfigScreen();
    }

    @Test
    public void testCheckValidDifficulty() {
        Assert.assertEquals(true, myActivity.checkValidDifficulty("Easy"));
    }

    @Test
    public void testNotEmptyName() {
        Assert.assertEquals(false, myActivity.checkNotEmptyName(""));
    }

    @Test
    public void testNameExists() {
        Assert.assertEquals(false, myActivity.checkNameExists(null));
    }

    @Test
    public void testValidSprite() {
        Assert.assertEquals(true, myActivity.checkSprite("https://"));
    }
}