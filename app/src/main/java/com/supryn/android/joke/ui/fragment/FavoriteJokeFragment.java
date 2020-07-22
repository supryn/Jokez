package com.supryn.android.joke.ui.fragment;

import java.util.List;

/**
 * Fragment displaying a joke marked as favorite.
 *
 */
public class FavoriteJokeFragment extends BaseJokeFragment {

    private static final String JOKE_IDS = "jokeIds";

    @Override
    int getJokeId(int pageNumber) {
        List<Integer> jokeIds = getArguments().getIntegerArrayList(JOKE_IDS);
        return jokeIds.get(pageNumber);
    }

}
