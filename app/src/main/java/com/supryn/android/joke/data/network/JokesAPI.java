package com.supryn.android.joke.data.network;



import com.supryn.android.joke.model.Joke;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JokesAPI {

    @GET("any")
    Observable<Joke> fetchJoke();

}
