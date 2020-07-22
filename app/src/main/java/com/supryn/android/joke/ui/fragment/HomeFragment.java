package com.supryn.android.joke.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.supryn.android.joke.R;
import com.supryn.android.joke.ui.adapter.HomeJokePagerAdapter;

/**
 * Fragment displaying a Joke on the Home screen.
 *
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        HomeJokePagerAdapter mPagerAdapter = new HomeJokePagerAdapter(getActivity().getSupportFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.tab_viewpager);
        viewPager.setAdapter(mPagerAdapter);

        return view;
    }

}