package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Pronunciation Guide Activity class
 * Inflates Textviews with helpful descriptions for pronouncing Chinese and loads Sound examples.
 */
public class PronunciationGuideActivity extends AppCompatActivity {
    //Media Section
    AudioManager audioManager;
    private float volume;
    private SoundPool soundPool;
    private int mSoundMa1;
    private int mSoundMa2;
    private int mSoundMa3;
    private int mSoundMa4;

    /**
     * Inits the textviews, loads the sounds, and sets onclick listeners for playing the sounds.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronunciation_guide);

        //audio manager for volume
        audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        float actVolume, maxVolume;
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(4)
                    .build();
        } else {
            soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        }

        mSoundMa1 = soundPool.load(this, R.raw.ma_straight, 0);
        mSoundMa2 = soundPool.load(this, R.raw.ma_up, 0);
        mSoundMa3 = soundPool.load(this, R.raw.ma_caron, 0);
        mSoundMa4 = soundPool.load(this, R.raw.ma_down, 0);

        ImageButton maSound1 = findViewById(R.id.sound_macron);
        maSound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(mSoundMa1, volume,volume,0,0, 1f);
            }
        });

        ImageButton maSound2 = findViewById(R.id.sound_accent);
        maSound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(mSoundMa2, volume,volume,0,0, 1f);
            }
        });

        ImageButton maSound3 = findViewById(R.id.sound_caron);
        maSound3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(mSoundMa3, volume,volume,0,0, 1f);
            }
        });

        ImageButton maSound4 = findViewById(R.id.sound_grave);
        maSound4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(mSoundMa4, volume,volume,0,0, 1f);
            }
        });
    }

    /**
     * Releases sound pool resources
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Starts Activities
     * Search Activity
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
