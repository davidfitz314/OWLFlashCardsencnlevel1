package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * ViewModel Class
 * Connects to the repository and handles maintaining LiveData for Activities
 */
public class CardViewModel extends AndroidViewModel {
    private CardRepository mRepository;
    private LiveData<List<Card>> mAllCards;
    private MutableLiveData<Integer> mCategory = new MutableLiveData<>();

    /**
     * Inserts the category into the LiveData
     * @return LiveData<List<Card>>
     */
    public LiveData<List<Card>> mSomeCards =
            Transformations.switchMap(mCategory, category -> {
                return mRepository.getSomeCards(category);
            });

    private LiveData<Card[]> mInitCard;

    private MutableLiveData<String> mSearchTerm = new MutableLiveData<>();

    /**
     * Inserts the query_term into the LiveData
     * @return LiveData<List<Card>>
     */
    public LiveData<List<Card>> mSearchedCard =
            Transformations.switchMap(mSearchTerm, searchTerm -> {
                return mRepository.findCard(searchTerm);
            });

    /**
     * Sets the MutableLiveData mCategory
     * @param category
     */
    public void setCategory(int category){
        mCategory.setValue(category);
    }

    /**
     * Sets the MutableLiveData mSearchTerm
     * @param term
     */
    public void setSearchTerm(String term){
        mSearchTerm.setValue(term);
    }

    /**
     * Initializes the Repository
     * and gets LiveData for member Variables
     * @param application
     */
    public CardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CardRepository(application);
        mAllCards = mRepository.getAllCards();
        mInitCard = mRepository.getInitCard();
    }

    /**
     * Gets all Cards in database wrapped with LiveData
     * @return
     */
    public LiveData<List<Card>> getAllCards(){
        return this.mAllCards;
    }

    /**
     * Gets a card from the database wrapped in LiveData
     * @return
     */
    public LiveData<Card[]> getInitCard(){
        return mInitCard;
    }

}
