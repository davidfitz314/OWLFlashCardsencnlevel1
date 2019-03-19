package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.List;

public class CardViewModel extends AndroidViewModel {
    private CardRepository mRepository;
    private LiveData<List<Card>> mAllCards;
    private MutableLiveData<Integer> mCategory = new MutableLiveData<>();
    public LiveData<List<Card>> mSomeCards =
            Transformations.switchMap(mCategory, category -> {
                return mRepository.getSomeCards(category);
            });

    private LiveData<Card[]> mInitCard;

    private MutableLiveData<String> mSearchTerm = new MutableLiveData<>();
    public LiveData<List<Card>> mSearchedCard =
            Transformations.switchMap(mSearchTerm, searchTerm -> {
                return mRepository.findCard(searchTerm);
            });

    public void setCategory(int category){
        mCategory.setValue(category);
    }

    public void setSearchTerm(String term){
        mSearchTerm.setValue(term);
    }

    public CardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CardRepository(application);
        mAllCards = mRepository.getAllCards();
        mInitCard = mRepository.getInitCard();
    }

    public LiveData<List<Card>> getAllCards(){
        return this.mAllCards;
    }

    public LiveData<Card[]> getInitCard(){
        return mInitCard;
    }

}
