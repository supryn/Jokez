package com.supryn.android.joke.ui.fragment;

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
import com.supryn.android.joke.ui.main.JokeViewModel;
import com.supryn.android.joke.ui.main.JokeViewModelFactory;
import com.supryn.android.joke.utility.ObjectProviderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Joke Fragment for two concrete types of joke fragments.
 * 1) HomeJokeFragment: Jokes displayed on the Home screen.
 * 2) FavoriteJokeFragment: Jokes displayed on the Favorite screen.
 *
 */
public abstract class BaseJokeFragment extends Fragment {

    static final String PAGE_POSITION = "pagePosition";
    private static final String PREF_FAVORITE_KEY = "key_favorite";
    private static final String JOKE_IDS = "jokeIds";

    private JokeViewModel mViewModel;
    private AnimatedVectorDrawable mEmptyHeart;
    private AnimatedVectorDrawable mFillHeart;
    private ImageView mButtonFavoriteJoke;
    private int mPageNumber;
    private boolean mIsFavorite;

    public static BaseJokeFragment getInstance(int fragmentResId, int position, List<Integer> favoriteJokeIds) {
        BaseJokeFragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_POSITION, position);
        switch (fragmentResId) {
            case R.string.app_fragment_home_joke:
                fragment = new HomeJokeFragment();
                break;
            case R.string.app_fragment_favorite_joke:
                bundle.putIntegerArrayList(JOKE_IDS, (ArrayList<Integer>) favoriteJokeIds);
                fragment = new FavoriteJokeFragment();
                break;
            default:
                throw new RuntimeException("Fragment with resId " + fragmentResId + " not found.");
        }
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentJokeBinding binding = FragmentJokeBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        mPageNumber = getArguments().getInt(PAGE_POSITION);
        mEmptyHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_empty);
        mFillHeart = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.avd_heart_fill);
        mButtonFavoriteJoke = binding.getRoot().findViewById(R.id.button_favorite_joke);
        setupClickListener();

        mIsFavorite = PreferenceManager
                .getDefaultSharedPreferences(getActivity())
                .getBoolean(PREF_FAVORITE_KEY + getJokeId(mPageNumber), false);
        AnimatedVectorDrawable drawable = mIsFavorite ? mFillHeart : mEmptyHeart;
        mButtonFavoriteJoke.setImageDrawable(drawable);
        drawable.start();

        JokeViewModelFactory factory = ObjectProviderUtil.provideJokeViewModelFactory(getActivity().getApplicationContext());
        mViewModel = new ViewModelProvider(this, factory).get(JokeViewModel.class);
        mViewModel.getJoke(getJokeId(mPageNumber)).observe(getViewLifecycleOwner(), binding::setJoke);

        return binding.getRoot();
    }

    private void setupClickListener() {
        mButtonFavoriteJoke.setOnClickListener(v -> {
            AnimatedVectorDrawable drawable = mIsFavorite ? mEmptyHeart : mFillHeart;
            mButtonFavoriteJoke.setImageDrawable(drawable);
            drawable.start();
            mIsFavorite = !mIsFavorite;
            PreferenceManager
                    .getDefaultSharedPreferences(getActivity())
                    .edit()
                    .putBoolean(PREF_FAVORITE_KEY + getJokeId(mPageNumber), mIsFavorite)
                    .apply();
            mViewModel.updateFavorite(getJokeId(mPageNumber), mIsFavorite);
        });
    }

    abstract int getJokeId(int pageNumber);

}
