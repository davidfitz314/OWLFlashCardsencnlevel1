package com.dcfportfolio.owlflashcardsen_cnlevel1;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppFunctionalityWithDefaultPrefrencesOnRecordedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class){
        @Override
        protected void beforeActivityLaunched() {
            clearSharedPrefs(InstrumentationRegistry.getTargetContext());
            super.beforeActivityLaunched();
        }
    };

    /**
     * Clears everything in the SharedPreferences
     */
    private void clearSharedPrefs(Context context) {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    @Test
    public void appFunctionalityWithDefaultPrefrencesOnTest() {

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.custom),
                                childAtPosition(
                                        withId(R.id.customPanel),
                                        0)),
                        0),
                        isDisplayed()));
        editText.perform(replaceText("Dav"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.title_enter_button), withText("Begin"), withContentDescription("Begin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Categories"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Categories")));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.pronounceGuideButton), withText("Pronunciation Guide"), withContentDescription("Pronunciation Guide"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.sound_macron), withContentDescription("Play sample sound."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.sound_accent), withContentDescription("Play sample sound."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatImageButton2.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.sound_caron), withContentDescription("Play sample sound."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        8),
                                1)));
        appCompatImageButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.sound_grave), withContentDescription("Play sample sound."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        10),
                                1)));
        appCompatImageButton4.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.pronounceGuideButton), withText("Pronunciation Guide"), withContentDescription("Pronunciation Guide"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.prounceHeader), withText("Quick Guide"), withContentDescription("Quick Guide"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Quick Guide")));

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.pronounceGuideButton), withText("Pronunciation Guide"), withContentDescription("Pronunciation Guide"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.searchBar), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Search")));

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.close_search_activity_button), withContentDescription("Close"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.prounceHeader), withText("Quick Guide"), withContentDescription("Quick Guide"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Quick Guide")));

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withText("Settings"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        //textView5.check(matches(withText("Settings")));

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withText("Categories"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Categories")));

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.cat1Button), withText("Animals"), withContentDescription("Animals"),
                        childAtPosition(
                                allOf(withId(R.id.cardMenuGridLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.show_alert_checkbox), withText("Dont Show Again"),
                        childAtPosition(
                                allOf(withId(R.id.alertHelpConstraintView),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.show_alert_checkbox), withText("Dont Show Again"),
                        childAtPosition(
                                allOf(withId(R.id.alertHelpConstraintView),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction constraintLayout = onView(
                allOf(withId(R.id.alertHelpConstraintView),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        constraintLayout.perform(click());

        ViewInteraction appCompatImageButton8 = onView(
                allOf(withId(R.id.play_sound), withContentDescription("Play Sound"),
                        childAtPosition(
                                allOf(withId(R.id.card_linear_layout),
                                        childAtPosition(
                                                withId(R.id.cardViewFlippale),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatImageButton8.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.cardCenterImage), withContentDescription("front and reverse card center"),
                        childAtPosition(
                                allOf(withId(R.id.card_linear_layout),
                                        childAtPosition(
                                                withId(R.id.cardViewFlippale),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction cardView = onView(
                allOf(withId(R.id.cardViewFlippale),
                        childAtPosition(
                                withParent(withId(R.id.pager)),
                                0),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction appCompatImageButton9 = onView(
                allOf(withId(R.id.play_sound), withContentDescription("Play Sound"),
                        childAtPosition(
                                allOf(withId(R.id.card_linear_layout),
                                        childAtPosition(
                                                withId(R.id.cardViewFlippale),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatImageButton9.perform(click());

        ViewInteraction actionMenuItemView5 = onView(
                allOf(withId(R.id.searchBar), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView5.perform(click());

        ViewInteraction actionMenuItemView6 = onView(
                allOf(withId(R.id.close_search_activity_button), withContentDescription("Close"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView6.perform(click());

        ViewInteraction cardView2 = onView(
                allOf(withId(R.id.cardViewFlippale),
                        childAtPosition(
                                withParent(withId(R.id.pager)),
                                0),
                        isDisplayed()));
        cardView2.perform(click());

        ViewInteraction appCompatImageButton10 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton10.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.cat2Button), withText("BodyParts"), withContentDescription("BodyParts"),
                        childAtPosition(
                                allOf(withId(R.id.cardMenuGridLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.show_alert_checkbox), withText("Dont Show Again"),
                        childAtPosition(
                                allOf(withId(R.id.alertHelpConstraintView),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                6),
                        isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction constraintLayout2 = onView(
                allOf(withId(R.id.alertHelpConstraintView),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        constraintLayout2.perform(click());

        ViewInteraction appCompatImageButton11 = onView(
                allOf(withId(R.id.play_sound), withContentDescription("Play Sound"),
                        childAtPosition(
                                allOf(withId(R.id.card_linear_layout),
                                        childAtPosition(
                                                withId(R.id.cardViewFlippale),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatImageButton11.perform(click());

        ViewInteraction cardView3 = onView(
                allOf(withId(R.id.cardViewFlippale),
                        childAtPosition(
                                withParent(withId(R.id.pager)),
                                0),
                        isDisplayed()));
        cardView3.perform(click());

        ViewInteraction appCompatImageButton12 = onView(
                allOf(withId(R.id.play_sound), withContentDescription("Play Sound"),
                        childAtPosition(
                                allOf(withId(R.id.card_linear_layout),
                                        childAtPosition(
                                                withId(R.id.cardViewFlippale),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatImageButton12.perform(click());

        ViewInteraction appCompatImageButton13 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton13.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.cat9Button), withText("Transportation"), withContentDescription("Transportation"),
                        childAtPosition(
                                allOf(withId(R.id.cardMenuGridLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                8)));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton14 = onView(
                allOf(withId(R.id.play_sound), withContentDescription("Play Sound"),
                        childAtPosition(
                                allOf(withId(R.id.card_linear_layout),
                                        childAtPosition(
                                                withId(R.id.cardViewFlippale),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatImageButton14.perform(click());

        ViewInteraction cardView4 = onView(
                allOf(withId(R.id.cardViewFlippale),
                        childAtPosition(
                                withParent(withId(R.id.pager)),
                                0),
                        isDisplayed()));
        cardView4.perform(click());

        ViewInteraction cardView5 = onView(
                allOf(withId(R.id.cardViewFlippale),
                        childAtPosition(
                                withParent(withId(R.id.pager)),
                                0),
                        isDisplayed()));
        cardView5.perform(click());

        ViewInteraction appCompatImageButton15 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton15.perform(click());

        ViewInteraction actionMenuItemView7 = onView(
                allOf(withId(R.id.searchBar), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView7.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.searchEditTextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.searchEditTextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("cat"), closeSoftKeyboard());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.commit_search_button), withText("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.searchEnglishTextView), withText("Cat"), withContentDescription("Cat"),
                        childAtPosition(
                                allOf(withId(R.id.searchConstraintInnerLayout),
                                        childAtPosition(
                                                withId(R.id.scrollView2),
                                                0)),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("Cat")));

        ViewInteraction appCompatImageButton16 = onView(
                allOf(withId(R.id.search_sound_english_button), withContentDescription("search screen play sound"),
                        childAtPosition(
                                allOf(withId(R.id.searchConstraintInnerLayout),
                                        childAtPosition(
                                                withId(R.id.scrollView2),
                                                0)),
                                4)));
        appCompatImageButton16.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton17 = onView(
                allOf(withId(R.id.search_sound_chinese_button), withContentDescription("search screen play sound"),
                        childAtPosition(
                                allOf(withId(R.id.searchConstraintInnerLayout),
                                        childAtPosition(
                                                withId(R.id.scrollView2),
                                                0)),
                                5)));
        appCompatImageButton17.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView8 = onView(
                allOf(withId(R.id.close_search_activity_button), withContentDescription("Close"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView8.perform(click());

        ViewInteraction actionMenuItemView9 = onView(
                allOf(withId(R.id.settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView9.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(android.R.id.edit), withText("Dav"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText3.perform(scrollTo(), replaceText("Dave"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(android.R.id.edit), withText("Dave"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction textView8 = onView(
                allOf(withId(android.R.id.summary), withText("Dave"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Dave")));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView3.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView4.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView5.perform(actionOnItemAtPosition(4, click()));

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatImageButton18 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton18.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.cat4Button), withText("Numbers"), withContentDescription("Numbers"),
                        childAtPosition(
                                allOf(withId(R.id.cardMenuGridLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                3)));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton19 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton19.perform(click());

        ViewInteraction actionMenuItemView10 = onView(
                allOf(withId(R.id.settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView10.perform(click());

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView6.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView7.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction appCompatImageButton21 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton21.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.cat7Button), withText("Places"), withContentDescription("Places"),
                        childAtPosition(
                                allOf(withId(R.id.cardMenuGridLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                6)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView11 = onView(
                allOf(withId(R.id.settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView11.perform(click());

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)));
        recyclerView8.perform(actionOnItemAtPosition(4, click()));

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatImageButton22 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton22.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.cat4Button), withText("Numbers"), withContentDescription("Numbers"),
                        childAtPosition(
                                allOf(withId(R.id.cardMenuGridLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                3)));
        appCompatButton14.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
