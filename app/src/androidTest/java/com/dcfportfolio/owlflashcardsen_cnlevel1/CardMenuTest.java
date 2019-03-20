package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Tests for card menu verifying each item can be opened and display the next activity.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class CardMenuTest {
    @Rule
    public ActivityTestRule<CardMenu> mActivityTestRule = new ActivityTestRule<>(CardMenu.class);

    @Test
    public void openPronounceActivity(){
        onView(withId(R.id.pronounceGuideButton)).perform(click());
        onView(withId(R.id.prounceHeader)).check(matches(isDisplayed()));
    }

    @Test
    public void openAnimalsCard(){
        onView(withId(R.id.cat1Button)).perform(click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu2(){
        onView(withId(R.id.cat2Button)).perform(click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu3(){
        onView(withId(R.id.cat3Button)).perform(click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu4(){
        onView(withId(R.id.cat4Button)).perform(click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu5(){
        onView(withId(R.id.cat5Button)).perform(scrollTo(), click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu6(){
        scrollTo();
        onView(withId(R.id.cat6Button)).perform(scrollTo(), click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu7(){
        scrollTo();
        onView(withId(R.id.cat7Button)).perform(scrollTo(), click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu8(){
        scrollTo();
        onView(withId(R.id.cat8Button)).perform(scrollTo(), click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());
    }

    @Test
    public void openCardMenu9(){
        scrollTo();
        onView(withId(R.id.cat9Button)).perform(scrollTo(), click());
        onView(withId(R.id.cardView2)).check(matches(isDisplayed()));
        onView(withId(R.id.cardView2)).perform(click());

    }
}
