package com.supryn.android.joke.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supryn.android.joke.R;
import com.supryn.android.joke.model.Joke;
import com.supryn.android.joke.ui.JokeViewModel;
import com.supryn.android.joke.ui.JokeViewModelFactory;
import com.supryn.android.joke.ui.adapter.FavoriteJokePagerAdapter;
import com.supryn.android.joke.utility.ObjectProviderUtil;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

    private JokeViewModel mViewModel;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mViewPager = view.findViewById(R.id.tab_viewpager);


        JokeViewModelFactory factory = ObjectProviderUtil.provideJokeViewModelFactory(getActivity().getApplicationContext());
        mViewModel = new ViewModelProvider(this, factory).get(JokeViewModel.class);
        mViewModel.getFavoriteJokes().observe(getViewLifecycleOwner(), jokes -> {
            if (jokes != null) {
                mViewPager.setAdapter(new FavoriteJokePagerAdapter(
                        getActivity().getSupportFragmentManager(),
                        jokes.size(),
                        getFavoriteJokeIds(jokes)
                ));
            }
        });

        return view;
    }

    private List<Integer> getFavoriteJokeIds(List<Joke> jokes) {
        List<Integer> favoriteJokeIds = new ArrayList<>();
        for (int i = 0; i < jokes.size(); i++) {
            favoriteJokeIds.add(jokes.get(i).jokeId);
        }

        return favoriteJokeIds;
    }
}