package com.supryn.android.joke.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.supryn.android.joke.data.database.JokeDao;
import com.supryn.android.joke.data.network.JokesDataSource;
import com.supryn.android.joke.model.Joke;

import java.util.List;

public final class JokeRepository {

    private static final String LOG_TAG = JokeRepository.class.getSimpleName();
    private static final String LOG_JOKES_INSERTED = "Jokes inserted into DB.";

    private static JokeRepository sInstance;
    private static JokesDataSource mDataSource;
    private static JokeDao mDao;
    private static AppExecutors mExecutors;


    private JokeRepository(JokesDataSource dataSource, JokeDao dao, AppExecutors executors) {
        mDataSource = dataSource;
        mDao = dao;
        mExecutors = executors;

        retrieveJokes();
        mDataSource.getJokes().observeForever(jokes -> {
            mExecutors.getDiskExecutor().execute(() -> {
                mDao.insertJokes(jokes);
                Log.d(LOG_TAG, LOG_JOKES_INSERTED);
            });
        });


    }


    public static JokeRepository getInstance(JokeDao dao, JokesDataSource dataSource, AppExecutors executors) {
        if (sInstance == null) {
            sInstance = new JokeRepository(dataSource, dao, executors);
        }

        return sInstance;
    }

    private void retrieveJokes() {
        mExecutors.getNetworkExecutor().execute(() -> {
            if (isDataFetchNeeded()) {
                mDataSource.fetchJokes();
            }
        });
    }

    /**
     * Get a specific joke by its id.
     *
     * @param jokeId
     * @return LiveData<Joke> joke
     */
    public LiveData<Joke> getJokeById(int jokeId) {
        return mDao.getJokeById(jokeId);
    }



    private boolean isDataFetchNeeded() {
        return mDao.getJokeCount() <= 0;
    }

}
