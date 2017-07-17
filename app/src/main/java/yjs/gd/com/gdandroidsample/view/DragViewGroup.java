package yjs.gd.com.gdandroidsample.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Jesson_Yi on 2017/7/14.
 */

public class DragViewGroup extends FrameLayout {

    private int he;
    private int wi;
    private View MainView ;
    private View MenuView ;
    private ViewDragHelper viewDragHelper;
    private ViewDragHelper.Callback callback =new ViewDragHelper.Callback(){


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child==MainView;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if(MainView.getLeft()<500){

                viewDragHelper.smoothSlideViewTo(MainView,0,0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }else{
                viewDragHelper.smoothSlideViewTo(MainView,300,0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }
        }
    };

    public DragViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        MenuView =getChildAt(0);
        MainView =getChildAt(1);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewDragHelper=ViewDragHelper.create(this,callback);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
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
        setMeasuredDimension(wi,he);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count =getChildCount();
        for(int i=0;i<count;i++){
            View view=getChildAt(i);
            int height=view.getMeasuredHeight();
            int width=view.getMeasuredWidth();
            view.layout(l,t,width,height);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       return viewDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
            postInvalidate();
        }
    }
}
