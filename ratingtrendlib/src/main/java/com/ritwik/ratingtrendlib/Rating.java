package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

public class Rating {
    public static final String TAG = "Rating";

    public static final float WIDTH_BY_HEIGHT_RATIO = 1.5f;
    public static final int STAR_ICON_SIZE = 20;


    private int mValue;
    private Paint mPaint;
    private float mCornerRadius;
    private RectF mRect;
    private int mStrokeColor;
    private int mFillColor;
    private float mWidth;
    private float mHeight;
    private int mPadding;
    private int mStarIcon;
    private Context mContext;

    private float mStrokeWidth;
    private int mStarIconSize = STAR_ICON_SIZE;


    /***
     *
     *
     * @param cornerRadius
     *
     *
     */
    public Rating(float cornerRadius, float strokeWidth, Context context) {

        this.mCornerRadius = cornerRadius;
        this.mStrokeWidth = strokeWidth;
        this.mPaint = new Paint();
        mPaint.setAntiAlias(true);
        this.mRect = new RectF();
        this.mContext = context;

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
        canvas.drawText(String.valueOf(mValue), (mWidth/2) - 24, (mHeight/2) +10, mPaint);

        //draw the star
        //mStarIcon.setBounds((int)(mWidth/2)+5,(int)(mHeight/2) -10, (int)(mWidth/2) + 25, (int)(mHeight/2) +10 );
        //mStarIcon.setBounds((int)(mWidth/2),(int)(mHeight/2)-20, (int)(mWidth/2)+20 ,(int)(mHeight/2)-20 );
        //mStarIcon.draw(canvas);

        //DrawableCompat.wrap(mDrawableIcon);

        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_star);
        Drawable wrapDrawable = DrawableCompat.wrap(drawable);
        //DrawableCompat.setTint(wrapDrawable, mStrokeColor);

        DrawableCompat.setTint(wrapDrawable, mStrokeColor);
        wrapDrawable.setBounds((int)(mWidth/2)+5,(int)(mHeight/2) -10, (int)(mWidth/2) + 25, (int)(mHeight/2) +10 );
        wrapDrawable.mutate();
        wrapDrawable.draw(canvas);
        //mDrawableIcon = null;
        //DrawableCompat.unwrap(mDrawableIcon);


        Log.d(TAG, "drawSelf: Colour is" +  mStrokeColor);


    }

    public void setmWidth(float width){
        this.mWidth = width;
        this.mHeight = mWidth/WIDTH_BY_HEIGHT_RATIO;
        this.mStarIconSize = (int) (width/2);
    }

    public void setmValue(int mValue) {
        this.mValue = mValue;

    }
}
