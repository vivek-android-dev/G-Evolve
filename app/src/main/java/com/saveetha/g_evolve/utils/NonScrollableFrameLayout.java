package com.saveetha.g_evolve.utils;

 import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
 import android.widget.FrameLayout;

 import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class NonScrollableFrameLayout extends FrameLayout {

    public NonScrollableFrameLayout(@NonNull Context context) {
        super(context);
    }

    public NonScrollableFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollableFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Always return false to prevent the parent view from handling the touch events
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Handle the touch event here if needed
        return super.onTouchEvent(event);
    }
}

