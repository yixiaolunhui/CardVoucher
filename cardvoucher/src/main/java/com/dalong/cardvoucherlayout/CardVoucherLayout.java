package com.dalong.cardvoucherlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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

    public final static int SHAPE_CIRCLE=0;//圆形

    public final static int SHAPE_RECTANGLE=1;//矩形

    public final static int SHAPE_TRIANGLE=2;//三角

    public final static int SHAPE_ELLIPSE=3;//椭圆

    private float mRadius;//半径

    private float mSpacing;//间距

    private int mColor;//颜色

    private int mOrientation=Orientation_HORIZONTAL;//方向

    private int mShape=SHAPE_CIRCLE;// 形状

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
        mShape=typedArray.getInt(R.styleable.CardVoucherLayout_CardVoucher_Shape,SHAPE_CIRCLE);
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
        switch (mShape){
            case SHAPE_CIRCLE:
                drawHorizontalCircle(canvas);
                break;
            case SHAPE_RECTANGLE:
                drawHorizontalRectangle(canvas);
                break;
            case SHAPE_TRIANGLE:
                drawHorizontalTriangle(canvas);
                break;
            case SHAPE_ELLIPSE:
                drawHorizontalEllipse(canvas);
                break;
        }

    }

    /**
     * 绘制水平的椭圆形
     * @param canvas
     */
    private void drawHorizontalEllipse(Canvas canvas) {
        for (int i=0;i<mHorizontalNum;i++){
            float x = mSpacing+mRadius+mResidual_Horizontal_Distance/2+((mSpacing+mRadius*2)*i);
            // 定义椭圆对象
            RectF mRectF = new RectF();
            // 设置椭圆4点
            mRectF.left =x-mRadius;
            mRectF.right = x+mRadius;
            mRectF.top = 0;
            mRectF.bottom = mRadius;
            // 绘制上面 椭圆
            canvas.drawOval(mRectF, mPaint);

            //绘制下面 椭圆 下面和上面的left和right是一样的所以只需要修改top 和bottom就可以了
            mRectF.top = getHeight()-mRadius;
            mRectF.bottom = getHeight();
            canvas.drawOval(mRectF, mPaint);
        }
    }

    /**
     * 绘制水平的三角形
     * @param canvas
     */
    private void drawHorizontalTriangle(Canvas canvas) {
        for (int i=0;i<mHorizontalNum;i++){
            float x = mSpacing+mRadius+mResidual_Horizontal_Distance/2+((mSpacing+mRadius*2)*i);
            // 绘制三角形
            Path path = new Path();
            // 绘制上边  三角形的三个点  左面点(x-mRadius,0)   右面点(x+mRadius,0)   下面点(x, mRadius)  然后再回去封闭
            path.moveTo(x-mRadius,0);
            path.lineTo(x+mRadius,0);
            path.lineTo(x, mRadius);
            path.lineTo(x-mRadius,0);
            path.close();
            canvas.drawPath(path,mPaint);

            //绘制下边  绘制上边  三角形的三个点  左面点(x-mRadius,getHeight())   右面点(x+mRadius,getHeight())   上面点(x,getHeight()-mRadius)  然后再回去封闭
            path.moveTo(x-mRadius,getHeight());
            path.lineTo(x+mRadius,getHeight());
            path.lineTo(x,getHeight()-mRadius);
            path.lineTo(x-mRadius,getHeight());
            path.close();
            canvas.drawPath(path,mPaint);
        }
    }

    /**
     * 绘制水平的矩形
     * @param canvas
     */
    private void drawHorizontalRectangle(Canvas canvas) {
        for (int i=0;i<mHorizontalNum;i++){
            float x = mSpacing+mRadius+mResidual_Horizontal_Distance/2+((mSpacing+mRadius*2)*i);
            // 绘制矩形
            Path path = new Path();
            // 绘制上边  矩形的4个点  (x-mRadius,0),(x+mRadius,0),(x+mRadius,mRadius),(x-mRadius, mRadius)
            path.moveTo(x-mRadius,0);
            path.lineTo(x+mRadius,0);
            path.lineTo(x+mRadius,mRadius);
            path.lineTo(x-mRadius, mRadius);
            path.lineTo(x-mRadius,0);
            path.close();
            canvas.drawPath(path,mPaint);

            // 绘制上边  矩形的4个点  (x-mRadius,getHeight()),(x+mRadius,getHeight()),(x+mRadius,getHeight()-mRadius),(x-mRadius,getHeight()-mRadius)
            path.moveTo(x-mRadius,getHeight());
            path.lineTo(x+mRadius,getHeight());
            path.lineTo(x+mRadius,getHeight()-mRadius);
            path.lineTo(x-mRadius,getHeight()-mRadius);
            path.lineTo(x-mRadius,getHeight());
            path.close();
            canvas.drawPath(path,mPaint);
        }
    }

    /**
     * 绘制水平圆形
     * @param canvas
     */
    private void drawHorizontalCircle(Canvas canvas){
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
        switch (mShape){
            case SHAPE_CIRCLE:
                drawVerticalCircle(canvas);
                break;
            case SHAPE_RECTANGLE:
                drawVerticalRectangle(canvas);
                break;
            case SHAPE_TRIANGLE:
                drawVerticalTriangle(canvas);
                break;
            case SHAPE_ELLIPSE:
                drawVerticalEllipse(canvas);
                break;
        }
    }
    /**
     * 绘制竖直椭圆形
     * @param canvas
     */
    private void drawVerticalEllipse(Canvas canvas) {
        for (int i=0;i<mVerticalNum;i++){
            float y = mSpacing+mRadius+mResidual_Vertical_Distance/2+((mSpacing+mRadius*2)*i);
            // 定义椭圆对象
            RectF mRectF = new RectF();
            // 设置椭圆四个点
            mRectF.left =0;
            mRectF.right = mRadius;
            mRectF.top = y-mRadius;
            mRectF.bottom = y+mRadius;
            // 绘制左边 椭圆
            canvas.drawOval(mRectF, mPaint);

            // 绘制右边  椭圆 右边top和bottom与左面一样不需要修改的
            mRectF.left = getWidth()-mRadius;
            mRectF.right = getWidth();
            canvas.drawOval(mRectF, mPaint);
        }

    }
    /**
     * 绘制竖直三角形
     * @param canvas
     */
    private void drawVerticalTriangle(Canvas canvas) {

        for (int i=0;i<mVerticalNum;i++){
            float y = mSpacing+mRadius+mResidual_Vertical_Distance/2+((mSpacing+mRadius*2)*i);
            // 绘制三角形
            Path path = new Path();
            // 绘制左边  三角形的3个点  (0,y-mRadius),(0,y+mRadius),(mRadius,y)
            path.moveTo(0,y-mRadius);
            path.lineTo(0,y+mRadius);
            path.lineTo(mRadius,y);
            path.lineTo(0,y-mRadius);
            path.close();
            canvas.drawPath(path,mPaint);

            //绘制右边  三角形的3个点   (getWidth(),y-mRadius),(getWidth(),y+mRadius),(getWidth()-mRadius,y)
            path.moveTo(getWidth(),y-mRadius);
            path.lineTo(getWidth(),y+mRadius);
            path.lineTo(getWidth()-mRadius,y);
            path.lineTo(getWidth(),y-mRadius);
            path.close();
            canvas.drawPath(path,mPaint);
        }
    }
    /**
     * 绘制竖直矩形
     * @param canvas
     */
    private void drawVerticalRectangle(Canvas canvas) {
        for (int i=0;i<mVerticalNum;i++){
            float y = mSpacing+mRadius+mResidual_Vertical_Distance/2+((mSpacing+mRadius*2)*i);
            // 绘制矩形
            Path path = new Path();
            // 绘制左边  矩形的4个点  (0,y-mRadius),(mRadius,y-mRadius),(mRadius,y+mRadius),(0,y-mRadius)
            path.moveTo(0,y-mRadius);
            path.lineTo(mRadius,y-mRadius);
            path.lineTo(mRadius,y+mRadius);
            path.lineTo(0,y+mRadius);
            path.lineTo(0,y-mRadius);
            path.close();
            canvas.drawPath(path,mPaint);

            // 绘制右边  矩形的4个点  (getWidth(),y-mRadius),(getWidth()-mRadius,y-mRadius),(getWidth()-mRadius,y+mRadius),(getWidth(),y+mRadius)
            path.moveTo(getWidth(),y-mRadius);
            path.lineTo(getWidth()-mRadius,y-mRadius);
            path.lineTo(getWidth()-mRadius,y+mRadius);
            path.lineTo(getWidth(),y+mRadius);
            path.lineTo(getWidth(),y-mRadius);
            path.close();
            canvas.drawPath(path,mPaint);
        }
    }


    /**
     * 绘制竖直圆形
     * @param canvas
     */
    private void drawVerticalCircle(Canvas canvas){
        for (int i=0;i<mVerticalNum;i++){
            // 垂直方向 x值不变 为0和布局的宽度2个   y坐标为动态计算  间距+计算好的剩余部分/2+半径+第几个*(间距加上直径)
            float y = mSpacing+mRadius+mResidual_Vertical_Distance/2+((mSpacing+mRadius*2)*i);
            canvas.drawCircle(0,y,mRadius,mPaint);//左面
            canvas.drawCircle(getWidth(),y,mRadius,mPaint);//右面
        }
    }
}
