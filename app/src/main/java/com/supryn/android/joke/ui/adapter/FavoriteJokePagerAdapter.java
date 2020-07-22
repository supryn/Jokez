package com.supryn.android.joke.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.supryn.android.joke.R;
import com.supryn.android.joke.ui.fragment.BaseJokeFragment;

import java.util.List;

public class FavoriteJokePagerAdapter extends FragmentStatePagerAdapter {

    private int mFavoriteJokeSize;
    private List<Integer> mFavoriteJokeIds;

    public FavoriteJokePagerAdapter(@NonNull FragmentManager fm, int favoriteJokeSize, List<Integer> favoriteJokeIds) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFavoriteJokeSize = favoriteJokeSize;
        mFavoriteJokeIds = favoriteJokeIds;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BaseJokeFragment.getInstance(R.string.app_fragment_favorite_joke, position, mFavoriteJokeIds);
    }

    @Override
    public int getCount() {
        return mFavoriteJokeSize;
    }
}
