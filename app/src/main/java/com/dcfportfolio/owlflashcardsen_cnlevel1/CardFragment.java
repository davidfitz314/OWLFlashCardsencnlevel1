package com.dcfportfolio.owlflashcardsen_cnlevel1;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.Card;

import static android.content.Context.AUDIO_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {
    private boolean isChinese = false;
    private static final String ARG_SECTION_ENGLISH_NAME = "english_name";
    private static final String ARG_SECTION_CHINESE_NAME = "chinese_name";
    private static final String ARG_SECTION_CHINESE_ENGLISH_NAME = "chinese_english_name";
    private static final String ARG_SECTION_SOUND_ENGLISH = "english_sound";
    private static final String ARG_SECTION_SOUND_CHINESE = "chinese_sound";
    private SharedPreferences mPreferences;

    //Media Section
    AudioManager audioManager;
    private float volume;
    private SoundPool soundPool;
    private int soundEnglish;
    private int soundChinese;

    //Debugging tools
    private static final String LOG_TAG = "CardFragment_DEBUG";
    private static boolean DEBUG_MODE = false;

    /**
     * Empty constructor for init the fragment
     */
    public CardFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new Instance of the Fragment and converts the param into Strings for bundle args.
     * @param card
     * @return
     */
    public static CardFragment newInstance(Card card){
        CardFragment cardFragment = new CardFragment();
        if (card != null) {
            Bundle args = new Bundle();
            args.putString(ARG_SECTION_ENGLISH_NAME, card.getEnglish());
            args.putString(ARG_SECTION_CHINESE_NAME, card.getChinese());
            args.putString(ARG_SECTION_CHINESE_ENGLISH_NAME, card.getChineseEnglish());
            args.putInt(ARG_SECTION_SOUND_ENGLISH, card.getSoundEn());
            args.putInt(ARG_SECTION_SOUND_CHINESE, card.getSoundCn());
            cardFragment.setArguments(args);
        }
        return cardFragment;
    }

    /**
     * Release soundpool resources
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        soundPool.release();
        soundPool = null;
    }

    /**
     * Creates the fragment view
     * handles flipping the card to English or Chinese version
     * handles button clicks for playing sound
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            String langChoice = mPreferences.getString(SettingsActivity.KEY_PREF_SET_CARD_LANGUAGE, getString(R.string.language_choice_default_value));
            if (langChoice.equals(getString(R.string.language_choice_default_value))) {
                isChinese = false;
            } else {
                isChinese = true;
            }
        } catch (Exception e){
            if (DEBUG_MODE) {
                Log.e(LOG_TAG, Log.getStackTraceString(e));
            }
        }

        setAudioControls();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder().setAudioAttributes(attr).setMaxStreams(2).build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        Bundle arguments = getArguments();
        int soundE = 0;
        int soundC = 0;
        soundE = arguments.getInt(ARG_SECTION_SOUND_ENGLISH, 0);
        soundC = arguments.getInt(ARG_SECTION_SOUND_CHINESE, 0);

        if (soundE != 0) {
            soundEnglish = soundPool.load(getContext(), soundE, 1);
        }
        if (soundC != 0) {
            soundChinese = soundPool.load(getContext(), soundC, 1);
        }

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        final ImageButton playSoundButton = rootView.findViewById(R.id.play_sound);
        final ImageView centerImage = rootView.findViewById(R.id.cardCenterImage);
        if (isChinese){
            centerImage.setImageResource(R.drawable.high_earth);
            if (getArguments().getInt(ARG_SECTION_SOUND_ENGLISH, 0) != 0){
                playSoundButton.setOnClickListener((View v)-> {
                        soundPool.play(soundChinese, volume,volume,0,0, 1f);
                });
            } else {
                playSoundButton.setVisibility(View.GONE);
            }
            isChinese=false;
            TextView nameView = rootView.findViewById(R.id.primary_textview);
            nameView.setText(getArguments().getString(ARG_SECTION_CHINESE_NAME));
            TextView secondaryView = rootView.findViewById(R.id.secondary_textview);
            secondaryView.setVisibility(View.VISIBLE);
            secondaryView.setText(getArguments().getString(ARG_SECTION_CHINESE_ENGLISH_NAME));
        } else {
            centerImage.setImageResource(R.drawable.high_little_owl);
            if (getArguments().getInt(ARG_SECTION_SOUND_ENGLISH, 0) != 0){
                playSoundButton.setOnClickListener((View v) ->{
                    soundPool.play(soundEnglish, volume,volume,0,0, 1f);
                });
            } else {
                playSoundButton.setVisibility(View.GONE);
            }

            isChinese=true;
            TextView nameView = rootView.findViewById(R.id.primary_textview);
            nameView.setText(getArguments().getString(ARG_SECTION_ENGLISH_NAME));
            TextView secondaryView = rootView.findViewById(R.id.secondary_textview);
            secondaryView.setVisibility(View.GONE);
        }


        final CardView flipCard = rootView.findViewById(R.id.cardViewFlippale);
        final LinearLayout bgSquare = rootView.findViewById(R.id.card_linear_layout);
        bgSquare.setBackgroundResource(R.drawable.square);

        flipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set up card flip animation
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(flipCard, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(flipCard, "scaleX", 0f, 1f);
                oa1.setDuration(150);
                oa2.setDuration(150);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());

                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        bgSquare.setBackgroundResource(R.drawable.square);

                        if (isChinese){
                            centerImage.setImageResource(R.drawable.high_earth);
                            if (getArguments().getInt(ARG_SECTION_SOUND_ENGLISH, 0) != 0){
                                playSoundButton.setOnClickListener((View v) -> {
                                        soundPool.play(soundChinese, volume,volume,0,0, 1f);
                                });
                            } else {
                                playSoundButton.setVisibility(View.GONE);
                            }

                            isChinese=false;
                            TextView nameView = rootView.findViewById(R.id.primary_textview);
                            nameView.setText(getArguments().getString(ARG_SECTION_CHINESE_NAME));
                            TextView secondaryView = rootView.findViewById(R.id.secondary_textview);
                            secondaryView.setVisibility(View.VISIBLE);
                            secondaryView.setText(getArguments().getString(ARG_SECTION_CHINESE_ENGLISH_NAME));
                        } else {
                            centerImage.setImageResource(R.drawable.high_little_owl);
                            if (getArguments().getInt(ARG_SECTION_SOUND_ENGLISH, 0) != 0){
                                playSoundButton.setOnClickListener((View v) -> {
                                        soundPool.play(soundEnglish, volume,volume,0,0, 1f);

                                });
                            } else {
                                playSoundButton.setVisibility(View.GONE);
                            }

                            isChinese=true;
                            TextView nameView = rootView.findViewById(R.id.primary_textview);
                            nameView.setText(getArguments().getString(ARG_SECTION_ENGLISH_NAME));
                            TextView secondaryView = rootView.findViewById(R.id.secondary_textview);
                            secondaryView.setVisibility(View.GONE);
                        }

                        oa2.start();
                    }
                });
                oa1.start();
            }
        });

        return rootView;
    }

    /**
     * Gets the AudioManager for controlling volume through phone buttons.
     */
    public void setAudioControls(){
        //audio manager for volume
        try {
            audioManager = (AudioManager) getContext().getSystemService(AUDIO_SERVICE);
            float actVolume, maxVolume;
            actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            volume = actVolume / maxVolume;
        } catch (Exception e){
            volume = 1;
            if (DEBUG_MODE) {
                Log.e(LOG_TAG, Log.getStackTraceString(e));
            }
        }

    }
}
