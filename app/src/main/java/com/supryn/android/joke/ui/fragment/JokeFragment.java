package com.supryn.android.joke.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supryn.android.joke.R;
import com.supryn.android.joke.databinding.FragmentJokeBinding;
import com.supryn.android.joke.ui.JokeViewModel;
import com.supryn.android.joke.ui.JokeViewModelFactory;
import com.supryn.android.joke.utility.ObjectProviderUtil;

/**
 * Fragment that displays a Joke.
 *
 */
public class JokeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentJokeBinding binding = FragmentJokeBinding.inflate(inflater);
        binding.setLifecycleOwner(this);

        JokeViewModelFactory factory = ObjectProviderUtil.provideJokeViewModelFactory(getActivity().getApplicationContext());
        JokeViewModel viewModel = new ViewModelProvider(this, factory).get(JokeViewModel.class);
        viewModel.retrieveJoke().observe(getViewLifecycleOwner(), jokes -> {
            String a = "a";
        });


        return binding.getRoot();
    }
}