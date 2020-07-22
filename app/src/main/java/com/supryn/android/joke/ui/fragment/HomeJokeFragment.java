package com.supryn.android.joke.ui.fragment;

import com.supryn.android.joke.ui.JokeClickListener;

public class HomeJokeFragment extends BaseJokeFragment {

    public HomeJokeFragment(JokeClickListener clickListener) {
        super(clickListener);
    }

    @Override
    int getJokeId(int pageNumber) {
        return pageNumber;
    }

}