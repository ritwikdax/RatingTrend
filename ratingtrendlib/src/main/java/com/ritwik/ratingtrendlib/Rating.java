package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.core.graphics.drawable.DrawableCompat;


public class Rating {
    public static final String TAG = "Rating";

    public static final float WIDTH_BY_HEIGHT_RATIO = 1.5f;
    public static final int STAR_ICON_SIZE = 20;
    public static final int FONT_SIZE = 30;


    private int mValue;
    private Paint mPaint;
    private float mCornerRadius;
    private RectF mRect;
    private int mStrokeColor;
    private int mFillColor;
    private float mWidth;
    private float mHeight;
    private Context mContext;

    private float mStrokeWidth;
    private int mStarIconSize = STAR_ICON_SIZE;

    private int mStarIcon;


    /***
     *
     *
     * @param cornerRadius
     *
     *
     */
    public Rating(int starIcon, float cornerRadius, float strokeWidth, Context context) {

        this.mCornerRadius = cornerRadius;
        this.mStrokeWidth = strokeWidth;
        this.mPaint = new Paint();
        mPaint.setAntiAlias(true);
        this.mRect = new RectF();
        this.mContext = context;
        this.mStarIcon = starIcon;

    }


    public void setmStrokeColor(int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }

    public void setmFillColor(int mFillColor) {
        this.mFillColor = mFillColor;
    }


    public void setmWidth(float width){
        this.mWidth = width;
        this.mHeight = mWidth/WIDTH_BY_HEIGHT_RATIO;
        this.mStarIconSize = (int) (width/2);
    }

    public void setmValue(int mValue) {
        this.mValue = mValue;
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
        mPaint.setTextSize(FONT_SIZE);
        canvas.drawText(String.valueOf(mValue), (mWidth/2) - 24, (mHeight/2) +10, mPaint);

        //drawing the icons
        Drawable drawable = mContext.getResources().getDrawable(mStarIcon);
        Drawable wrapDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrapDrawable, mStrokeColor);
        wrapDrawable.setBounds((int)(mWidth/2)+5,(int)(mHeight/2) -10, (int)(mWidth/2) + 25, (int)(mHeight/2) +10 );
        wrapDrawable.mutate();
        wrapDrawable.draw(canvas);

        Log.d(TAG, "drawSelf: Colour is" +  mStrokeColor);

    }
}
