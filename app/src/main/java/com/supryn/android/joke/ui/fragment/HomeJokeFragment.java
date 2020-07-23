package com.supryn.android.joke.ui.fragment;

import com.supryn.android.joke.ui.JokeSwipeListener;

public class HomeJokeFragment extends BaseJokeFragment {

    public HomeJokeFragment(JokeSwipeListener clickListener) {
        super(clickListener);
    }

    @Override
    int getJokeId(int pageNumber) {
        return pageNumber;
    }

}