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
        prepareRatingSeq();
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

    private void prepareRatingSeq(){

        mRatingSequence = new int[]{1, 3, 5, 4, 1, 5,3,4};
        mRatings = new Rating[8];

        for (int i=0; i<8 ; i++){
            mRatings[i] = getRating(mRatingSequence[i]);
        }


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


        float left = 0.5f *mStrokeWidth;
        float top = 0.5f * mStrokeWidth;
        float right =(mBoxWidth/8) - (0.5f *mStrokeWidth) ;
        float bottom = mBoxHeight - (0.5f *mStrokeWidth);
        int i=1;
        canvas.save();
        for (Rating rating: mRatings){


            rating.drawSelf(left, top, right, bottom, canvas);
            canvas.translate(bw + mSpacing, 0);

            i++;

        }
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
    private Rating getRating(int value){

        Rating rating = new Rating(value, mCornerRadius);
        Paint strokePaint = new Paint();
        Paint fillPaint = new Paint();

        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(mStrokeWidth);
        strokePaint.setAntiAlias(true);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);


        switch (value){
            case 1:
                strokePaint.setColor(mOneStarStrokeColor);
                fillPaint.setColor(mOneStarFillColor);
                rating.setmStrokePaint(strokePaint);
                rating.setmFillPaint(fillPaint);
                break;

            case 2:
                strokePaint.setColor(mTwoStarStrokeColor);
                fillPaint.setColor(mTwoStarFillColor);
                rating.setmStrokePaint(strokePaint);
                rating.setmFillPaint(fillPaint);
                break;

            case 3:
                strokePaint.setColor(mThreeStarStrokeColor);
                fillPaint.setColor(mThreeStarFillColor);
                rating.setmStrokePaint(strokePaint);
                rating.setmFillPaint(fillPaint);
                break;

            case 4:
                strokePaint.setColor(mFourStarStrokeColor);
                fillPaint.setColor(mFourStarFillColor);
                rating.setmStrokePaint(strokePaint);
                rating.setmFillPaint(fillPaint);
                break;

            case 5:
                strokePaint.setColor(mFiveStarStrokeColor);
                fillPaint.setColor(mFiveStarFillColor);
                rating.setmStrokePaint(strokePaint);
                rating.setmFillPaint(fillPaint);
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
        invalidate();
    }
}
