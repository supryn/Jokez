package com.supryn.android.joke.data.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.supryn.android.joke.model.Joke;
import com.supryn.android.joke.utility.JokeConstant;

import java.lang.reflect.Type;

/**
 * Deserializes the Json Joke Response into its java Joke model.
 *
 */
public class JokeDeserializer implements JsonDeserializer<Joke> {


    @Override
    public Joke deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jokeJson = json.getAsJsonObject();
        String jokeType = jokeJson.get(JokeConstant.JSON_JOKE_TYPE).getAsString();
        int jokeId = jokeJson.get(JokeConstant.JSON_JOKE_ID).getAsInt();

        Joke joke;
        if (JokeConstant.JSON_JOKE_TYPE_SINGLE.equals(jokeType)) {
            String onePartJoke = jokeJson.get(JokeConstant.JSON_JOKE_SINGLE).getAsString();
            joke = new Joke(jokeType, onePartJoke, jokeId);
        } else {
            String jokeSetup = jokeJson.get(JokeConstant.JSON_JOKE_TWOPART_SETUP).getAsString();
            String jokeDelivery = jokeJson.get(JokeConstant.JSON_JOKE_TWOPART_DELIVERY).getAsString();
            joke = new Joke(jokeType, jokeSetup, jokeDelivery, jokeId);
        }

        return joke;
    }
}
