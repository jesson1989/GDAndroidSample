package yjs.gd.com.gdandroidsample.widget;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import yjs.gd.com.gdandroidsample.utils.ViewUtils;

/**
 * Created by Jesson_Yi on 2017/11/1.
 */

public class PercentView extends View{

    // shader containing repeated waves
    private Matrix matrixCanvas = new Matrix();
    private Camera camera = new Camera();

    private int centerX;
    private int centerY;
    private int circlePadding;
    private int radius;

    private Paint mDashCirclePaint;
    private Paint mArcPaint;

    private float startAngle;
    private float sweepAngle;


    public PercentView(Context context) {
        super(context);
        init();
    }

    public PercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cX = (int) (w / 2f);
        int cY = (int) (h / 2f);
        radius =(Math.min(cX, cY)-circlePadding);
        centerX=cX;
        centerY=cY;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDashPathCircle(canvas);
        drawPercentArcCircle(canvas);
    }
    public  void setAngle(float startAngle,float sweepAngle){
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        invalidate();
    }
    private void init() {

        circlePadding=(int) ViewUtils.dp2px(getContext(),2);

        mDashCirclePaint = new Paint();
        mDashCirclePaint.setStrokeWidth(5);
        mDashCirclePaint.setAntiAlias(true);
        mDashCirclePaint.setColor(Color.BLUE);
        mDashCirclePaint.setStyle(Paint.Style.STROKE);
//        DashPathEffect pathEffect = new DashPathEffect(new float[]{4,15},0);
//        mDashCirclePaint.setPathEffect(pathEffect);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.RED);
        mArcPaint.setStrokeWidth(ViewUtils.dp2px(getContext(),3));
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        mArcPaint.setAntiAlias(true);
    }


    private void drawDashPathCircle(Canvas canvas){
        canvas.drawCircle(centerX, centerY, radius, mDashCirclePaint);
    }

    private void drawPercentArcCircle(Canvas canvas){
        canvas.save();
        rotateCanvas(canvas,centerX,centerY);
        RectF rect=new RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
        canvas.drawArc(rect,startAngle,sweepAngle,false,mArcPaint);
        canvas.restore();
    }

    private void rotateCanvas(Canvas canvas,int centerX,int centerY) {
        matrixCanvas.reset();// 清空Matrix里面的矩阵数值，事实上也就是把这个矩阵重置为单位矩阵

        camera.save();
        camera.rotateX(0);
        camera.rotateY(0);
        camera.rotateZ(90);
        camera.getMatrix(matrixCanvas);//根据camera在不同坐标系的旋转角度获取对应的偏转矩阵。
        camera.restore();

        int matrixCenterX = centerX;
        int matrixCenterY = centerY;
        // This moves the center of the view into the upper left corner (0,0)
        // which is necessary because Matrix always uses 0,0, as it's transform point
        matrixCanvas.preTranslate(-matrixCenterX, -matrixCenterY);//将当前View向上向左移动对应的距离（实际上是移动到了之前View的（0,0）位置处）。
        // This happens after the camera rotations are applied, moving the view
        // back to where it belongs, allowing us to rotate around the center or
        // any point we choose
        matrixCanvas.postTranslate(matrixCenterX, matrixCenterY);//进行移动操作后再把该View移动回原来的位置。

        canvas.concat(matrixCanvas);//将这个矩阵变换作用于当前View所处的画布。
    }
}
