package com.haiphon.customviewdemo.customview;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.haiphon.customviewdemo.R;


/**
 * Created by Miller Zhang  on 2017/2/22.
 * desc:
 * github:https://github.com/zxyaust  CSDN:http://blog.csdn.net/ZRationImageView Whatever happens tomorrow,we've had today.
 */

public class ZRationImageView extends ImageView {

    private float ration;

    public ZRationImageView(Context context) {
        this(context, null);
    }

    public ZRationImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZRationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ZRationImageView);
        ration = array.getFloat(R.styleable.ZRationImageView_ration, 0f);
        array.recycle();
        setScaleType(ScaleType.FIT_XY);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int width = this.getWidth();
        int height = (int) (width * ration);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        setLayoutParams(layoutParams);
    }
}
