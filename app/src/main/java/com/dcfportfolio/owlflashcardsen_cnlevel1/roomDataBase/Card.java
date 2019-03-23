package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Card class
 * Allows creation of card objects.
 * @Entities int id, String mEnglish, String mChinese, String mChineseEnglish, int mSoundEn, int mSoundCn, int mCategory
 */
@Entity(tableName = "card_table")
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @NonNull
    @ColumnInfo(name = "card_english")
    private String mEnglish;

    @NonNull
    @ColumnInfo(name = "card_chinese")
    private String mChinese;

    @NonNull
    @ColumnInfo(name = "card_chinese_english")
    private String mChineseEnglish;

    @ColumnInfo(name = "english_sound")
    private int mSoundEn;

    @ColumnInfo(name = "chinese_sound")
    private int mSoundCn;

    @NonNull
    @ColumnInfo(name = "card_category")
    private int mCategory;

    @Ignore
    Card(@NonNull String english_in, @NonNull String chinese_in, @NonNull String chinese_english_in, int sound_english_in, int sound_chinese_in, @NonNull int category_in){
        this.mEnglish = english_in;
        this.mChinese = chinese_in;
        this.mChineseEnglish = chinese_english_in;
        this.mSoundEn = sound_english_in;
        this.mSoundCn = sound_chinese_in;
        this.mCategory = category_in;
    }

    @Ignore
    Card(@NonNull String english_in, @NonNull String chinese_in, @NonNull String chinese_english_in, @NonNull int category_in){
        this.mEnglish = english_in;
        this.mChinese = chinese_in;
        this.mChineseEnglish = chinese_english_in;
        this.mSoundEn = 0;
        this.mSoundCn = 0;
        this.mCategory = category_in;
    }

    Card(){
        this.mEnglish = "";
        this.mChinese = "";
        this.mChineseEnglish = "";
        this.mSoundEn = 0;
        this.mSoundCn = 0;
        this.mCategory = 0;
    }

    public int getId() {
        return mId;
    }

    @NonNull
    public String getEnglish() {
        return mEnglish;
    }

    @NonNull
    public String getChinese() {
        return mChinese;
    }

    @NonNull
    public String getChineseEnglish() {
        return mChineseEnglish;
    }

    public int getSoundEn() {
        return mSoundEn;
    }

    public int getSoundCn() {
        return mSoundCn;
    }

    public int getCategory() {
        return mCategory;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setEnglish(@NonNull String mEnglish) {
        this.mEnglish = mEnglish;
    }

    public void setChinese(@NonNull String mChinese) {
        this.mChinese = mChinese;
    }

    public void setChineseEnglish(@NonNull String mChineseEnglish) {
        this.mChineseEnglish = mChineseEnglish;
    }

    public void setSoundEn(int mSoundEn) {
        this.mSoundEn = mSoundEn;
    }

    public void setSoundCn(int mSoundCn) {
        this.mSoundCn = mSoundCn;
    }

    public void setCategory(int mCategory) {
        this.mCategory = mCategory;
    }
}
