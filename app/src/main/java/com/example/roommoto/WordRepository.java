package com.example.roommoto;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordRepository {
    //Add Member variales for the Dao and the list of words.
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;
    //create a constructore that gets a handle to the database and initializes the member variables.
    WordRepository(Application application){
        WordRoomDatabase db=WordRoomDatabase.getDatabase(application);
        mWordDao=db.wordDao();
        mAllWords=mWordDao.getAllWord();
    }
    //add a wrapper for the getAllWords();
    LiveData<List<Word>>getAllWords(){
        return getAllWords();
    }
    //add a wrapper for the insert();
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}
