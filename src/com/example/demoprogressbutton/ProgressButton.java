package com.example.demoprogressbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;

public class ProgressButton extends Button {
    public static final int TYPE_FILL = 0;
    public static final int TYPE_STROKE = 1;

    private Paint mPaint = new Paint();
    private int mProgress;
    private int currentType = TYPE_FILL;

    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if(currentType == TYPE_FILL) {
            mPaint.setColor(getContext().getResources().getColor(R.color.green_yellow));
            mPaint.setAntiAlias(true);
            mPaint.setAlpha(128);
            mPaint.setStrokeWidth(1.0f);
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            rect.left += getPaddingLeft();
            rect.top += getPaddingTop();
            rect.right = (rect.left - getPaddingLeft()) + (mProgress * getWidth() / 100) - getPaddingRight();
            rect.bottom -= getPaddingBottom();
            canvas.drawRoundRect(new RectF(rect), 8.0f, 8.0f, mPaint);
        } else if(currentType == TYPE_STROKE) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(getContext().getResources().getColor(R.color.green_yellow));
            mPaint.setAlpha(255);
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            Paint paint1, paint2, paint3, paint4;
            if(mProgress >= 0 && mProgress < 25) {
                paint1 = new Paint(mPaint);
                Rect temp = new Rect(rect.left + getPaddingLeft(),
                        rect.top + getPaddingTop(),
                        rect.left + mProgress * (getWidth() - getPaddingLeft() - getPaddingRight())
                                / 25 - getPaddingRight(),
                        rect.top + getPaddingTop() + 2);
                canvas.drawRect(temp, paint1);
            } else if(mProgress < 50) {
                paint1 = new Paint(mPaint);
                Rect rect1 = new Rect(rect.left + getPaddingLeft(),
                        rect.top + getPaddingTop(), rect.right - getPaddingRight(),
                        rect.top + getPaddingTop() + 2);
                canvas.drawRect(rect1, paint1);

                paint2 = new Paint(mPaint);
                Rect rect2 = new Rect(rect.right - getPaddingRight() - 2,
                        rect.top + getPaddingTop(), rect.right - getPaddingRight(),
                        rect.top + getPaddingTop() + (mProgress - 25) *
                                (getHeight() - getPaddingTop() - getPaddingBottom()) / 25);
                canvas.drawRect(rect2, paint2);
            } else if(mProgress < 75) {
                paint1 = new Paint(mPaint);
                Rect rect1 = new Rect(rect.left + getPaddingLeft(),
                        rect.top + getPaddingTop(), rect.right - getPaddingRight(),
                        rect.top + getPaddingTop() + 2);
                canvas.drawRect(rect1, paint1);

                paint2 = new Paint(mPaint);
                Rect rect2 = new Rect(rect.right - getPaddingRight() - 2,
                        rect.top + getPaddingTop(), rect.right - getPaddingRight(),
                        rect.bottom - getPaddingBottom());
                canvas.drawRect(rect2, paint2);

                paint3 = new Paint(mPaint);
                Rect rect3 = new Rect(
                        rect.right - getPaddingRight() - (mProgress - 50) *
                                (getWidth() - getPaddingLeft() - getPaddingRight()) / 25,
                        rect.bottom - getPaddingBottom() - 2,
                        rect.right - getPaddingRight(),
                        rect.bottom - getPaddingBottom());
                canvas.drawRect(rect3, paint3);
            } else if(mProgress <= 100) {
                paint1 = new Paint(mPaint);
                Rect rect1 = new Rect(
                        rect.left + getPaddingLeft(),
                        rect.top + getPaddingTop(), rect.right - getPaddingRight(),
                        rect.top + getPaddingTop() + 2);
                canvas.drawRect(rect1, paint1);

                paint2 = new Paint(mPaint);
                Rect rect2 = new Rect(
                        rect.right - getPaddingRight() - 2,
                        rect.top + getPaddingTop(), rect.right - getPaddingRight(),
                        rect.bottom - getPaddingBottom());
                canvas.drawRect(rect2, paint2);

                paint3 = new Paint(mPaint);
                Rect rect3 = new Rect(
                        rect.left + getCompoundPaddingLeft(),
                        rect.bottom - getPaddingBottom() - 2, rect.right - getPaddingRight(),
                        rect.bottom - getPaddingRight());
                canvas.drawRect(rect3, paint3);

                paint4 = new Paint(mPaint);
                Rect rect4 = new Rect(
                        rect.left + getCompoundPaddingLeft(),
                        rect.bottom - getPaddingBottom() - (mProgress - 75) *
                                (getHeight() - getPaddingTop() - getPaddingBottom()) / 25,
                        rect.left + getPaddingLeft() + 2,
                        rect.bottom - getPaddingBottom());
                canvas.drawRect(rect4, paint4);
            }
        }

        super.onDraw(canvas);
    }

    public void updateProgress(int progress) {
        if(progress >= 0 && progress <= 100) {
            mProgress = progress;
            invalidate();
        } else if(progress < 0) {
            mProgress = 0;
            invalidate();
        } else if(progress > 100) {
            mProgress = 100;
            invalidate();
        }
    }

    public void setType(int type) {
        if(type == TYPE_FILL || type == TYPE_STROKE)
            currentType = type;
        else
            currentType = TYPE_FILL;
    }
}
