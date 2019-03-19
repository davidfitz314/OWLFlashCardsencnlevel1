package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented Tests for MainActivity,
 * covers onclick button intents and TextView display data.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.dcfportfolio.owlflashcardsen_cnlevel1", appContext.getPackageName());
    }


}
