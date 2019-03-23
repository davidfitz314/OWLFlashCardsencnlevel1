package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Card Repository Class
 * connects to the Dao class to update, insert or retrieve data from the database.
 */
public class CardRepository {
    private CardDao mDao;
    private LiveData<List<Card>> mAllCards;
    private LiveData<List<Card>> mSomeCards;
    private LiveData<List<Card>> mFoundCard;
    private LiveData<Card[]> mInitCard;


    CardRepository(Application application) {
        CardDatabase db = CardDatabase.getDatabase(application);
        mDao = db.cardDao();
        mAllCards = mDao.getAllCards();
        mInitCard = mDao.initCard();
    }

    /**
     * Gets all cards from the Database
     * @return LiveData<List<Card>>
     */
    public LiveData<List<Card>> getAllCards() {
        return this.mAllCards;
    }

    /**
     * Gets a single card to init the database
     * @return LiveData<Card[]>
     */
    public LiveData<Card[]> getInitCard(){
        return mInitCard;
    }

    /**
     * Gets specific group of cards based on the insert param
     * @param cat_in
     * @return LiveData<List<Card>>
     */
    public LiveData<List<Card>> getSomeCards(int cat_in) {
        mSomeCards = mDao.getCardsbyCategory(cat_in);
        return mSomeCards;
    }

    /**
     * Finds the most relevant cards based on the query_term
     * @param query_term
     * @return LiveData<List<Card>>
     */
    public LiveData<List<Card>> findCard(String query_term){
        mFoundCard = mDao.findCard(query_term);
        return mFoundCard;
    }
}
