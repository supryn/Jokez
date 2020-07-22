package com.supryn.android.joke.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.supryn.android.joke.data.JokeRepository;
import com.supryn.android.joke.model.Joke;

import java.util.List;

/**
 * ViewModel for storing a UI data related to one Joke.
 *
 *
 */
public class JokeViewModel extends ViewModel {

    private JokeRepository mRepository;

    public JokeViewModel(JokeRepository repository) {
        mRepository = repository;
    }

    public LiveData<Joke> getJoke(int pageNumber) {
        return mRepository.getJokeById(pageNumber);
    }

    public void updateFavorite(int jokeId, boolean isFavorite) {
        mRepository.updateFavoriteJoke(jokeId, isFavorite);
    }

    public LiveData<List<Joke>> getFavoriteJokes() {
        return mRepository.getFavoriteJokes();
    }



}
