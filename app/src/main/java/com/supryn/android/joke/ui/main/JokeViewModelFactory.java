package com.supryn.android.joke.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.supryn.android.joke.data.JokeRepository;

public class JokeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final JokeRepository mRepository;

    public JokeViewModelFactory(JokeRepository repository) {
        mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create( Class<T> modelClass) {
        return (T) new JokeViewModel(mRepository);
    }
}
