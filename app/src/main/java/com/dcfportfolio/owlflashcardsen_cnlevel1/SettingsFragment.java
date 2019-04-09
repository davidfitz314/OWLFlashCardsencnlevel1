package com.dcfportfolio.owlflashcardsen_cnlevel1;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    private SharedPreferences mPreferences;

    /**
     * Implements listeners for changes in the preferences.
     * @param bundle
     * @param s
     */
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.preferences, s);

        /*
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String userNameStringPref = mPreferences.getString(SettingsActivity.KEY_PREF_EDIT_USER_NAME, getString(R.string.default_edit_text_name_string));
        final Preference userNamePref = this.findPreference(SettingsActivity.KEY_PREF_EDIT_USER_NAME);
        userNamePref.setSummary(userNameStringPref);
        userNamePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                userNamePref.setSummary(o.toString());
                return true;
            }
        });*/

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final ListPreference cardLangPref = (ListPreference) this.findPreference(SettingsActivity.KEY_PREF_SET_CARD_LANGUAGE);
        String langChoice = mPreferences.getString(SettingsActivity.KEY_PREF_SET_CARD_LANGUAGE, getString(R.string.language_choice_default_value));
        cardLangPref.setSummary(langChoice);
        cardLangPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                cardLangPref.setSummary(o.toString());
                return true;
            }
        });
    }

}
