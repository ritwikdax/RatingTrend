package com.ritwik.ratingtrendlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;



public class RatingTrendView extends View {

    private static final String TAG = "Rating";

    /*Default values*/
    private static final float DEFAULT_STROKE_WIDTH = 3f;
    private static final float DEFAULT_FONT_SIZE = 4f;
    private static final float DEFAULT_SPACING = 12f;

    private static final int DEFAULT_STROKE_COLOR = 0xFF305D02;
    private static final float DEFAULT_CORNER_RADIUS = 10f;




    /***
     * User defined values
     */

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


    private int mTotalWidth;


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
        float actualWidth = (width - (8 * mStrokeWidth) - (7* mSpacing))/8;
        return (int) ((int) (actualWidth / Rating.WIDTH_BY_HEIGHT_RATIO) + getPaddingBottom() + getPaddingTop() + (0.5 * mStrokeWidth));
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

        mTotalWidth = getExpectedSize(defWidth, widthMeasureSpec);

        setMeasuredDimension(getExpectedSize(defWidth, widthMeasureSpec),
                getExpectedSize(defHeight, heightMeasureSpec));
    }

    /***
     * @deprecated  {@link #DEFAULT_FONT_SIZE}
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        if (mRatingSequence == null){
            return;
        }
        int bw = getWidthOfEachRatng();

        canvas.translate(getPaddingLeft(), getPaddingTop());
        canvas.save();


        for (Rating rating : mRatings){

            rating.setmWidth(bw);
            rating.drawSelf(canvas);
            canvas.translate(bw + mSpacing, 0);


        }
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    /***
     * Give appropriate rating obj according to value like 1* 2* 3* etc
     *
     * @param value : must be in range(1,5)
     * @return : Rating obj
     *
     */
    private Rating getRating(int value ){

        Rating rating = new Rating(value, mCornerRadius, mStrokeWidth, getContext());
        //rating.setmStarIcon(mStarIcon);

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
                rating.setmStrokeColor(mFiveStarStrokeColor);
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

    private int getWidthOfEachRatng(){
        int availableWidth = (int) (mTotalWidth - (7*mSpacing) - getPaddingRight() - getPaddingLeft());
        return availableWidth/8;
    }
}
