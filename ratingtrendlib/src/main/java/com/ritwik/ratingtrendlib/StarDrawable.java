package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StarDrawable extends Drawable {
    private Context mContext;
    private ColorFilter mColourFilter;



    public StarDrawable(Context context, ColorFilter colorFilter){
        mContext = context;
        mColourFilter = colorFilter;
    }




    @Override
    public void draw(@NonNull Canvas canvas) {
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_star);
        //drawable.setColorFilter(mColourFilter);
        drawable.draw(canvas);

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

        mColourFilter = colorFilter;
    }

    @Override
    public int getOpacity() {
        return 0;
    }

}
