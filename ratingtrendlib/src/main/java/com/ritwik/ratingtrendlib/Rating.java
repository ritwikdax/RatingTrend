package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Rating {

    public static final float WIDTH_BY_HEIGHT_RATIO = 1.5f;

    private int mValue;
    private Paint mPaint;
    private float mCornerRadius;
    private RectF mRect;
    private int mStrokeColor;
    private int mFillColor;
    private float mWidth;
    private float mHeight;
    private int mPadding;

    private float mStrokeWidth;


    /***
     *
     * @param value
     * @param cornerRadius
     *
     *
     */
    public Rating(int value, float cornerRadius, float strokeWidth) {

        this.mValue = value;
        this.mCornerRadius = cornerRadius;
        this.mHeight = mWidth/WIDTH_BY_HEIGHT_RATIO;
        this.mStrokeWidth = strokeWidth;
        this.mPaint = new Paint();
        mPaint.setAntiAlias(true);
        this.mRect = new RectF();

    }


    public void setmStrokeColor(int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }

    public void setmFillColor(int mFillColor) {
        this.mFillColor = mFillColor;
    }

    /***
     * Draw the individual rectangle
     * @param canvas
     */

    public void drawSelf(Canvas canvas){

        mRect.set((0.5f * mStrokeWidth), (0.5f*mStrokeWidth),
                mWidth - (0.5f * mStrokeWidth), mHeight - (0.5f* mStrokeWidth));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mFillColor);
        canvas.drawRoundRect(mRect, mCornerRadius, mCornerRadius, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mStrokeColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawRoundRect(mRect, mCornerRadius, mCornerRadius, mPaint);

        //drawing text
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(30);
        canvas.drawText(String.valueOf(mValue), mWidth/2, (mHeight/2)+ 7, mPaint);

    }

    public void setmWidth(float width){
        this.mWidth = width;
        this.mHeight = mWidth/WIDTH_BY_HEIGHT_RATIO;
    }

}
