package com.haiphon.customviewdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.haiphon.customviewdemo.R;


/**
 * Created by Administrator on 2017/8/5.
 */

public class CircleProgressView extends View {

    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画圆环的画笔背景色
    private Paint mRingPaintBg;
    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 圆环背景颜色
    private int mRingBgColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private float mXCenter;
    // 圆心y坐标
    private float mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private int mProgress;

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    //属性
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TasksCompletedView, 0, 0);
        mCircleColor = typeArray.getColor(R.styleable.TasksCompletedView_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.TasksCompletedView_ringColor, 0xFFFFFFFF);
        mRingBgColor = typeArray.getColor(R.styleable.TasksCompletedView_ringBgColor, 0xFFFFFFFF);
        mStrokeWidth = typeArray.getDimension(R.styleable.TasksCompletedView_strokeWidth, 10);
        mRadius = typeArray.getDimension(R.styleable.TasksCompletedView_radius, 80);
    }

    //初始化画笔
    private void initVariable() {
        //内圆--白色
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        //外圆弧背景--灰白色
        mRingPaintBg = new Paint();
        mRingPaintBg.setAntiAlias(true);
        mRingPaintBg.setColor(mRingBgColor);
        mRingPaintBg.setStyle(Paint.Style.STROKE);
        mRingPaintBg.setStrokeWidth(mStrokeWidth);


        //外圆弧--红色
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);
        //mRingPaint.setStrokeCap(Paint.Cap.ROUND);//设置线冒样式，有圆 有方

        //中间字--红色
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mRingColor);
        mTextPaint.setTextSize(mRadius / 2);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
    }

    //画图
    @Override
    protected void onDraw(Canvas canvas) {
        mXCenter = ((float) getWidth()) / 2;
        mYCenter = ((float) getHeight()) / 2;

        // 把宽和高作比较，拿最小的那个/2 + 1和设置的半径做比较，如果设置的外边框小于设置的半径，则强制把半径设置为(宽或高）/2 - 外半径
        if(getWidth() >= getHeight() && getHeight() / 2  <= mRadius) {
//            mRingRadius = getHeight() / 2 - mStrokeWidth / 2;
            mRingRadius = getHeight() / 2;
            mRadius = getHeight() / 2 - mStrokeWidth;
        } else if(getHeight() >= getWidth() && getWidth() / 2  < mRadius) {
//            mRingRadius = getHeight() / 2 - mStrokeWidth / 2;
            mRingRadius = getHeight() / 2;
            mRadius = getWidth() / 2 - mStrokeWidth;
        } else {
            // 因为画宽度为10的圆弧，是从中间r=5的地方开始画的，他会向两边个扩充5个dp的宽度，最后是10dp的宽度
            mRingRadius = mRadius + mStrokeWidth / 2;
        }

        //内圆
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

        //外圆弧背景
        RectF oval1 = new RectF();
        oval1.left = (mXCenter - mRingRadius);
        oval1.top = (mYCenter - mRingRadius);
        oval1.right = mRingRadius * 2 + oval1.left;
        oval1.bottom = mRingRadius * 2 + oval1.top;
        canvas.drawArc(oval1, 0, 360, false, mRingPaintBg); //圆弧所在的椭圆对象、圆弧的起始角度、圆弧的角度、是否显示半径连线

        //外圆弧
        if (mProgress > 0 ) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
//            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
//            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + oval.left;
            oval.bottom = mRingRadius * 2 + oval.top;

            canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint); //

            //字体
            String txt = mProgress + "分";
            mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
            canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4, mTextPaint);
        }
    }

    //设置进度
    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();//重绘
    }
}