package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Inits and inflates the support fragment for displaying preferences
 */
public class SettingsActivity extends AppCompatActivity {
    //public static final String KEY_PREF_EDIT_USER_NAME = "edit_text_name_preference";
    //public static final String KEY_PREF_ASK_FOR_USER_NAME = "check_box_preference_username_set";
    public static final String KEY_PREF_CARD_HELP_NOTIFICATION = "switch_show_card_help_preference";
    public static final String KEY_PREF_SET_CARD_LANGUAGE = "list_preference_card_language_choice";

    /**
     * Begins the support fragment transaction
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
