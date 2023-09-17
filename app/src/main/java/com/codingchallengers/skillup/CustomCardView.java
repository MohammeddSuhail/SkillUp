package com.codingchallengers.skillup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CustomCardView extends FrameLayout {
    public CustomCardView(Context context) {
        super(context);
        init();
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Set the background and other properties of the CardView here
        setBackgroundResource(R.drawable.cardview_background); // Use your custom background drawable
//        setElevation(getResources().getDimensionPixelSize(R.dimen.card_elevation));
        // You can also set padding, corner radius, etc. here
    }
}
