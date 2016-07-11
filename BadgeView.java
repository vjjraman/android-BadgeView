package com.vijay;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by AV JAYARAMAN on 11-Jul-16.
 */
public class BadgeView extends ImageView {
    private int circleCol, labelCol;
    private String badgeText;
    private Paint textPaint;
    private Paint badgePaint;
    private Rect textBounds;
    private int mGestureThreshold;
    private static final float GESTURE_THRESHOLD_DP = 16.0f; // The gesture threshold expressed in dp

    /*Constructor*/
    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final float scale = getResources().getDisplayMetrics().density; // Get the screen's density scale
        mGestureThreshold = (int) (GESTURE_THRESHOLD_DP * scale + 0.5f); // Convert the dps to pixels, based on density scale

        textBounds = new Rect();
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(mGestureThreshold);
        badgePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        badgePaint.setStyle(Paint.Style.FILL);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BadgeView, 0, 0);
        try {
            if (a.hasValue(R.styleable.BadgeView_badgeLabel)) {
                badgeText = a.getString(R.styleable.BadgeView_badgeLabel);
            } else {
                badgeText = "";
            }
            circleCol = a.getInteger(R.styleable.BadgeView_badgeColor, 0);
            labelCol = a.getInteger(R.styleable.BadgeView_labelColor, 0);
        } finally {
            a.recycle();
        }

    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = this.getMeasuredHeight() / 2;
        int textX = viewWidthHalf + viewWidthHalf / 2;
        int textY = viewHeightHalf - viewHeightHalf / 2;
        /*
        Important note: While drawing Text(canvas.drawText), the text is aligned in such a way that the center bottom
                        takes the coordinates given which is corrected by the  "textErrorFix"

        */

        textPaint.setColor(labelCol);
        badgePaint.setColor(circleCol);
        textPaint.getTextBounds(badgeText, 0, badgeText.length(), textBounds); // defines the bounds of the text given to a predefine textBoundsangle
        int textErrorFix = textBounds.height() / 2;

        //if number of characters is one then draw a circle of radius equivalent to text bound
        if (badgeText.length() > 0 && badgeText.length() < 2) {
            float radius = textBounds.height();
            canvas.drawCircle(viewWidthHalf + (viewWidthHalf / 2), viewHeightHalf - (viewHeightHalf / 2), radius, badgePaint);
        } else if (badgeText.length() >= 2) {
            float left = viewWidthHalf + (viewWidthHalf / 2) - (textBounds.width() / 2) - 10;
            float top = viewHeightHalf - (viewHeightHalf / 2) - (textBounds.height() / 2) - 10;
            float right = viewWidthHalf + (viewWidthHalf / 2) + (textBounds.width() / 2) + 10;
            float bottom = viewHeightHalf - (viewHeightHalf / 2) + (textBounds.height() / 2) + 10;

            canvas.drawRoundRect(new RectF(left, top, right, bottom), 50, 50, badgePaint);

        }
        canvas.drawText(badgeText, textX, textY + textErrorFix, textPaint);


    }

    /*Getters*/
    public int getCirleColor() {
        return circleCol;
    }

    public int getLabelCol() {
        return labelCol;
    }

    public String getLabelText() {
        return badgeText;
    }

    /*Setters*/
    public void setCircleColor(int newColor) {
        //update the instance variable
        circleCol = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelColor(int newColor) {
        //update the instance variable
        labelCol = newColor;
        invalidate();
        requestLayout();
    }

    public void setLabelText(String newLabel) {
        //update the instance variable
        badgeText = newLabel;
        invalidate();
        requestLayout();
    }
}
