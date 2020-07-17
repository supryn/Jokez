package com.supryn.android.joke.ui;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class JokeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final long VELOCITY_THRESHOLD = 3000;

    @Override
    public boolean onDown(final MotionEvent e){ return true; }

    @Override
    public boolean onFling(final MotionEvent e1, final MotionEvent e2,
                           final float velocityX,
                           final float velocityY){

        if(Math.abs(velocityX) < VELOCITY_THRESHOLD
                && Math.abs(velocityY) < VELOCITY_THRESHOLD){
            return false;//if the fling is not fast enough then it's just like drag
        }

        //if velocity in X direction is higher than velocity in Y direction,
        //then the fling is horizontal, else->vertical
        if(Math.abs(velocityX) > Math.abs(velocityY)){
            if(velocityX >= 0){
                Log.i("TAG", "swipe right");
            }else{//if velocityX is negative, then it's towards left
                Log.i("TAG", "swipe left");
            }
        }else{
            if(velocityY >= 0){
                Log.i("TAG", "swipe down");
            }else{
                Log.i("TAG", "swipe up");
            }
        }

        return true;
    }
}
