package com.supryn.android.joke.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.supryn.android.joke.data.database.JokeDao;
import com.supryn.android.joke.data.network.JokesDataSource;
import com.supryn.android.joke.model.Joke;

public final class JokeRepository {

    private static JokeRepository sInstance;
    private static JokesDataSource mDataSource;
    private static JokeDao mDao;
    private static AppExecutors mExecutors;
    private int mCurrentJokeId;


    private JokeRepository(JokesDataSource dataSource, JokeDao dao, AppExecutors executors) {
        mDataSource = dataSource;
        mDao = dao;
        mExecutors = executors;

        mDataSource.getJoke().observeForever(joke -> {
            Joke dbJoke = mDao.getJoke(joke.jokeId).getValue();
            if (dbJoke == null) {
                mDao.insertJoke(joke);
                mCurrentJokeId = joke.jokeId;
            }
        });
    }


    public static JokeRepository getInstance(JokeDao dao, JokesDataSource dataSource, AppExecutors executors) {
        if (sInstance == null) {
            sInstance = new JokeRepository(dataSource, dao, executors);
        }

        return sInstance;
    }

    public LiveData<Joke> retrieveJoke() {
        mExecutors.getNetworkExecutor().execute(() -> {
            mDataSource.fetchJoke();
        });

        return mDao.getJoke(mCurrentJokeId);
    }

}
