package com.supryn.android.joke.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "joke_table")
public class Joke {

    @PrimaryKey
    @ColumnInfo(name = "joke_id")
    public int jokeId;

    @ColumnInfo(name = "joke_type")
    public String jokeType;

    @ColumnInfo(name = "joke_single")
    public String joke;

    @ColumnInfo(name = "joke_double_delivery")
    public String delivery;

    @ColumnInfo(name = "is_favorite")
    public boolean isFavorite;

    @Ignore
    public Joke(String jokeType, String joke, int jokeId) {
        this.jokeType = jokeType;
        this.joke = joke;
        this.jokeId = jokeId;
    }


    public Joke(String jokeType, String joke, String delivery, int jokeId, boolean isFavorite) {
        this.jokeType = jokeType;
        this.joke = joke;
        this.delivery = delivery;
        this.jokeId = jokeId;
        this.isFavorite = isFavorite;
    }


}
