package com.supryn.android.joke.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.supryn.android.joke.R;
import com.supryn.android.joke.databinding.FragmentJokeBinding;
import com.supryn.android.joke.model.Joke;
import com.supryn.android.joke.ui.JokeSwipeListener;
import com.supryn.android.joke.ui.JokeWidgetProvider;
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

    private JokeSwipeListener mClickListener;
    private JokeViewModel mViewModel;
    private AnimatedVectorDrawable mEmptyHeart;
    private AnimatedVectorDrawable mFillHeart;
    private ImageView mButtonFavoriteJoke;
    private ImageButton mButtonShareJoke;
    private int mPageNumber;
    private boolean mIsFavorite;


    BaseJokeFragment(JokeSwipeListener clickListener) {
        super();
        mClickListener = clickListener;
    }

    public static BaseJokeFragment getInstance(int fragmentResId, JokeSwipeListener clickListener, int position, List<Integer> favoriteJokeIds) {
        BaseJokeFragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_POSITION, position);
        switch (fragmentResId) {
            case R.string.app_fragment_home_joke:
                fragment = new HomeJokeFragment(clickListener);
                break;
            case R.string.app_fragment_favorite_joke:
                bundle.putIntegerArrayList(JOKE_IDS, (ArrayList<Integer>) favoriteJokeIds);
                fragment = new FavoriteJokeFragment(clickListener);
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
        setupFavoriteButtonClickListener();
        mButtonShareJoke = binding.getRoot().findViewById(R.id.button_share_joke);

        mIsFavorite = PreferenceManager
                .getDefaultSharedPreferences(getActivity())
                .getBoolean(PREF_FAVORITE_KEY + getJokeId(mPageNumber), false);
        AnimatedVectorDrawable drawable = mIsFavorite ? mFillHeart : mEmptyHeart;
        mButtonFavoriteJoke.setImageDrawable(drawable);
        drawable.start();

        JokeViewModelFactory factory = ObjectProviderUtil.provideJokeViewModelFactory(getActivity().getApplicationContext());
        mViewModel = new ViewModelProvider(this, factory).get(JokeViewModel.class);
        mViewModel.getJoke(getJokeId(mPageNumber)).observe(getViewLifecycleOwner(), joke -> {
            if (joke != null) {
                binding.setJoke(joke);
                setupShareButtonClickListener(joke);
                JokeWidgetProvider.sendRefreshBroadcast(
                        getActivity().getApplicationContext(),
                        joke);
            }
        });

        invokeInterstitialAd();
        return binding.getRoot();
    }

    private void setupFavoriteButtonClickListener() {
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

    private void setupShareButtonClickListener(Joke joke) {
        mButtonShareJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, parseJokeText(joke));
                sendIntent.setType(getString(R.string.app_share_mime_type));
                Intent shareIntent = Intent.createChooser(sendIntent, getString(R.string.app_share_via_text));
                startActivity(shareIntent);
            }

            private String parseJokeText(Joke aJoke) {
                return aJoke.delivery == null ? aJoke.joke : aJoke.joke + " " + aJoke.delivery;
            }
        });
    }

    abstract int getJokeId(int pageNumber);

    private void invokeInterstitialAd() {
        // launch an interstitial ad every 5th joke
        if (mPageNumber != 0 && mPageNumber % 5 == 0) {
            mClickListener.onSwipe();
        }
    }
}
