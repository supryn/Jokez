package com.supryn.android.joke.data.network;


import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.supryn.android.joke.R;
import com.supryn.android.joke.model.Joke;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Wrapper class around Retrofit library.
 *
 */
final class RetrofitClient {

    private static RetrofitClient sInstance;
    private static Retrofit mRetrofit;


    private RetrofitClient(Context context) {
        mRetrofit = new Retrofit.Builder()
            .baseUrl(context.getString(R.string.jokes_api_base_url))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(createGsonConverter(context))
            .build();
    }

    static synchronized RetrofitClient getInstance(Context context) {
        if (sInstance == null) {
                sInstance = new RetrofitClient(context);
        }

        return sInstance;
    }

    private static Converter.Factory createGsonConverter(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(
                new TypeToken<Joke>() {}.getType(),
                new JokeDeserializer(context));

        return GsonConverterFactory.create(gsonBuilder.create());
    }

    JokesAPI getJokesAPI() { return mRetrofit.create(JokesAPI.class); }
}
