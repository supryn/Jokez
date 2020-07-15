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

    @ColumnInfo(name = "joke_double_setup")
    public String setup;

    @ColumnInfo(name = "joke_double_delivery")
    public String delivery;

    @Ignore
    public Joke(String jokeType, String joke, int jokeId) {
        this.jokeType = jokeType;
        this.joke = joke;
        this.jokeId = jokeId;
    }

    @Ignore
    public Joke(String jokeType, String setup, String delivery, int jokeId) {
        this.jokeType = jokeType;
        this.setup = setup;
        this.delivery = delivery;
        this.jokeId = jokeId;

    }

    public Joke(String jokeType, String joke, String setup, String delivery, int jokeId) {
        this.jokeType = jokeType;
        this.joke = joke;
        this.setup = setup;
        this.delivery = delivery;
        this.jokeId = jokeId;
    }
}
