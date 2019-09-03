package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;



public class RatingTrendView extends View {

    private static final String TAG = "Rating";

    /*Default values*/
    private static final float DEFAULT_STROKE_WIDTH = 4f;
    private static final float DEFAULT_FONT_SIZE = 4f;
    private static final float DEFAULT_SPACING = 5f;

    private static final int DEFAULT_STROKE_COLOR = 0xFF305D02;
    private static final int DEFAULT_RATE_VALUE = 5;
    private static final float DEFAULT_CORNER_RADIUS = 20f;
    private static final float DEFAULT_WIDTH_BY_HEIGHT_RATIO = 1.3f;




    /***
     * User defined values
     */
    private int mWidth;
    private int mHeight;

    private int[] mRatingSequence;
    private Rating[] mRatings;

    private int mOneStarStrokeColor;
    private int mOneStarFillColor;
    private int mTwoStarStrokeColor;
    private int mTwoStarFillColor;
    private int mThreeStarStrokeColor;
    private int mThreeStarFillColor;
    private int mFourStarStrokeColor;
    private int mFourStarFillColor;
    private int mFiveStarStrokeColor;
    private int mFiveStarFillColor;

    private float mStrokeWidth = DEFAULT_STROKE_WIDTH;
    private float mCornerRadius = DEFAULT_CORNER_RADIUS;
    private float mSpacing = DEFAULT_SPACING;
    private int mStarIcon;


    private int mBoxWidth;
    private int mBoxHeight;



    private Paint mStrokePaint;
    private Paint mFillPaint;

    private int mStrokeColor = DEFAULT_STROKE_COLOR;
    private int mFillColor = DEFAULT_STROKE_COLOR;




    public RatingTrendView(Context context) {
        this(context, null);
    }

    public RatingTrendView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //Focus on this constructor
    public RatingTrendView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }


    /***
     * Initializing user defined values
     *
     * @param context :
     * @param attrs :
     */
    private void initAttrs(Context context, AttributeSet attrs){

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingTrendView);
        mCornerRadius = typedArray.getDimension(R.styleable.RatingTrendView_rtv_cornerRadius,
                DEFAULT_CORNER_RADIUS);
        mStrokeWidth = typedArray.getDimension(R.styleable.RatingTrendView_rtv_strokeWidth,
                DEFAULT_STROKE_WIDTH);
        mStarIcon = typedArray.getResourceId(R.styleable.RatingTrendView_rtv_starIcon, R.drawable.ic_star);




        /***
         * Initializing colours
         */
        mOneStarStrokeColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.oneStarStroke));
        mOneStarFillColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.oneStarFill));
        mTwoStarStrokeColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.twoStarStroke));
        mTwoStarFillColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.twoStarFill));
        mThreeStarStrokeColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.threeStarStroke));
        mThreeStarFillColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.threeStarFill));
        mFourStarStrokeColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.fourStarStroke));
        mFourStarFillColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.fourStarFill));
        mFiveStarStrokeColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.fiveStarStroke));
        mFiveStarFillColor = typedArray.getColor(R.styleable.RatingTrendView_rtv_oneStarStrokeColor,
                getResources().getColor(R.color.fiveStarFill));

        typedArray.recycle();

    }

    private int getDefaultWidth(){

        return 20;
    }
    private int getDefaultHeight(int measureSpec){
        int width = MeasureSpec.getSize(measureSpec);
        return (int) ((width/8) / DEFAULT_WIDTH_BY_HEIGHT_RATIO) + getPaddingBottom() + getPaddingTop();
    }

    private int getExpectedSize(int size, int measureSpec){

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
        int defHeight = getDefaultHeight(widthMeasureSpec);

        setMeasuredDimension(getExpectedSize(defWidth, widthMeasureSpec),
                getExpectedSize(defHeight, heightMeasureSpec));

        mBoxHeight = getHeight();
        mBoxWidth = getWidth();

        Log.d(TAG, "onMeasure: "+ mBoxWidth +" "+mBoxHeight );
    }

    /***
     * @deprecated  {@link #DEFAULT_FONT_SIZE}
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        int bw = mBoxWidth/8;
        int i=1;
        canvas.save();
        mRatings[0].setmWidth(getWidth()/8);
        mRatings[0].drawSelf(canvas);

        /*
        if (mRatingSequence == null){
            return;
        }
        for (Rating rating : mRatings){


            rating.setmWidth(getWidth()/8);
            rating.drawSelf(canvas);
            canvas.translate(bw + mSpacing, 0);

        }

         */
        canvas.restore();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mBoxHeight = getHeight();
        mBoxWidth = getWidth();
        mBoxWidth = (int) (mBoxWidth - (7* mSpacing));

        Log.d(TAG, "onSizeChanged: getHeight()"+ mBoxHeight + " "+ mBoxWidth);
        Log.d(TAG, "onSizeChanged: getMeasuredWidth()" + " " + mWidth +" "+ mHeight );

    }

    /***
     * Give appropriate rating obj according to value like 1* 2* 3* etc
     *
     * @param value : must be in range(1,5)
     * @return : Rating obj
     *
     */
    private Rating getRating(int value ){

        Rating rating = new Rating(value, mCornerRadius);

        switch (value){
            case 1:
                rating.setmFillColor(mOneStarFillColor);
                rating.setmStrokeColor(mOneStarStrokeColor);
                break;

            case 2:
                rating.setmStrokeColor(mTwoStarStrokeColor);
                rating.setmFillColor(mTwoStarFillColor);
                break;

            case 3:
                rating.setmFillColor(mThreeStarFillColor);
                rating.setmStrokeColor(mThreeStarStrokeColor);
                break;

            case 4:
                rating.setmStrokeColor(mFourStarStrokeColor);
                rating.setmFillColor(mFourStarFillColor);
                break;

            case 5:
                rating.setmFillColor(mFiveStarFillColor);
                rating.setmStrokeColor(mFiveStarFillColor);
                break;
        }
        return rating;
    }


    /***
     * Setting last 8 rating Sequence
     * @param ratingSeq : array [5,4,2,1,2,1,1,4]
     *
     */
    public void setRatingSequence(int[] ratingSeq){

        this.mRatingSequence = ratingSeq;
        mRatings = new Rating[mRatingSequence.length];

        for (int i=0; i<ratingSeq.length; i++){
            mRatings[i] = getRating(ratingSeq[i]);
        }

        invalidate();
    }
}
