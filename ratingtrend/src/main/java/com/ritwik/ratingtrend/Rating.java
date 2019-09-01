package com.ritwik.ratingtrend;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Rating extends View {

    /*Default values*/
    private static final int DEFAULT_STROKE_WIDTH = 1;
    private static final int DEFAULT_FONT_SIZE = 4;
    private static final int DEFAULT_BG_COLOR = 0xdfe6d7;
    private static final int DEFAULT_STROKE_COLOR = 0x305d02;
    private static final int DEFAULT_RATE_VALUE = 5;
    private static final int DEFAULT_CORNER_RADIUS = 0;



    /***
     * User defined values
     */
    private Paint mStrokePaint;
    private Paint mFillPaint;
    private int mRateValue;
    private int mStrokeWidth;
    private int mBackgroundColor;
    private int mStrokeColor;
    private int mFontSize;
    private int mCornerRadius;



    public Rating(Context context) {
        super(context);
    }

    public Rating(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Rating(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*Initialising user defined values through xml*/
    private void init(Context context, AttributeSet attrs){


    }
}
