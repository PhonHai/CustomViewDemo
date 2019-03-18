package com.haiphon.customviewdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.haiphon.customviewdemo.R;
import com.haiphon.customviewdemo.utils.DensityUtils;

public class CircleView extends View {
    private Paint mPaintCircle;
    private Paint mPaintText;
    private int circleColor;
    private String circleText;
    private float circleTextSize;
    private int circleTextColor;
    /* 测量小时文本宽高的矩形 */
    private Rect mTextRect = new Rect();
    private Context mContext;


    public CircleView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        circleColor = typedArray.getColor(R.styleable.CircleView_circle_color, Color.RED);

        circleText = typedArray.getString(R.styleable.CircleView_circle_text);
        circleTextSize = typedArray.getDimension(R.styleable.CircleView_circle_textSize, DensityUtils.sp2px(context, 14));
        circleTextColor = typedArray.getColor(R.styleable.CircleView_circle_textColor, Color.BLACK);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(circleColor);

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置只绘制图形内容
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(circleTextColor);
        //居中绘制文字
        mPaintText.setTextSize(circleTextSize);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setColor(Color.YELLOW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        // 这里设置的是px，所以要先将dp转换成px
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DensityUtils.dp2px(mContext, 200), DensityUtils.dip2px(mContext, 200));
        } else if(widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DensityUtils.dip2px(mContext, 200), heightSpecSize);
        } else if(heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, DensityUtils.dip2px(mContext, 200));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        // 画一个矩形，矩形的两个点是(0，0)   和  (宽度+左右边距，高度+上下边距)
        canvas.drawRect(new Rect(0,0,getWidth() + getPaddingLeft() + getPaddingRight(), getHeight() + getPaddingTop() + getPaddingBottom()), mPaintText);
        // 半径
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(width / 2 + getPaddingLeft(), height / 2 + getPaddingTop(), radius, mPaintCircle);
        // 计算字体所在外围的矩形的左右上下的距离。
        mPaintText.getTextBounds(circleText, 0, circleText.length(), mTextRect);
        canvas.drawText(circleText, width / 2 + getPaddingLeft(), height / 2 + getPaddingTop()- mTextRect.top / 2 , mPaintText);
    }
}
