package yjs.gd.com.gdandroidsample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Jesson_Yi on 2017/7/18.
 */

public class PointView extends View {

    // +++ circle spot
    private static final int MASK_COLOR = 0xC0000000;
    private float mCircleX = -100;
    private float mCircleY = -100;
    private float mCircleRadius = 500;
    private Paint mCirclePaint = new Paint();
    private PorterDuffXfermode mXferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.getVisibility() == View.VISIBLE) {
            drawMaskAndSpotlight(canvas);
        }
    }

    // +++ circle spot
    public void setCirclePos(float mCircleX, float mCircleY) {
        this.mCircleX = mCircleX;
        this.mCircleY = mCircleY;
    }

    private void drawMaskAndSpotlight(Canvas canvas) {

        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        RadialGradient gradient = new RadialGradient(mCircleX, mCircleY,
                mCircleRadius,0xff000000,0x00000000,
                android.graphics.Shader.TileMode.CLAMP);
        mCirclePaint.reset();
        mCirclePaint.setColor(MASK_COLOR);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mCirclePaint);
        mCirclePaint.setShader(gradient);
        mCirclePaint.setXfermode(mXferMode);
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mCirclePaint);
        canvas.restoreToCount(sc);
    }
}