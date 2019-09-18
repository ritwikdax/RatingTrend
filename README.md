# RatingTrend
Inspired from Zomato rating trend view.

![Library](https://github.com/devritwik/RatingTrend/blob/master/Rating%20trend%20view.jpg)

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

## Some Constraints
1.  Rating value must be in range(1,5)
2.  Atmost 8 sequence can be shown

## Customization Options :open_mouth:

| Attribute | Description |
| --- | --- |
| rtv_oneStarFillColor | Fill or background colour of 1 STAR rating box |
| rtv_oneStarStrokeColor | Border or Stroke color of 1 STAR rating box |
| rtv_twoStarFillColor | Fill or background colour of 2 STAR rating box |
| rtv_twoStarStrokeColor | Border or Stroke color of 2 STAR rating box |
| rtv_threeStarFillColor | Fill or background colour of 3 STAR rating box |
| rtv_threeStarStrokeColor | Border or Stroke color of 3 STAR rating box |
| rtv_fourStarFillColor | Fill or background colour of 4 STAR rating box |
| rtv_fourStarStrokeColor | Border or Stroke color of 4 STAR rating box |
| rtv_fiveStarFillColor | Fill or background colour of 5 STAR rating box |
| rtv_fiveStarStrokeColor | Border or Stroke color of 5 STAR rating box |
| rtv_starIcon | If you want to change the default STAR ICON|
| rtv_spacing | Define spacing between 2 consecutive Rating Box |
| rtv_strokeWidth | Modify the border width of each Rating Box |
| rtv_cornerRadius | Define corner roundness in each Rating Box |



