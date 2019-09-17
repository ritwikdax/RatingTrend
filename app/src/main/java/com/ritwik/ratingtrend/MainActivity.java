package com.ritwik.ratingtrend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ritwik.ratingtrendlib.RatingTrendView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RatingTrendView rtv = findViewById(R.id.rtv_ratingTrend);
        rtv.setRatingSequence(new int[]{1,2, 3,4,5,1,2,3});

        RatingTrendView rtv1 = findViewById(R.id.rtv_ratingTrend1);
        rtv1.setRatingSequence(new int[]{5,4, 3,2,1});
    }
}
