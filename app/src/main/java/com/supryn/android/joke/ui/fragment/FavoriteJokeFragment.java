package com.supryn.android.joke.ui.fragment;

import com.supryn.android.joke.ui.JokeClickListener;

import java.util.List;

/**
 * Fragment displaying a joke marked as favorite.
 *
 */
public class FavoriteJokeFragment extends BaseJokeFragment {

    public FavoriteJokeFragment(JokeClickListener clickListener) {
        super(clickListener);
    }

    private static final String JOKE_IDS = "jokeIds";

    @Override
    int getJokeId(int pageNumber) {
        List<Integer> jokeIds = getArguments().getIntegerArrayList(JOKE_IDS);
        return jokeIds.get(pageNumber);
    }

}
