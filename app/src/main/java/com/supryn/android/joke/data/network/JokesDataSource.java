package com.supryn.android.joke.data.network;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.supryn.android.joke.model.Joke;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Jokes Data Source that calls the retrofit client to help retrieve a random joke.
 *
 */
public final class JokesDataSource {

    private static JokesDataSource sInstance;
    private static RetrofitClient mRetrofitClient;
    private static MutableLiveData<List<Joke>> mJokes;

    //TODO : change to be dynamic
    private static final int JOKES_SIZE = 30;

    private JokesDataSource(Context context) {
        mRetrofitClient = RetrofitClient.getInstance(context);
    }

    public static JokesDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new JokesDataSource(context);
            mJokes = new MutableLiveData<>();
        }

        return sInstance;
    }


    public void fetchJokes() {
        JokesAPI jokesAPI = mRetrofitClient.getJokesAPI();

        List<Observable<Joke>> requests = new ArrayList<>();
        for (int i = 0; i <= JOKES_SIZE; i++) {
            requests.add(jokesAPI.fetchJoke(i));
        }

        Observable.zip(requests, objects -> {
            List<Joke> jokes = new ArrayList<>();
            for (Object o : objects) {
                jokes.add((Joke) o);
            }
            return jokes;
        })
        .subscribeOn(Schedulers.io())
        .subscribe(jokes -> mJokes.postValue(jokes));
    }

    public LiveData<List<Joke>> getJokes() {
        return mJokes;
    }
}
