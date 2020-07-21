package com.supryn.android.joke.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.supryn.android.joke.R;
import com.supryn.android.joke.ui.adapter.JokeFragmentStatePagerAdapter;

/**
 * Fragment that displays a Joke.
 *
 */
public class HomeFragment extends Fragment {

    private JokeFragmentStatePagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPagerAdapter = new JokeFragmentStatePagerAdapter(getActivity().getSupportFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.tab_viewpager);
        viewPager.setAdapter(mPagerAdapter);


        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}