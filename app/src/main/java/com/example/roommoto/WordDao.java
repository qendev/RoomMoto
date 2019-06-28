package com.example.roommoto;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

//Daos have to be either interfaces or abstract classes because we dont provide method body.
//We just create a method and annotate it and room does the rest.

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>>getAllWord();


}
