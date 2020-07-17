package com.supryn.android.joke.data.network;



import com.supryn.android.joke.model.Joke;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JokesAPI {

    @GET("any")
    Observable<Joke> fetchJoke(@Query("idRange") int idRange);

}
