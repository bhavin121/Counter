package com.example.counterwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;

/**
 * It is an custom view
 * in Test branch
 */
public class Counter extends View {

    private OnChangeListener onChangeListener;
    private OnZeroListener onZeroListener;
    private int counterValue = 0;
    private int buttonColor = Color.BLUE;
    private int textColor = Color.WHITE;
    private int cornerRadius = 10;
    private int shadowColor = Color.GRAY;
    private int shadowDx = 0;
    private int shadowDy = 0;
    private int shadowRadius = 0;
    private int padding = 0;
    private int paddingLeft = 0;
    private int paddingRight = 0;
    private int paddingTop = 0;
    private int paddingBottom = 0;
    private Context context;
    private RectF rectF, rectF1;
    private Paint buttonPaint;
    private Paint textPaint;
    private Paint textBackground;

    public Counter(Context context) {
        super(context);
        this.context = context;
    }

    public Counter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray array = context.obtainStyledAttributes(
                attrs,
                R.styleable.Counter,
                0,0
        );

        try {
            buttonColor = array.getColor(R.styleable.Counter_buttonColor, Color.BLUE);
            textColor = array.getColor(R.styleable.Counter_textColor, Color.WHITE);
            cornerRadius = array.getDimensionPixelSize(R.styleable.Counter_cornerRadius, 15);
            shadowColor = array.getColor(R.styleable.Counter_android_shadowColor, Color.GRAY);
            shadowDx = array.getDimensionPixelSize(R.styleable.Counter_shadowDx, 0);
            shadowDy = array.getDimensionPixelSize(R.styleable.Counter_shadowDy, 0);
            shadowRadius = array.getDimensionPixelSize(R.styleable.Counter_shadowRadius, 0);
            padding = array.getDimensionPixelSize(R.styleable.Counter_contentPadding, 0);
            if(padding != 0){
                paddingBottom = padding;
                paddingLeft = padding;
                paddingRight = padding;
                paddingTop = padding;
            }else{
                paddingLeft = array.getDimensionPixelSize(R.styleable.Counter_contentPaddingLeft, 0);
                paddingRight = array.getDimensionPixelSize(R.styleable.Counter_contentPaddingRight, 0);
                paddingTop = array.getDimensionPixelSize(R.styleable.Counter_contentPaddingTop, 0);
                paddingBottom = array.getDimensionPixelSize(R.styleable.Counter_contentPaddingBottom, 0);
            }
        } finally {
            array.recycle();
        }

        rectF = new RectF();
        rectF1 = new RectF();
        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setColor(buttonColor);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        textBackground.setColor(textColor);
        textBackground.setAlpha(80);
        buttonPaint.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
        setLayerType(LAYER_TYPE_SOFTWARE, buttonPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int effHeight = getHeight() - paddingTop - paddingBottom;
        int effWidth = getWidth() - paddingLeft - paddingRight;

        rectF1.set(0, 0, effHeight-20, effHeight-20);
        rectF1.offset(effWidth/2f-effHeight/2f+10+paddingLeft, 10+paddingTop);
        rectF.set(0, 0, effWidth, effHeight);
        rectF.offset(paddingLeft, paddingTop);
        textPaint.setTextSize(0.6f*effHeight);

        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, buttonPaint);
        canvas.drawRoundRect(rectF1, cornerRadius, cornerRadius, textBackground);
        canvas.drawText(String.valueOf(counterValue),effWidth/2f+paddingLeft, effHeight/2f + 0.2f*effHeight + paddingTop, textPaint);
        canvas.drawText("-",effWidth/6f + paddingLeft, effHeight/2f + 0.2f*effHeight +paddingTop, textPaint);
        canvas.drawText("+",5f*effWidth/6f + paddingLeft, effHeight/2f + 0.2f*effHeight + paddingTop, textPaint);

        //canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/4, buttonPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
            if(event.getX() <= getWidth()/3f){
                if(counterValue == 1){
                    counterValue = 0;
                    if(onZeroListener != null){
                        onZeroListener.onZero();
                    }
                    if(onChangeListener != null){
                        onChangeListener.onChange(counterValue);
                    }
                    invalidate();
                }else if(counterValue>0){
                    counterValue--;
                    invalidate();
                    if(onChangeListener != null){
                        onChangeListener.onChange(counterValue);
                    }
                }else{
                    Toast.makeText(context, "Min Value", Toast.LENGTH_SHORT).show();
                }
            }else if(event.getX() >= getWidth()*2f/3){
                counterValue++;
                invalidate();
                if(onChangeListener != null){
                    onChangeListener.onChange(counterValue);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
    }

    public int getShadowDx() {
        return shadowDx;
    }

    public void setShadowDx(int shadowDx) {
        this.shadowDx = shadowDx;
    }

    public int getShadowDy() {
        return shadowDy;
    }

    public void setShadowDy(int shadowDy) {
        this.shadowDy = shadowDy;
    }

    public int getShadowRadius() {
        return shadowRadius;
    }

    public void setShadowRadius(int shadowRadius) {
        this.shadowRadius = shadowRadius;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    @Override
    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    @Override
    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    @Override
    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    @Override
    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public int getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(int buttonColor) {
        this.buttonColor = buttonColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public int getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(int counterValue) {
        this.counterValue = counterValue;
    }

    public void setOnChangeListener(OnChangeListener onChangeListener){
        this.onChangeListener = onChangeListener;
    }

    public void setOnZeroListener(OnZeroListener onZeroListener) {
        this.onZeroListener = onZeroListener;
    }
}
