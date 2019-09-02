package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Rating {

    private int mValue;
    private Paint mStrokePaint;
    private Paint mFillPaint;
    private float mCornerRadius;
    private RectF filledRect;
    private RectF strokeRect;

    public Rating(int value, float cornerRadius) {

        this.mValue = value;
        this.mCornerRadius = cornerRadius;
        filledRect = new RectF();
        strokeRect = new RectF();

    }

    public void setmValue(int mValue) {
        this.mValue = mValue;
    }

    public void setmStrokePaint(Paint mStrokePaint) {
        this.mStrokePaint = mStrokePaint;
    }

    public void setmFillPaint(Paint mFillPaint) {
        this.mFillPaint = mFillPaint;
    }

    public int getmValue() {
        return mValue;
    }

    public Paint getmStrokePaint() {
        return mStrokePaint;
    }

    public Paint getmFillPaint() {
        return mFillPaint;
    }

    public void drawSelf(float left, float top, float right, float bottom, Canvas canvas){

        filledRect.set(left, top, right, bottom);
        strokeRect.set(left, top, right, bottom);
        canvas.drawRoundRect(filledRect, mCornerRadius, mCornerRadius, mFillPaint);
        canvas.drawRoundRect(strokeRect, mCornerRadius, mCornerRadius, mStrokePaint);

    }
}
