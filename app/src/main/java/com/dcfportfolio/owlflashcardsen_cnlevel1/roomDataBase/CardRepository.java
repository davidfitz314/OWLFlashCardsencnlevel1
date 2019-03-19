package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

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

    public LiveData<List<Card>> getAllCards() {
        return this.mAllCards;
    }

    public LiveData<Card[]> getInitCard(){
        return mInitCard;
    }

    public LiveData<List<Card>> getSomeCards(int cat_in) {
        mSomeCards = mDao.getCardsbyCategory(cat_in);
        return mSomeCards;
    }

    public LiveData<List<Card>> findCard(String query_term){
        mFoundCard = mDao.findCard(query_term);
        return mFoundCard;
    }
}
