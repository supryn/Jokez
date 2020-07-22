package com.supryn.android.joke.utility;

import android.content.Context;

import com.supryn.android.joke.data.AppExecutors;
import com.supryn.android.joke.data.JokeRepository;
import com.supryn.android.joke.data.database.JokeDatabase;
import com.supryn.android.joke.data.network.JokesDataSource;
import com.supryn.android.joke.ui.main.JokeViewModelFactory;

/**
 * Utility class providing ViewModel object(s).
 */
public final class ObjectProviderUtil {


    public static JokeViewModelFactory provideJokeViewModelFactory(Context context) {
        return new JokeViewModelFactory(provideRepository(context));
    }


    public static JokeRepository provideRepository(Context context) {
        return JokeRepository.getInstance(
                JokeDatabase.getInstance(context).jokeDao(),
                JokesDataSource.getInstance(context),
                AppExecutors.getInstance());
    }
}
