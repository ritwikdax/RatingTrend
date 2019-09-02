package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Rating extends View {
    /*Default values*/
    private static final float DEFAULT_STROKE_WIDTH = 4f;
    private static final float DEFAULT_FONT_SIZE = 4f;

    private static final int DEFAULT_STROKE_COLOR = 0xFF305D02;
    private static final int DEFAULT_RATE_VALUE = 5;
    private static final float DEFAULT_CORNER_RADIUS = 2f;



    /***
     * User defined values
     */
    private int mWidth;
    private int mHeight;

    private Paint mStrokePaint;
    private Paint mFillPaint;
    private int mRateValue = DEFAULT_RATE_VALUE;
    private float mStrokeWidth = DEFAULT_STROKE_WIDTH;
    private int mStrokeColor = DEFAULT_STROKE_COLOR;
    private int mFillColor = DEFAULT_STROKE_COLOR;
    private float mFontSize = DEFAULT_FONT_SIZE;
    private float mCornerRadius = DEFAULT_CORNER_RADIUS;



    public Rating(Context context) {
        this(context, null);
    }

    public Rating(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Rating(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    /***
     * Initializing user defined values
     *
     * @param context :
     * @param attrs :
     */
    private void initAttrs(Context context, AttributeSet attrs){

    }

    public void initPaints(){


        mFillPaint = new Paint();
        mFillPaint.setColor(getResources().getColor(R.color.red));
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStyle(Paint.Style.FILL);

        mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(mStrokeColor);
        mStrokePaint.setStrokeWidth(mStrokeWidth);

    }

    private int getDefaultWidth(){

        return 20;
    }
    private int getDefaultHeight(){
        return 20;
    }

    private int getExpectSize(int size, int measureSpec){

        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(size, specSize);
                break;
            default:
                break;
        }
        return result;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defWidth = getDefaultWidth();
        int defHeight = getDefaultHeight();
        setMeasuredDimension(getExpectSize(defWidth, widthMeasureSpec),
                getExpectSize(defHeight, heightMeasureSpec));
    }

    /***
     * @deprecated  {@link #DEFAULT_FONT_SIZE}
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        float left = mStrokeWidth * 0.5f;
        float top = mStrokeWidth * 0.5f;
        float right = getWidth() - mStrokeWidth * 0.5f;
        float bottom = getHeight() - mStrokeWidth * 0.5f;

        canvas.drawRoundRect(new RectF(left, top, right, bottom),
                20, 20, mStrokePaint);



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
}
