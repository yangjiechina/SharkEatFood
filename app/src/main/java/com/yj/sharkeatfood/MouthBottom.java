package com.yj.sharkeatfood;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by Administrator on 2017/6/2.
 */

public class MouthBottom extends View {
    private Paint mMouthPaint = null;
    public boolean animationEnd = true;

    public MouthBottom(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMouthPaint = new Paint();
        mMouthPaint.setColor(Color.WHITE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(0, 0, 100, 100, 160, 180, true, mMouthPaint);
    }

    public void RotateAnimtaion() {
        RotateAnimation animation = new RotateAnimation(0, 20, 50, 50);
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationEnd = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationEnd = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        this.setAnimation(animation);
        animation.start();
    }

}
