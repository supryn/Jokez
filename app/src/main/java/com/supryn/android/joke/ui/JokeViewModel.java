package com.supryn.android.joke.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.supryn.android.joke.data.JokeRepository;
import com.supryn.android.joke.model.Joke;

public class JokeViewModel extends ViewModel {

    private JokeRepository mRepository;
    private LiveData<Joke> mJoke;

    public JokeViewModel(JokeRepository repository, int pageNumber) {
        mRepository = repository;
        mJoke = mRepository.getJokeById(pageNumber);
    }

    public LiveData<Joke> getJoke() {
        return mJoke;
    }
}
