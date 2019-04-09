package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.Card;
import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.CardViewModel;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private TextView mTitleView;

    //Debugging tools
    private static final String LOG_TAG = "MainActivity_DEBUG";
    private static boolean DEBUG_MODE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //send call to database by initializing the view model here
        CardViewModel initDatabase = ViewModelProviders.of(this).get(CardViewModel.class);
        initDatabase.getInitCard().observe(this, new Observer<Card[]>() {
            @Override
            public void onChanged(@Nullable Card[] cards) {
                //Does nothing, used to make sure database is initialized before other activities use it.
            }
        });

        //setting up a view tree observer for grabbing a textviews width and setting the buttons width below it to match
        mTitleView = findViewById(R.id.mainScreenTextViewTitle);
        final Button mEnterButton = findViewById(R.id.title_enter_button);

        try {
            ViewTreeObserver buttonWidth = mTitleView.getViewTreeObserver();
            buttonWidth.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mTitleView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int width = mTitleView.getMeasuredWidth();
                    mEnterButton.setWidth(width);
                }
            });
        } catch (Exception e) {
            if (DEBUG_MODE) {
                Log.e(LOG_TAG, Log.getStackTraceString(e));
            }
        }

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        /*boolean getDisplay = mPreferences.getBoolean(SettingsActivity.KEY_PREF_ASK_FOR_USER_NAME, true);

        if (getDisplay) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText edittext = new EditText(this);
            alert.setMessage(R.string.set_display_name_alert_message);
            alert.setTitle(R.string.welcome_to_owl_flash_cards_alert_title);
            edittext.setText(mPreferences.getString(SettingsActivity.KEY_PREF_EDIT_USER_NAME, ""));
            alert.setView(edittext);

            alert.setPositiveButton(R.string.alert_dialog_ok_button, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    //save the new preferences data
                    String YouEditTextValue = edittext.getText().toString();
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString(SettingsActivity.KEY_PREF_EDIT_USER_NAME, YouEditTextValue);
                    editor.putBoolean(SettingsActivity.KEY_PREF_ASK_FOR_USER_NAME, false);
                    editor.apply();
                    editor.clear();

                    //display a greeting for the user using a toast
                    String greeting = getString(R.string.welcome_string_greeting);
                    LayoutInflater layoutInflater = getLayoutInflater();
                    View layout = layoutInflater.inflate(R.layout.welcome_custom_toast_message, (ViewGroup) findViewById(R.id.custom_toast_welcome_message));
                    TextView text = (TextView) layout.findViewById(R.id.welcome_view_message);
                    //String name = mPreferences.getString(SettingsActivity.KEY_PREF_EDIT_USER_NAME, "");
                    String combineGreetingName = greeting;// + " " + name;
                    text.setText(combineGreetingName);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
            });

            alert.show();
        } else {
            String greeting = getString(R.string.welcome_back_string_greeting);
            LayoutInflater layoutInflater = getLayoutInflater();
            View layout = layoutInflater.inflate(R.layout.welcome_custom_toast_message, (ViewGroup) findViewById(R.id.custom_toast_welcome_message));
            TextView text = (TextView) layout.findViewById(R.id.welcome_view_message);
            String name = mPreferences.getString(SettingsActivity.KEY_PREF_EDIT_USER_NAME, "");
            String combineGreetingName = greeting + " " + name;
            text.setText(combineGreetingName);
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
*/
        String greeting = getString(R.string.welcome_string_greeting);
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.welcome_custom_toast_message, (ViewGroup) findViewById(R.id.custom_toast_welcome_message));
        TextView text = (TextView) layout.findViewById(R.id.welcome_view_message);
        String combineGreetingName = greeting +" ";
        text.setText(combineGreetingName);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

        android.support.v7.preference.PreferenceManager
                .setDefaultValues(this, R.xml.preferences, false);

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    public void goToMenuPage(View view) {
        Intent cardCatIntent = new Intent(this, CardMenu.class);
        startActivity(cardCatIntent);
    }

}
