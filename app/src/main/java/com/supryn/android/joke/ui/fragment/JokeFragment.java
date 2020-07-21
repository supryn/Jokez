package com.supryn.android.joke.ui.fragment;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.supryn.android.joke.R;
import com.supryn.android.joke.databinding.FragmentJokeBinding;
import com.supryn.android.joke.model.Joke;
import com.supryn.android.joke.ui.JokeViewModel;
import com.supryn.android.joke.ui.JokeViewModelFactory;
import com.supryn.android.joke.utility.ObjectProviderUtil;

import org.w3c.dom.Text;


public class JokeFragment extends Fragment {

    private static final String PAGE_POSITION = "pagePosition";
    private static final String PREF_FAVORITE_KEY = "key_favorite";

    private JokeViewModel mViewModel;
    private AnimatedVectorDrawable mEmptyHeart;
    private AnimatedVectorDrawable mFillHeart;
    private int mPageNumber;

    public static JokeFragment getInstance(int position) {
        JokeFragment fragment = new JokeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_POSITION, position);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentJokeBinding binding = FragmentJokeBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        mPageNumber = getArguments().getInt(PAGE_POSITION);
        setupClickListener(binding.getRoot().findViewById(R.id.favorite_joke_button));
        mEmptyHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_empty);
        mFillHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_fill);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isFavorite = sharedPreferences.getBoolean(PREF_FAVORITE_KEY + mPageNumber, false);
        AnimatedVectorDrawable drawable = isFavorite ? mFillHeart : mEmptyHeart;
        ((ImageView) binding.getRoot().findViewById(R.id.favorite_joke_button)).setImageDrawable(drawable);
        drawable.start();

        JokeViewModelFactory factory = ObjectProviderUtil.provideJokeViewModelFactory(getActivity().getApplicationContext());
        mViewModel = new ViewModelProvider(this, factory).get(JokeViewModel.class);
        mViewModel.getJoke(mPageNumber).observe(getViewLifecycleOwner(), joke -> {
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
            sharedPreferences.edit().putBoolean(PREF_FAVORITE_KEY + mPageNumber, isFavorite).commit();
            mViewModel.updateFavorite(mPageNumber, isFavorite);
        });
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