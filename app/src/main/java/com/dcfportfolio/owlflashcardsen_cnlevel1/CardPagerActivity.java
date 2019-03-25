package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.Card;
import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.CardViewModel;

import java.util.List;

/**
 * CardPagerActivity class
 * Binds fragments to pages and loads as needed.
 */
public class CardPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CardViewModel mViewModel;
    private CardPagerActivity.InnerPageAdapter mAdapter;
    private SharedPreferences mPreferences;

    //Debugging tools
    private static final String LOG_TAG = "CardPagerActivity_DEBUG";
    private static boolean DEBUG_MODE = false;

    /**
     * Connects to the view model for loading the needed cards
     * displays card help screen fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_pager);


        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean helpOn = mPreferences.getBoolean(SettingsActivity.KEY_PREF_CARD_HELP_NOTIFICATION, true);
        if (helpOn){
            CustomAlertDialogCardHelp helpDialog = new CustomAlertDialogCardHelp(this);
            try {
                helpDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            } catch (NullPointerException e){
                if (DEBUG_MODE) {
                    Log.e(LOG_TAG, Log.getStackTraceString(e));
                }
            }
            helpDialog.show();
        }


        mAdapter = new InnerPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);

        mViewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        //Default category in event of intent error
        int currentCategory = 1;
        String currentTitle = getString(R.string.cards_default_fragment_title);
        if (getIntent().getIntExtra(CardMenu.mCategory, 1) != 0){
            currentCategory = getIntent().getIntExtra(CardMenu.mCategory, 0);
            currentTitle = getIntent().getStringExtra(CardMenu.mTitle);
        }
        mViewModel.setCategory(currentCategory);


        final Observer<List<Card>> cardObserver = new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {
                mAdapter.setCardList(cards);
            }
        };

        if (currentCategory > 0 && currentCategory <= 9) {
            mViewModel.mSomeCards.observe(this, cardObserver);
        }

        //Default Title
        setTitle(currentTitle);

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    /**
     * Inner Page Adapter static class for updating card fragments and placing them into pages
     */
    public static class InnerPageAdapter extends FragmentStatePagerAdapter {
        private List<Card> mCurrentCards;

        public InnerPageAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setCardList(List<Card> cards_in){
            mCurrentCards = cards_in;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int i) {
            if (mCurrentCards != null)
                return CardFragment.newInstance(mCurrentCards.get(i));
            return null;
        }

        @Override
        public int getCount() {
            if (mCurrentCards != null)
                return mCurrentCards.size();
            return 0;
        }

    }

    /**
     * Inflates the custom menu items
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Launches new activities
     * Searchbar activity
     * Settings Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.searchBar:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
