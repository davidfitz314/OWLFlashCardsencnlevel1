package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.Card;
import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.CardViewModel;

import java.util.List;

/**
 * Search Activity class
 * Allows searching the database by using query terms from English, Chinese, or pinyin.
 */
public class SearchActivity extends AppCompatActivity {
    private EditText mSearchText;
    private boolean mHasSearched = false;
    private ConstraintLayout mSearchResultsHolder;
    private CardViewModel mViewModel;
    private TextView mEnglishText;
    private TextView mChineseText;
    private TextView mChineseEnglishText;
    private String mLastSearchTerm = "";
    private static final String LAST_TERM_LABEL = "com.dcfportfolio.owlflashcardsen_cnlevel1.LAST_SEARCH_TERM_LABEL";
    private static final String HAS_SERACHED_LABEL = "com.dcfportfolio.owlflashcardsen_cnlevel1.BOOLEAN_HAS_SEARCHED";

    //Media Section
    boolean loaded = false;
    AudioManager audioManager;
    private float volume;
    protected SoundPool soundPool;
    protected int soundEnglish = 0;
    protected int soundChinese = 0;

    //Debugging tools
    private static final String LOG_TAG = "SearchActivity_DEBUG";
    private static boolean DEBUG_MODE = false;

    private List<Card> mAllCards;

    /**
     * Init all buttons and views
     * Displays search results after click handler has been pressed.
     * Uses LiveObservers for changing search terms in the view model
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchResultsHolder = findViewById(R.id.searchConstraintInnerLayout);
        mSearchText = findViewById(R.id.searchEditTextView);
        mViewModel = ViewModelProviders.of(this).get(CardViewModel.class);

        if (savedInstanceState != null){
            mLastSearchTerm = savedInstanceState.getString(LAST_TERM_LABEL, "");
            mHasSearched = savedInstanceState.getBoolean(HAS_SERACHED_LABEL, false);
        }

        if (mLastSearchTerm.length() > 0 && mHasSearched) {
            mViewModel.setSearchTerm(mLastSearchTerm);
            mSearchResultsHolder.setVisibility(View.VISIBLE);
        }

        final Observer<List<Card>> cardObserver = new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {

                ImageButton englishSound = findViewById(R.id.search_sound_english_button);
                ImageButton chineseSound = findViewById(R.id.search_sound_chinese_button);
                if (cards != null && cards.size() > 0){
                    mAllCards = cards;
                    mEnglishText.setText(mAllCards.get(0).getEnglish());
                    mEnglishText.setContentDescription(mAllCards.get(0).getEnglish());
                    mChineseText.setText(mAllCards.get(0).getChinese());

                    mChineseEnglishText.setText(mAllCards.get(0).getChineseEnglish());
                    mChineseEnglishText.setContentDescription(mAllCards.get(0).getChineseEnglish());
                    if (mAllCards.get(0).getSoundEn() != 0 && mAllCards.get(0).getSoundCn() != 0) {

                        int soundE = mAllCards.get(0).getSoundEn();
                        int soundC = mAllCards.get(0).getSoundCn();
                        try {
                            soundEnglish = soundPool.load(getApplicationContext(), soundE, 0);
                            soundChinese = soundPool.load(getApplicationContext(), soundC, 0);
                            englishSound.setEnabled(true);
                            chineseSound.setEnabled(true);
                            /*englishSound.setTextColor(getResources().getColor(R.color.enabled_color));
                            chineseSound.setTextColor(getResources().getColor(R.color.enabled_color));*/

                        } catch (Exception e){
                            if (DEBUG_MODE) {
                                Log.e(LOG_TAG, Log.getStackTraceString(e));
                            }
                        }

                    } else {
                        englishSound.setEnabled(false);
                        chineseSound.setEnabled(false);
                        /*englishSound.setTextColor(getResources().getColor(R.color.disabled_color));
                        chineseSound.setTextColor(getResources().getColor(R.color.disabled_color));*/
                    }
                } else {
                    mEnglishText.setText(getString(R.string.search_no_results_default_text));
                    mEnglishText.setContentDescription(getString(R.string.search_no_results_default_text));
                    mChineseText.setText(getString(R.string.search_no_results_default_text));
                    mChineseText.setContentDescription(getString(R.string.search_no_results_default_text));
                    mChineseEnglishText.setText(getString(R.string.search_no_results_default_text));
                    mChineseEnglishText.setContentDescription(getString(R.string.search_no_results_default_text));
                    englishSound.setEnabled(false);
                    chineseSound.setEnabled(false);
                    /*englishSound.setTextColor(getResources().getColor(R.color.disabled_color));
                    chineseSound.setTextColor(getResources().getColor(R.color.disabled_color));*/
                }
            }
        };

        mViewModel.mSearchedCard.observe(this, cardObserver);

        mEnglishText = findViewById(R.id.searchEnglishTextView);
        mChineseText = findViewById(R.id.searchChineseTextView);
        mChineseEnglishText = findViewById(R.id.searchChineseEnglishTextView);

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //audio manager for volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float actVolume, maxVolume;
        try {
            actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            volume = actVolume / maxVolume;
        } catch (Exception e){
            if (DEBUG_MODE) {
                Log.e(LOG_TAG, Log.getStackTraceString(e));
            }
            volume = 1;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder().setAudioAttributes(attr).setMaxStreams(2).build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        if (!mHasSearched){
            mSearchResultsHolder.setVisibility(View.GONE);
        } else {
            mSearchResultsHolder.setVisibility(View.VISIBLE);
        }

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * Edits the search term to have no leading or trailing whitespaces and to be lowercase then inserts it to the view model
     * @param view
     */
    public void commitSearch(View view) {
        if (!mHasSearched) {
            mHasSearched = true;
            mSearchResultsHolder.setVisibility(View.VISIBLE);
        }

        String search_text = mSearchText.getText().toString().toLowerCase();

        mLastSearchTerm = search_text.trim();
        mViewModel.setSearchTerm(mLastSearchTerm);


        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mSearchResultsHolder.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        } catch (Exception e){
            if (DEBUG_MODE) {
                Log.e(LOG_TAG, Log.getStackTraceString(e));
            }
        }

    }

    /**
     * plays the English sound if loaded
     * @param view
     */
    public void playEnglishSound(View view) {
        if (loaded && soundEnglish != 0){
            soundPool.play(soundEnglish, volume,volume,1,0, 1f);
        }
    }

    /**
     * Plays the Chinese sound if loaded
     * @param view
     */
    public void playChineseSound(View view) {
        if (loaded && soundChinese != 0){
            soundPool.play(soundChinese, volume,volume,1,0, 1f);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.close_search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Closes Search Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.close_search_activity_button:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mHasSearched) {
            outState.putString(LAST_TERM_LABEL, mLastSearchTerm);
            outState.putBoolean(HAS_SERACHED_LABEL, mHasSearched);
        }
        super.onSaveInstanceState(outState);
    }
}
