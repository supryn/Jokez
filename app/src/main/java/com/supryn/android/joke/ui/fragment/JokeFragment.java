package com.supryn.android.joke.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supryn.android.joke.R;

/**
 * Fragment that displays a Joke.
 *
 */
public class JokeFragment extends Fragment {



    public static JokeFragment getInstance() {
        JokeFragment fragment = new JokeFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(MOVIE_ID_KEY, movieId);
        fragment.setArguments(bundle);

        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false);
    }
}