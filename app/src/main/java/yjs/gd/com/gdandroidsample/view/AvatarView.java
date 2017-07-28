package yjs.gd.com.gdandroidsample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import yjs.gd.com.gdandroidsample.R;

/**
 * Created by jesson on 2017/7/28.
 */

public class AvatarView extends View {
    private Bitmap mSrc,mRef;


    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pc);
        BitmapShader bitmapShader = new BitmapShader(bitmap,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint =new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);

        canvas.drawCircle(400,450,400,paint);


//图片倒影效果及线性着色器实现朦胧效果。
//        mSrc =scaleBitmapHalf(BitmapFactory.decodeResource(getResources(),R.drawable.ppcc));
//        Matrix matrix =new Matrix();
//        matrix.setScale(1F,-1F);
//        mRef =Bitmap.createBitmap(mSrc,0,0,mSrc.getWidth(),mSrc.getHeight(),matrix,true);
//
//
//        LinearGradient linearGradient =new LinearGradient(0,mSrc.getHeight(),0,mSrc.getHeight()+mSrc.getHeight()/4,0XDD000000,0X10000000,Shader.TileMode.CLAMP);
//        Paint paint =new Paint();
//        paint.setShader(linearGradient);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//
//        canvas.drawBitmap(mSrc,0,0,null);
//        canvas.drawBitmap(mRef,0,mSrc.getHeight(),null);
//        canvas.drawRect(0,mSrc.getHeight(),mSrc.getWidth(),2*mSrc.getHeight(),paint);
//        paint.setXfermode(null);

    }
    //等比例缩放图片
    private Bitmap scaleBitmapHalf(Bitmap bm) {
        return Bitmap.createScaledBitmap(bm, bm.getWidth()/2, bm.getHeight()/2, true);
    }
}
