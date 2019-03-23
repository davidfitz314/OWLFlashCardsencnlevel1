package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * CardDao class
 * Inserts and retrieves data into the database
 */
@Dao
public interface CardDao {
    //Get all Cards
    @Query("SELECT * from card_table")
    LiveData<List<Card>> getAllCards();

    //get 1 random card
    @Query("SELECT * from card_table LIMIT 1")
    Card[] getAnyCard();

    @Query("SELECT * FROM card_table LIMIT 1")
    LiveData<Card[]> initCard();

    @Query("SELECT * FROM card_table WHERE card_category LIKE :cat_in ORDER BY Random()")
    LiveData<List<Card>> getCardsbyCategory(int cat_in);

    @Query("SELECT * FROM card_table WHERE card_english LIKE '%' || :search_term || '%' OR card_chinese LIKE '%' || :search_term || '%' OR card_chinese_english LIKE '%' || :search_term || '%'")
    LiveData<List<Card>> findCard(String search_term);

    //insert a card
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertCard(Card card);

    //delete all cards
    @Query("DELETE from card_table")
    void deleteAll();

}
