package com.yj.sharkeatfood;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */

public class FoodView extends View {
    private Paint mFoodPaint = null;
    public List<Point> pointList = null;
    private int average = 0;
    private boolean first = true;
    public FoodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mFoodPaint = new Paint();
        mFoodPaint.setColor(Color.WHITE);
        pointList = new ArrayList<>();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        average = (getMeasuredWidth() - 100) / 15;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!first) {
            pointList.remove(0);
            for(int i = 0 ;i <pointList.size();i++){
                canvas.drawCircle(pointList.get(i).x, 10, 10, mFoodPaint);
            }
        } else {
            for (int i = 1; i <= 19; i++) {
                int distance = average * i;
                canvas.drawCircle(distance + 100, 10, 10, mFoodPaint);
                Point point = new Point();
                point.set(distance, 0);
                pointList.add(point);
            }
            first = false;
        }
    }
}
