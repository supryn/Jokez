package com.supryn.android.joke.ui.fragment;

public class HomeJokeFragment extends BaseJokeFragment {

    @Override
    int getJokeId(int pageNumber) {
        return pageNumber;
    }

}