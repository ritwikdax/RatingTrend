# RatingTrend
Inspired from Zomato rating trend view.

## How to install?

**Step 1.**  Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}


```
**Step 2.** Add the dependency

```
dependencies {

	implementation 'com.github.devritwik:RatingTrend:v1.0'
}
```

## How to use?
1. Insert this code in your activity_main.xml file
Always make sure that the width is `match_parent` and height is `wrap_content`
Padding or margin may be as per your choice.:astonished:

```
 <com.ritwik.ratingtrendlib.RatingTrendView
	android:padding="8dp"
        android:id="@+id/rtv_ratingTrend"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
```

2. Then add this code in your MainActivity.java file.

```
RatingTrendView rtv = findViewById(R.id.rtv_ratingTrend);
rtv.setRatingSequence(new int[]{1,2, 3,4,5,1,2,3});
```
