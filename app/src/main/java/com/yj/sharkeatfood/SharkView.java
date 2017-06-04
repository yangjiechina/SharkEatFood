package com.yj.sharkeatfood;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/6/2.
 */

public class SharkView extends ViewGroup {
    private MouthBottom mouthBottom = null;
    private MouthTop mouthTop = null;
    public boolean animationEnd = true;

    public SharkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mouthBottom = new MouthBottom(context, attrs);
        mouthTop = new MouthTop(context, attrs);
        addView(mouthBottom);
        addView(mouthTop);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            childAt.layout(0, 0, 100, 100);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void eatAnimation() {
        animationEnd = mouthBottom.animationEnd && mouthTop.animationEnd;
        if (animationEnd) {
            mouthBottom.RotateAnimtaion();
            mouthTop.RotateAnimtaion();
        } else {
            animationEnd = false;
        }
    }
}
