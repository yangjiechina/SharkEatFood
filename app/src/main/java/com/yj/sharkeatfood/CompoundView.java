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
import android.view.animation.AnimationUtils;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/6/2.
 */

public class CompoundView extends ViewGroup {
    private FoodView foodView = null;
    private SharkView sharkView = null;
    private int distance = 0;

    public CompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        foodView = new FoodView(context, attrs);
        sharkView = new SharkView(context, attrs);
        addView(foodView);
        addView(sharkView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }
        distance = this.getMeasuredWidth()-100;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt == foodView) {
                childAt.layout(0, 250, getMeasuredWidth(), 300);
            } else {
                childAt.layout(0, 200, getMeasuredWidth(), 300);
            }
        }
        setTranslationAnimation();
    }

    private void setTranslationAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(8000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                int average = (int) (distance * animatedFraction);
                int scrollX = Math.abs(sharkView.getScrollX());
                if(foodView.pointList.size()>0 && scrollX+100 >= foodView.pointList.get(0).x-10){
                    sharkView.eatAnimation();
                    foodView.invalidate();
                }else if(sharkView.animationEnd){
                    sharkView.scrollTo(-average, 0);
                }
            }
        });
        animator.start();
    }
}
