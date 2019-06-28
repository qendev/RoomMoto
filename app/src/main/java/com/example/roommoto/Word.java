package com.example.roommoto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate =true)
    private int id;

    @ColumnInfo(name = "word")

    @NonNull
    private String mWord;

    public Word(String word){
        this.mWord=word;
    }
    public String getWord(){
        return this.mWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
