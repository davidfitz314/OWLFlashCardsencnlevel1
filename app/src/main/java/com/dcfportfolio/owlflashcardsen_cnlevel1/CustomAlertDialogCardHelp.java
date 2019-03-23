package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import java.util.Set;

/**
 * Custom Alert Dialog Card Help class
 * @extends Dialog
 * @implements View.OnClickListener
 * Creates a dialog for displaying card help
 */
public class CustomAlertDialogCardHelp extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public ImageButton yes;
    public CheckBox repeatHelp;
    private SharedPreferences mPreferences;
    private ConstraintLayout mConstraintView;


    public CustomAlertDialogCardHelp(Activity a){
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_card_help_screen);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        repeatHelp = (CheckBox) findViewById(R.id.show_alert_checkbox);
        boolean helpOn = mPreferences.getBoolean(SettingsActivity.KEY_PREF_CARD_HELP_NOTIFICATION, false);
        repeatHelp.setChecked(!helpOn);
        repeatHelp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = mPreferences.edit();
                boolean helpChecked = !isChecked;
                editor.putBoolean(SettingsActivity.KEY_PREF_CARD_HELP_NOTIFICATION, helpChecked);
                editor.apply();
                editor.clear();
            }
        });

        yes = (ImageButton) findViewById(R.id.close_alert_button);
        yes.setOnClickListener(this);

        mConstraintView = findViewById(R.id.alertHelpConstraintView);
        mConstraintView.setBackgroundResource(android.R.color.transparent);
        mConstraintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_alert_button:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
