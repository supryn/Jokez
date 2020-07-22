package com.supryn.android.joke.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.supryn.android.joke.R;
import com.supryn.android.joke.ui.JokeClickListener;
import com.supryn.android.joke.ui.adapter.HomeJokePagerAdapter;

/**
 * Fragment displaying a Joke on the Home screen.
 *
 */
public class HomeFragment extends Fragment implements JokeClickListener {

    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HomeJokePagerAdapter mPagerAdapter = new HomeJokePagerAdapter(getActivity().getSupportFragmentManager(), this);
        ViewPager viewPager = view.findViewById(R.id.tab_viewpager);
        viewPager.setAdapter(mPagerAdapter);

        mInterstitialAd = new InterstitialAd(getActivity().getApplicationContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new InterstitialAddListener(mInterstitialAd));

        return view;
    }

    @Override
    public void onClick() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}

class InterstitialAddListener extends AdListener {

    private InterstitialAd mInterstitialAd;

    InterstitialAddListener(InterstitialAd interstitialAd) {
        mInterstitialAd = interstitialAd;
    }

    @Override
    public void onAdClosed() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}