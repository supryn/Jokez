package com.supryn.android.joke.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.supryn.android.joke.model.Joke;

/**
 * Local Room Database.
 *
 */
@Database(entities = {Joke.class}, version = 3, exportSchema = false)
public abstract class JokeDatabase extends RoomDatabase {

    private static JokeDatabase sInstance;
    private static final String LOG_TAG = Joke.class.getSimpleName();

    private static final String DB_NAME = "Joke_DB";


    public static JokeDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Retrieving Database instance.");
        if (sInstance == null) {
            synchronized (JokeDatabase.class) {
                sInstance = Room.databaseBuilder(
                        context, JokeDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                Log.d(LOG_TAG, "Database instance created.");
            }
        }

        return sInstance;
    }

    public abstract JokeDao jokeDao();
}
