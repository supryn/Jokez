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


    @Query("SELECT COUNT(joke_id) FROM joke_table")
    int getJokeCount();


    @Query("SELECT * FROM joke_table WHERE is_favorite = 1")
    LiveData<List<Joke>> getFavoriteJokes();

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


    /**
     * Updates the favorite status of a joke.
     *
     * @param jokeId
     * @param isFavorite
     */
    @Query("UPDATE joke_table SET is_favorite = :isFavorite WHERE joke_id = :jokeId")
    void updateFavoriteJoke(int jokeId, boolean isFavorite);
}
