package com.supryn.android.joke.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.supryn.android.joke.model.Joke;

import java.util.List;

/**
 * Dao exposing CRUD database operations.
 *
 */
@Dao
public interface JokeDao {

    @Query("SELECT * FROM joke_table")
    LiveData<List<Joke>> getJokes();

    @Query("SELECT COUNT(joke_id) FROM joke_table")
    int getJokeCount();


    /**
     * Insert jokes into the database.
     *
     * @param jokes
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertJokes(List<Joke> jokes);


    /**
     * Gets a single joke by its' movie id.
     *
     * @param jokeId
     * @return LiveData<Joke>
     */
    @Query("SELECT * FROM joke_table WHERE joke_id = :jokeId")
    LiveData<Joke> getJokeById(int jokeId);

}
