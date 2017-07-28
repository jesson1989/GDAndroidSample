package yjs.gd.com.gdandroidsample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import yjs.gd.com.gdandroidsample.R;

/**
 * Created by Jesson_Yi on 2017/7/21.
 */

public class XfermodeView extends View {


    private Bitmap preBitmap,proBitmap;
    private Paint  mPaint;
    private Path mPath;
    private Canvas mCanvas;
    private int SCREEN_W;
    private int SCREEN_H;



    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setScreenWH();
        proBitmap=scaleBitmapFillScreen(BitmapFactory.decodeResource(getResources(), R.drawable.gril_l));

        Bitmap temp_Bitmap= scaleBitmapFillScreen(BitmapFactory.decodeResource(getResources(), R.drawable.gril_c));
        preBitmap=Bitmap.createBitmap(temp_Bitmap.getWidth(),temp_Bitmap.getHeight(), Bitmap.Config.ARGB_8888);


        mCanvas= new Canvas(preBitmap);
        mPaint=new Paint();
        mPaint.setStrokeWidth(50);
        mPaint.setStyle(Paint.Style.STROKE);
        //设置结合处为圆弧
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        //设置画笔为圆弧状
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置透明度为0
        mPaint.setAlpha(255);
        mPaint.setXfermode( new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        //这里要说的一点是，两张图片进行相交合并时，不管是显示底图还是显示上层图。实事上都需要进行一个Alpha的互相迭代计算过程。
        // 这里使用DST_IN模式，乍看，会以为是显示有衣服的图片，其实这里ALpha的值为0，所以
        //有衣服的图片也显示不出来了的。
        //同样这里如果用DST_OUT做的话，这里使用Alpha为255也能实现这一点。
        //当然针对这个功能来说直接使用CLEAR模式是最简单不过
        //PS:在使用PorterDuffXfermode 进行图层混合时，并不是简单的只进行图层的计算，同时也会去计算透明通道的值。才形成这样的效果。

        mCanvas.drawBitmap(temp_Bitmap,0,0,null);
        mPath = new Path() ;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float x = event.getX();
        final float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(proBitmap,0,0,null);
        canvas.drawBitmap(preBitmap,0,0,null);
    }


    private void setScreenWH() {
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getResources().getDisplayMetrics();
        //获取屏幕宽度
        int screenWidth = dm.widthPixels;
        //获取屏幕高度
        int screenHeight = dm.heightPixels;

        SCREEN_W = screenWidth;
        SCREEN_H = screenHeight;
    }
    private Bitmap scaleBitmapFillScreen(Bitmap bm) {
        return Bitmap.createScaledBitmap(bm, SCREEN_W, SCREEN_H, true);
    }
}
