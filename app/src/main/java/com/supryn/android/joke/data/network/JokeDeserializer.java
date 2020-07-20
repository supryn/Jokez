package com.supryn.android.joke.data.network;

import android.content.Context;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.supryn.android.joke.R;
import com.supryn.android.joke.model.Joke;

import java.lang.reflect.Type;

/**
 * Deserializes the Json Joke Response into its java Joke model.
 *
 */
public class JokeDeserializer implements JsonDeserializer<Joke> {

    private Context mContext;

    public JokeDeserializer(Context context) {
        mContext = context;
    }

    @Override
    public Joke deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jokeJson = json.getAsJsonObject();
        String jokeType = jokeJson.get(getStringResource(R.string.json_joke_type)).getAsString();
        int jokeId = jokeJson.get(getStringResource(R.string.json_joke_id)).getAsInt();

        Joke joke;
        if (getStringResource(R.string.json_joke_type_single).equals(jokeType)) {
            String onePartJoke = jokeJson.get(getStringResource(R.string.json_joke_single)).getAsString();
            joke = new Joke(jokeType, onePartJoke, jokeId);
        } else {
            String jokeSetup = jokeJson.get(getStringResource(R.string.json_joke_twopart_setup)).getAsString();
            String jokeDelivery = jokeJson.get(getStringResource(R.string.json_joke_twopart_delivery)).getAsString();
            joke = new Joke(jokeType, jokeSetup, jokeDelivery, jokeId, false);
        }

        return joke;
    }

    private String getStringResource(int resId) {
        return mContext.getString(resId);
    }
}
