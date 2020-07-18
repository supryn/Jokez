package com.supryn.android.joke.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.supryn.android.joke.ui.fragment.JokeFragment;

public class JokeFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    // TODO : change size to be dynamic
    private static final int JOKES_SIZE = 30;

    public JokeFragmentStatePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return JokeFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return JOKES_SIZE;
    }


}
