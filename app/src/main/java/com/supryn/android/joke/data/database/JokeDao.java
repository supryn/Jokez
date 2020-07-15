package com.supryn.android.joke.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.supryn.android.joke.model.Joke;

/**
 * Dao exposing CRUD database operations.
 *
 */
public interface JokeDao {


    /**
     *
     *
     */
    public LiveData<Joke> getJoke() {

    }


    /**
     * Insert a joke into the database.
     *
     * @param joke
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertJoke(Joke joke);


}
