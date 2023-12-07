package dhruvTests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.example.s1.R;
import com.example.s1.StartGame;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;

import android.widget.ImageView;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class espressoTest1 {
    public String easyLives;
    public String mediumLives;
    public String hardLives;

    @Rule
    public ActivityScenarioRule<StartGame> activityScenarioRule =
            new ActivityScenarioRule<>(StartGame.class);

    @Test
    public void aGetEasyLives() {
        onView(withId(R.id.startGameButton)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), equalTo("Easy"))).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.gameScreenButton)).perform(click());
        easyLives = onView(withId(R.id.livesDisplay)).check(matches(isDisplayed())).toString();
        Assert.assertNotEquals(easyLives, "0");
    }

    @Test
    public void bGetMediumLives() {
        onView(withId(R.id.startGameButton)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), equalTo("Medium"))).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.gameScreenButton)).perform(click());
        mediumLives = onView(withId(R.id.livesDisplay)).check(matches(isDisplayed())).toString();
        Assert.assertNotEquals(easyLives, mediumLives);
    }

    @Test
    public void cGetHardLives() {
        onView(withId(R.id.startGameButton)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), equalTo("Hard"))).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.gameScreenButton)).perform(click());
        hardLives = onView(withId(R.id.livesDisplay)).check(matches(isDisplayed())).toString();
        Assert.assertNotEquals(hardLives, mediumLives);
        Assert.assertNotEquals(hardLives, easyLives);
    }

    @Test
    public void difficultyHasBeenChosenForEasy() {
        //   TextView difficulty = findViewById(R.id.difficultyDisplay);
        onView(withId(R.id.startGameButton)).perform(click());
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), equalTo("Easy"))).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.gameScreenButton)).perform(click());
        String difficulty = onView(withId(R.id.difficultyDisplay)).toString();
        Assert.assertNotNull(difficulty);
        Assert.assertFalse(difficulty.isEmpty());
        Assert.assertFalse(difficulty.equals("Easy") ||
                difficulty.toString().equals("Medium") ||
                difficulty.toString().equals("Hard"));
    }


}
