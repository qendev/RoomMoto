package com.example.roommoto;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class},version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    //Make the DB(WordRoomDatabase) a singleto to prevent multiple instances of the database from being opened at the same time.
    private static volatile WordRoomDatabase INSTANCE;
        static  WordRoomDatabase getDatabase(final  Context context){
            if(INSTANCE==null){
                synchronized (WordRoomDatabase.class){
                    if(INSTANCE==null){
                        //Create the DB here.
                        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                                WordRoomDatabase.class,"word database")
                                .addCallback(sRoomDatabaseCallback)
                                .build();


                    }
                }
            }
            return INSTANCE;
        }
        //Create callback for the purpose of deleteing all content and populating the database whenever the app is started.
        private static RoomDatabase.Callback sRoomDatabaseCallback =
                new RoomDatabase.Callback(){

                    @Override
                    public void onOpen (@NonNull SupportSQLiteDatabase db){
                        super.onOpen(db);
                        new PopulateDbAsync(INSTANCE).execute();

                    }


                };

            private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

                private final WordDao mDao;

                PopulateDbAsync(WordRoomDatabase db) {
                    mDao = db.wordDao();
                }

                @Override
                protected Void doInBackground(final Void... params) {
                    mDao.deleteAll();
                    Word word = new Word("Hello");
                    mDao.insert(word);
                    word = new Word("World");
                    mDao.insert(word);
                    return null;


                }
            }

}
