package yjs.gd.com.gdandroidsample.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Jesson_Yi on 2017/7/6.
 */

public class MyScroll extends ViewGroup {
    private float mLastMotionY;
    private int he;
    private int wi;
    private int startY;
    private int endY;
    private Scroller mScroller;
    private Toolbar bar;
    private ObjectAnimator obj;

    public MyScroll(Context context) {
        super(context);
    }

    public MyScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);

    }

    public MyScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count =getChildCount();
        for(int i=0;i<count;i++){
            View view=getChildAt(i);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
            wi=view.getMeasuredWidth();
            he=view.getMeasuredHeight();
        }
        setMeasuredDimension(wi,3*he);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count =getChildCount();
        for(int i=0;i<count;i++){
            View view=getChildAt(i);
            int height=view.getMeasuredHeight();
            view.layout(l,i*height,r,(i+1)*height);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float x = event.getX();
        final float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startY=getScrollY();
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy=y-mLastMotionY;
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                if(getScrollY()<0){
                    if(-dy<0){
                        dy=0;
                    }
                }
                if(getScrollY()>getHeight()*2/3){
                    if(-dy>0){
                        dy=0;
                    }
                }
                if(-dy>0){
                    Log.e("hahah","------->1");
                    objAnimation(1);
                }else{
                    Log.e("hahah","------->2");
                    objAnimation(0);
                }
                scrollBy(0,(int)-dy);

                mLastMotionY=y;
                break;
            case MotionEvent.ACTION_UP:
                int ds=0;
                endY=getScrollY();
                if(endY<0||endY>getHeight()*2/3){
                    break;
                }
                int ppp=endY-startY;
                if(ppp>0){
                if(ppp<he/3){
                    mScroller.startScroll(0,getScrollY(),0,-ppp);
                }else{
                    mScroller.startScroll(0,getScrollY(),0,he-ppp);
                }
                }else{
                    if(-ppp<he/3){
                        mScroller.startScroll(0,getScrollY(),0,-ppp);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-he-ppp);}
                }
                break;
        }
        postInvalidate();
        return true;
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
    private void objAnimation(int flag){
        if(obj!=null&&obj.isRunning()){
            obj.cancel();
        }
        if(flag==0){
            obj=ObjectAnimator.ofFloat(bar,"translationY",bar.getTranslationY(),0);

        }else{
            obj=ObjectAnimator.ofFloat(bar,"translationY",bar.getTranslationY(),-bar.getHeight());
        }
        obj.start();

    }


    public Toolbar getBar() {
        return bar;
    }

    public void setBar(Toolbar bar) {
        this.bar = bar;
    }
}
