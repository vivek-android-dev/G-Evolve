package com.example.g_evolve.customviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NonScrollableViewPager extends ViewPager {

    public NonScrollableViewPager(@NonNull Context context) {
        super(context);
    }

    public NonScrollableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Disable touch event
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Disable intercepting touch event
        return false;
    }
}
