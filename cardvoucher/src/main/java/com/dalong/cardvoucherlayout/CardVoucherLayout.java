package com.dalong.cardvoucherlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 卡券布局
 * Created by zwl on 16/6/20.
 */

public class CardVoucherLayout extends LinearLayout {

    public final static int Orientation_HORIZONTAL=0;//水平

    public final static int Orientation_VERTICAL=1;// 竖直

    public final static int Orientation_ALL=2;// 竖直

    private float mRadius;//半径

    private float mSpacing;//间距

    private int mColor;//颜色

    private int mOrientation=Orientation_HORIZONTAL;//方向

    private Paint mPaint;//画笔

    private float mHorizontalNum;//计算的水平的数量

    private float mVerticalNum;//计算的竖直的数量

    private float mResidual_Horizontal_Distance;//计算数量之后剩余的水平距离

    private float mResidual_Vertical_Distance;//计算数量之后剩余的竖直距离



    public CardVoucherLayout(Context context) {
        this(context,null);
    }

    public CardVoucherLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CardVoucherLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CardVoucherLayout);
        mRadius=typedArray.getFloat(R.styleable.CardVoucherLayout_CardVoucher_Radius,8);
        mSpacing=typedArray.getFloat(R.styleable.CardVoucherLayout_CardVoucher_Spacing,8);
        mColor=typedArray.getColor(R.styleable.CardVoucherLayout_CardVoucher_Color, Color.WHITE);
        mOrientation=typedArray.getInt(R.styleable.CardVoucherLayout_CardVoucher_Orientation,Orientation_HORIZONTAL);
        typedArray.recycle();
        init();
    }

    /**
     * 初始化画笔设置
     */
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setColor(mColor);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        switch (mOrientation){
            case Orientation_HORIZONTAL:// 水平方向
                measureHorizontalNum(w);
                break;
            case Orientation_VERTICAL:// 竖直方向
                measurehVerticalNum(h);
                break;
            case Orientation_ALL: // 水平加竖直
                measurehVerticalNum(h);
                measureHorizontalNum(w);
                break;
        }

    }

    /**
     * 测量水平的数量
     * @param w
     */
    private void measureHorizontalNum(int w) {
        //(宽度-一个间距)对(直径+对应一个间距)取余剩下的就是多余的间距
        mResidual_Horizontal_Distance=(w-mSpacing)%(mRadius*2+mSpacing);
        //宽度减去多余的一个间距 (因为每个圆之间有个间距,其实圆比间距多一个,所以要减去一个) 然后把一个半圆和一个间距看成一整体取整
        mHorizontalNum=(int)((w-mSpacing)/(mRadius*2+mSpacing));
    }


    /**
     * 测量垂直的数量
     * @param h
     */
    private void measurehVerticalNum(int h) {
        //(高度-一个间距)对(直径+对应一个间距)取余剩下的就是多余的间距
        mResidual_Vertical_Distance=(h-mSpacing)%(mRadius*2+mSpacing);
        //高度减去多余的一个间距 (因为每个圆之间有个间距,其实圆比间距多一个,所以要减去一个) 然后把一个半圆和一个间距看成一整体取整
        mVerticalNum=(int)((h-mSpacing)/(mRadius*2+mSpacing));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mOrientation){
            case Orientation_HORIZONTAL:// 水平方向
                drawHorizontal(canvas);
                break;
            case Orientation_VERTICAL:// 竖直方向
                drawVertical(canvas);
                break;
            case Orientation_ALL: // 水平加竖直
                drawHorizontal(canvas);
                drawVertical(canvas);
                break;
        }
    }


    /**
     * 绘制水平的图形
     * @param canvas
     */
    private void drawHorizontal(Canvas canvas) {
        for (int i=0;i<mHorizontalNum;i++){
            // 水平方向 y值不变 为0和布局的高度2个   x坐标为动态计算  间距+计算好的剩余部分/2+半径+第几个*(间距加上直径)
            float x = mSpacing+mRadius+mResidual_Horizontal_Distance/2+((mSpacing+mRadius*2)*i);
            canvas.drawCircle(x,0,mRadius,mPaint);//上面
            canvas.drawCircle(x,getHeight(),mRadius,mPaint);//下面
        }
    }


    /**
     * 绘制垂直的图形
     * @param canvas
     */
    private void drawVertical(Canvas canvas) {
        for (int i=0;i<mVerticalNum;i++){
            // 垂直方向 x值不变 为0和布局的宽度2个   y坐标为动态计算  间距+计算好的剩余部分/2+半径+第几个*(间距加上直径)
            float y = mSpacing+mRadius+mResidual_Vertical_Distance/2+((mSpacing+mRadius*2)*i);
            canvas.drawCircle(0,y,mRadius,mPaint);//左面
            canvas.drawCircle(getWidth(),y,mRadius,mPaint);//右面
        }
    }
}
