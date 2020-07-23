package com.supryn.android.joke.ui;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.supryn.android.joke.R;
import com.supryn.android.joke.model.Joke;
import com.supryn.android.joke.ui.main.MainActivity;


/**
 * Implementation class for Jokez Widget, displaying one random joke.
 *
 */
public class JokeWidgetProvider extends AppWidgetProvider {

    static final String KEY_JOKE_ID = "KEY_JOKE_ID";
    static final String KEY_JOKE_MAIN = "KEY_JOKE_MAIN";
    static final String KEY_JOKE_DELIVERY = "KEY_JOKE_DELIVERY";


    @SuppressLint("ApplySharedPref")
    public static void sendRefreshBroadcast(Context context, Joke joke) {
        getPreferenceEditor(context).putInt(KEY_JOKE_ID, joke.jokeId).commit();
        getPreferenceEditor(context).putString(KEY_JOKE_MAIN, joke.joke).commit();
        getPreferenceEditor(context).putString(KEY_JOKE_DELIVERY, joke.delivery).commit();
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, JokeWidgetProvider.class));
        context.sendBroadcast(intent);
    }

//    @Override
//    public void onReceive(final Context context, Intent intent) {
//        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
//            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
//            RemoteViews remoteViews = buildWidget(context);
//            mgr.updateAppWidget(getWidgetProvider(context), remoteViews);
//
//        }
//        super.onReceive(context, intent);
//    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = buildWidget(context);


        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0);


//        remoteViews.setOnClickPendingIntent();

        appWidgetManager.updateAppWidget(getWidgetProvider(context), remoteViews);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private static RemoteViews buildWidget(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.joke_widget);
//        String recipeName = PreferenceManager.getDefaultSharedPreferences(context).getString(RECIPE_NAME_KEY, null);



        return remoteViews;
    }

    private static ComponentName getWidgetProvider(Context context) {
        return new ComponentName(context, JokeWidgetProvider.class);
    }

    static SharedPreferences.Editor getPreferenceEditor(Context context) {
        return  PreferenceManager.getDefaultSharedPreferences(context).edit();
    }
}

