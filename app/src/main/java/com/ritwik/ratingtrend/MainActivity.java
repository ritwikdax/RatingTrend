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
        rtv.setRatingSequence(new int[]{4,1,5,4,1,3,5,1});
    }
}
