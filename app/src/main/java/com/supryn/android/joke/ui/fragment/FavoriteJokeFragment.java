package com.supryn.android.joke.ui.fragment;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.supryn.android.joke.R;
import com.supryn.android.joke.databinding.FragmentJokeBinding;
import com.supryn.android.joke.ui.JokeViewModel;
import com.supryn.android.joke.ui.JokeViewModelFactory;
import com.supryn.android.joke.utility.ObjectProviderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment displaying a joke marked as favorite.
 *
 */
public class FavoriteJokeFragment extends Fragment {

    private static final String PAGE_POSITION = "pagePosition";
    private static final String JOKE_IDS = "jokeIds";
    private static final String PREF_FAVORITE_KEY = "key_favorite";

    private JokeViewModel mViewModel;
    private AnimatedVectorDrawable mEmptyHeart;
    private AnimatedVectorDrawable mFillHeart;
    private int mPageNumber;
    private List<Integer> mFavoriteJokeIds;
    private boolean full = false;

    public static FavoriteJokeFragment getInstance(int position, List<Integer> favoriteJokeIds) {
        FavoriteJokeFragment fragment = new FavoriteJokeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_POSITION, position);
        bundle.putIntegerArrayList(JOKE_IDS, (ArrayList<Integer>) favoriteJokeIds);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentJokeBinding binding = FragmentJokeBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        mPageNumber = getArguments().getInt(PAGE_POSITION);
        mFavoriteJokeIds = getArguments().getIntegerArrayList(JOKE_IDS);

        setupClickListener(binding.getRoot().findViewById(R.id.favorite_joke_button));
        mEmptyHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_empty);
        mFillHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_fill);


        JokeViewModelFactory factory = ObjectProviderUtil.provideJokeViewModelFactory(getActivity().getApplicationContext());
        mViewModel = new ViewModelProvider(this, factory).get(JokeViewModel.class);
        mViewModel.getJoke(mFavoriteJokeIds.get(mPageNumber)).observe(getViewLifecycleOwner(), joke -> {
            binding.setJoke(joke);
        });

        return binding.getRoot();
    }

    private void setupClickListener(ImageView imageView) {
        imageView.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean isFavorite = sharedPreferences.getBoolean(PREF_FAVORITE_KEY + mPageNumber, false);
            AnimatedVectorDrawable drawable = isFavorite ? mEmptyHeart : mFillHeart;
            imageView.setImageDrawable(drawable);
            drawable.start();
            isFavorite = !isFavorite;
            mViewModel.updateFavorite(mPageNumber, isFavorite);
            sharedPreferences.edit().putBoolean(PREF_FAVORITE_KEY + mPageNumber, isFavorite).apply();
        });
    }
}
