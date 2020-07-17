package com.supryn.android.joke.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.supryn.android.joke.data.JokeRepository;
import com.supryn.android.joke.model.Joke;

import java.util.List;

public class JokeViewModel extends ViewModel {

    private JokeRepository mRepository;

    public JokeViewModel(JokeRepository repository) {
        mRepository = repository;
    }

    public LiveData<List<Joke>> retrieveJoke() {
        return mRepository.retrieveJoke();
    }
}