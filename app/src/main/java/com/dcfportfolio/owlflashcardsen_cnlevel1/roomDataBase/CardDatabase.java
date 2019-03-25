package com.dcfportfolio.owlflashcardsen_cnlevel1.roomDataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dcfportfolio.owlflashcardsen_cnlevel1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * CardDatabase class
 * Intializes the database as a singleton
 */
@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class CardDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
    private static CardDatabase INSTANCE;
    private static Context mContext;
    private static final String LOG_TAG = "DB_DEBUG";
    private static boolean DEBUG_MODE = false;

    /**
     * getDatabase grabs the database instance.
     * if instance is null, creates a new instance.
     * @param context
     * @return
     */
    public static CardDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (CardDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CardDatabase.class, context.getString(R.string.flashcarddatabasename))
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    mContext = context.getApplicationContext();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final CardDao mDao;

        PopulateDbAsync(CardDatabase db){
            mDao = db.cardDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //reset database to blank slate
            //mDao.deleteAll();
            Context localContext = mContext.getApplicationContext();
            if (mDao.getAnyCard().length < 1){
                loadJSONData(localContext);
            }
            return null;
        }

        private void loadJSONData(Context context){
            Context localContext = context.getApplicationContext();
            String json = loadJSONString();
            try {
                if (json!= null) {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jArray = jsonObject.getJSONArray("cards");
                    for (int i = 0; i < jArray.length(); ++i) {
                        JSONObject object = jArray.getJSONObject(i);
                        String english = object.getString("English");// english
                        String chinese = object.getString("Chinese"); // chinese
                        String pinyin = object.getString("Pinyin"); // pinyin
                        String soundEnName = object.getString("SoundEn"); //sound english
                        String soundCnName = object.getString("SoundCn"); //sound chinese
                        int category = object.getInt("Category"); // category

                        //get Resource Ids
                        int enResourceId = localContext.getResources().getIdentifier(soundEnName, "raw", localContext.getPackageName());
                        int cnResourceId = localContext.getResources().getIdentifier(soundCnName, "raw", localContext.getPackageName());

                        Card card = new Card(english, chinese, pinyin, enResourceId, cnResourceId, category);
                        mDao.insertCard(card);
                    }
                }
            } catch (JSONException e) {
                if (DEBUG_MODE) {
                    Log.e(LOG_TAG, Log.getStackTraceString(e));
                }
            }

        }

        private String loadJSONString(){
            String json = null;
            try {
                InputStream is = mContext.getResources().openRawResource(R.raw.card_json_data);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, StandardCharsets.UTF_8);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return json;
        }
    }
}
