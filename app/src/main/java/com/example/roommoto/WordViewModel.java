package com.example.roommoto;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WordViewModel extends AndroidViewModel {
    //add a memeber variable to hold reference to the repository
    private WordRepository mRepository;
    private LiveData<List<Word>>mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository=new WordRepository(application);
        mAllWords=mRepository.getAllWords();
    }
    LiveData<List<Word>>getmAllWords(){
        return mAllWords;
    }
    //create a wrapper for insert() that calls Repository's insert()method.
    public void insert(Word word){
        mRepository.insert(word);
    }
}