package com.dcfportfolio.owlflashcardsen_cnlevel1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.Card;
import com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase.CardViewModel;

/**
 * Card Menu class
 * Handles card category button clicks and intents.
 */
public class CardMenu extends AppCompatActivity {
    private static final int catAnimals         = 1;
    private static final int catBodyParts       = 2;
    private static final int catFoods           = 3;
    private static final int catNumbers         = 4;
    private static final int catObjects         = 5;
    private static final int catPeople          = 6;
    private static final int catPlaces          = 7;
    private static final int catShapesColors    = 8;
    private static final int catTransportation  = 9;
    public static final String mCategory       = "com.dcfportfolio.owlflashcardsen_cnlevel1.CARD_CATEGORY";
    public static final String mTitle          = "com.dcfportfolio.owlflashcardsen_cnlevel1.CARD_TITLE";


    /**
     * Inits all Buttons
     * Generates button height using ViewTreeObserver
     * Sets all buttons OnClickListeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_menu);



        final Button mAnimalButton            = findViewById(R.id.cat1Button);
        final Button mBodyPartsButton         = findViewById(R.id.cat2Button);
        final Button mFoodButton              = findViewById(R.id.cat3Button);
        final Button mNumbersButton           = findViewById(R.id.cat4Button);
        final Button mObjectsButton           = findViewById(R.id.cat5Button);
        final Button mPeopleButton            = findViewById(R.id.cat6Button);
        final Button mPlacesButton            = findViewById(R.id.cat7Button);
        final Button mShapesColorsButton      = findViewById(R.id.cat8Button);
        final Button mTransportationButton    = findViewById(R.id.cat9Button);
        Button mProunciationGuideButton = findViewById(R.id.pronounceGuideButton);

        ViewTreeObserver buttonSizeObserver = mAnimalButton.getViewTreeObserver();
        buttonSizeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mAnimalButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = mAnimalButton.getMeasuredWidth();
                mAnimalButton.setHeight(width);
                mBodyPartsButton.setHeight(width);
                mFoodButton.setHeight(width);
                mNumbersButton.setHeight(width);
                mObjectsButton.setHeight(width);
                mPeopleButton.setHeight(width);
                mPlacesButton.setHeight(width);
                mShapesColorsButton.setHeight(width);
                mTransportationButton.setHeight(width);
            }
        });

        mAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animalCardsIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                animalCardsIntent.putExtra(mTitle, getString(R.string.animals_title));
                animalCardsIntent.putExtra(mCategory, catAnimals);
                startActivity(animalCardsIntent);
            }
        });

        mBodyPartsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bpIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                bpIntent.putExtra(mTitle, getString(R.string.body_parts_title));
                bpIntent.putExtra(mCategory, catBodyParts);
                startActivity(bpIntent);
            }
        });

        mFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                foodIntent.putExtra(mTitle, getString(R.string.food_title));
                foodIntent.putExtra(mCategory, catFoods);
                startActivity(foodIntent);
            }
        });

        mNumbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                numbersIntent.putExtra(mTitle, getString(R.string.numbers_title));
                numbersIntent.putExtra(mCategory, catNumbers);
                startActivity(numbersIntent);
            }
        });

        mObjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objectsIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                objectsIntent.putExtra(mTitle, getString(R.string.objects_title));
                objectsIntent.putExtra(mCategory, catObjects);
                startActivity(objectsIntent);
            }
        });

        mPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent peopleIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                peopleIntent.putExtra(mTitle, getString(R.string.people_title));
                peopleIntent.putExtra(mCategory, catPeople);
                startActivity(peopleIntent);
            }
        });

        mPlacesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent placesIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                placesIntent.putExtra(mTitle, getString(R.string.places_title));
                placesIntent.putExtra(mCategory, catPlaces);
                startActivity(placesIntent);
            }
        });

        mShapesColorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sbIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                sbIntent.putExtra(mTitle, getString(R.string.shapes_and_colors_title));
                sbIntent.putExtra(mCategory, catShapesColors);
                startActivity(sbIntent);
            }
        });

        mTransportationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transpIntent = new Intent(getApplicationContext(), CardPagerActivity.class);
                transpIntent.putExtra(mTitle, getString(R.string.transportation_title));
                transpIntent.putExtra(mCategory, catTransportation);
                startActivity(transpIntent);
            }
        });

        mProunciationGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pronounceIntent = new Intent(getApplicationContext(), PronunciationGuideActivity.class);
                startActivity(pronounceIntent);
            }
        });
    }

    /**
     * Inflates the custom menu items.
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
