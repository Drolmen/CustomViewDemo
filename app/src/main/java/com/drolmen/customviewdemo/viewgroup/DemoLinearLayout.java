package com.drolmen.customviewdemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class DemoLinearLayout extends ViewGroup {
    public DemoLinearLayout(Context context) {
        super(context);
    }

    public DemoLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DemoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
