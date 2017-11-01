package yjs.gd.com.gdandroidsample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import yjs.gd.com.gdandroidsample.R;

/**
 * Created by Jesson_Yi on 2017/7/18.
 */

public class PointViewGroup extends FrameLayout  {

    private PointView mSpotView;
    private final static int FADE_IN_OUT_DURATION = 200;

    public PointViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mSpotView=(PointView) findViewById(R.id.spot_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                handleAsusCircleSpotView(View.VISIBLE, event);
            case MotionEvent.ACTION_POINTER_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:

                handleAsusCircleSpotView(View.VISIBLE, event);
                break;

            case MotionEvent.ACTION_UP:

                handleAsusCircleSpotView(View.INVISIBLE, event);
            case MotionEvent.ACTION_POINTER_UP:

                break;

            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return true;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return true;
    }

    void handleAsusCircleSpotView(int visible, MotionEvent ev) {
        if (mSpotView != null) {
            mSpotView.setCirclePos(ev.getX(), ev.getY());
            mSpotView.invalidate();
            if (mSpotView.getVisibility() != visible) {
               doViewInOutAnimation(mSpotView, visible == View.VISIBLE);
            }
        }
    }
    void doViewInOutAnimation(final View v, final boolean in) {
        if (v == null) {
            return;
        }
        if (in) {
            v.setAlpha(.999f);
            v.setVisibility(View.VISIBLE);
        }
        Animation anim = in ? new AlphaAnimation(0.0f, 1.0f)
                : new AlphaAnimation(1.0f, 0.0f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(FADE_IN_OUT_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setAlpha(in ? .999f : 0.0f);
                v.setVisibility(in ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });
        v.startAnimation(anim);
    }
}