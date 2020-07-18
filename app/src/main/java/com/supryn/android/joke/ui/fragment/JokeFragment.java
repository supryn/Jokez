package com.supryn.android.joke.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.supryn.android.joke.R;
import com.supryn.android.joke.databinding.FragmentJokeBinding;
import com.supryn.android.joke.model.Joke;
import com.supryn.android.joke.ui.JokeViewModel;
import com.supryn.android.joke.ui.JokeViewModelFactory;
import com.supryn.android.joke.utility.ObjectProviderUtil;


public class JokeFragment extends Fragment {

    private static final String PAGE_POSITION = "pagePosition";


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
        int pageNumber = getArguments().getInt(PAGE_POSITION);

        FragmentJokeBinding binding = FragmentJokeBinding.inflate(inflater);
        binding.setLifecycleOwner(this);

        JokeViewModelFactory factory = ObjectProviderUtil.provideJokeViewModelFactory(getActivity().getApplicationContext(), pageNumber);
        JokeViewModel viewModel = new ViewModelProvider(this, factory).get(JokeViewModel.class);
        viewModel.getJoke().observe(getViewLifecycleOwner(), joke -> {
            binding.setJoke(joke);
        });

        return binding.getRoot();
    }
}