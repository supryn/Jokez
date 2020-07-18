package com.supryn.android.joke.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.supryn.android.joke.data.JokeRepository;

public class JokeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final JokeRepository mRepository;
    private int mPageNumber;


    public JokeViewModelFactory(JokeRepository repository, int pageNumber) {
        mRepository = repository;
        mPageNumber = pageNumber;
    }


    @Override
    public <T extends ViewModel> T create( Class<T> modelClass) {
        return (T) new JokeViewModel(mRepository, mPageNumber);
    }
}
