package com.supryn.android.joke.data.network;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.supryn.android.joke.model.Joke;

import io.reactivex.schedulers.Schedulers;

/**
 * Jokes Data Source that calls the retrofit client to help retrieve a random joke.
 *
 */
public final class JokesDataSource {

    private static JokesDataSource sInstance;
    private static RetrofitClient mRetrofitClient;
    private static MutableLiveData<Joke> mJoke;

    private JokesDataSource() {
        mRetrofitClient = RetrofitClient.getInstance();
    }

    public static JokesDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new JokesDataSource();
            mJoke = new MutableLiveData<>();
        }

        return sInstance;
    }


    public void fetchJoke() {
        JokesAPI jokesAPI = mRetrofitClient.getJokesAPI();
        jokesAPI
                .fetchJoke()
                .subscribeOn(Schedulers.io())
                .subscribe(joke -> mJoke.postValue(joke));
    }

    public static LiveData<Joke> getJoke() {
        return mJoke;
    }
}
