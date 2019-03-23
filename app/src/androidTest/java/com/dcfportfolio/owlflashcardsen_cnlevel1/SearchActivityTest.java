package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.app.Activity;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@LargeTest
@RunWith(JUnit4.class)
public class SearchActivityTest {
    private static final String STRING_TO_BE_TYPED = "Sky";
    private static final String RESPONSE_STRING = "Skyscraper";
    private static final String String_TO_BE_TYPED_TWO = "";

    @Rule
    public ActivityTestRule<SearchActivity> mActivityTestRule = new ActivityTestRule<>(SearchActivity.class);

    @Test
    public void searchBarTest(){
        onView(withId(R.id.searchEditTextView)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.commit_search_button)).perform(click());
        onView(withId(R.id.searchEnglishTextView)).check(matches(withText(RESPONSE_STRING)));
    }

    @Test
    public void searchBarTestChinese(){
        onView(withId(R.id.searchEditTextView)).perform(typeText(String_TO_BE_TYPED_TWO), closeSoftKeyboard());
        onView(withId(R.id.commit_search_button)).perform(click());
        onView(withId(R.id.searchEnglishTextView)).check(matches(withText("Dog")));
    }
}
