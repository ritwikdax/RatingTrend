package com.ritwik.ratingtrendlib;

import android.content.Context;

public class Rating {

    private int mValue;
    private int mStrokeColor;
    private int mFillColor;
    private Context mContext;

    public Rating(int value) {
        this.mValue = value;



    }

    private int getAppropriateStrokeColor(int value){
        int color = 0xff000000;
        switch (value){

        }
        return color;
    }
}
