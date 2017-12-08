package yjs.gd.com.gdandroidsample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.fragment.IInputReceiver;

/**
 * Created by jesson_yi on 2017/12/6.
 */

public class VerificationCodeView extends LinearLayout implements IInputReceiver{

    private static final String TAG = "VerificationCodeInput";
    private List<String> mList = new ArrayList<>();;

    private int box = 4;
    private int boxWidth = 180;
    private int boxHeight = 180;
    private int childHPadding = 14;
    private int childVPadding = 14;
    private Drawable boxBgFocus = null;
    private Drawable boxBgNormal = null;
    private List<TextView> mTextViewList = new ArrayList<>();
    private ArrayList<String> result;//输入结果保存

    public VerificationCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.vericationCodeInput);
        box = a.getInt(R.styleable.vericationCodeInput_box, 4);

        childHPadding = (int) a.getDimension(R.styleable.vericationCodeInput_child_h_padding, 0);
        childVPadding = (int) a.getDimension(R.styleable.vericationCodeInput_child_v_padding, 0);
        boxBgFocus = a.getDrawable(R.styleable.vericationCodeInput_box_bg_focus);
        boxBgNormal = a.getDrawable(R.styleable.vericationCodeInput_box_bg_normal);
        boxWidth = (int) a.getDimension(R.styleable.vericationCodeInput_child_width, boxWidth);
        boxHeight = (int) a.getDimension(R.styleable.vericationCodeInput_child_height, boxHeight);
        initViews();
    }



    @Override
    public void updateContent(List<String> result){
        for (int i = 0; i < 4; i++) {
            mTextViewList.get(i).setText("");
        }
        mList.clear();
        mList.addAll(result);

        for (int i = 0; i < result.size(); i++) {
            mTextViewList.get(i).setText(result.get(i));
        }
    }

    @Override
    public int getMaxCount(){
        return 20;

    }

    @Override
    public List<String> getContentList(){
        return mList;
    }

    private void initViews() {
        for (int i = 0; i < box; i++) {
            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(boxWidth, boxHeight);
            layoutParams.bottomMargin = childVPadding;
            layoutParams.topMargin = childVPadding;
            layoutParams.leftMargin = childHPadding;
            layoutParams.rightMargin = childHPadding;
            layoutParams.gravity = Gravity.CENTER;

            setBg(textView);
            textView.setTextColor(Color.BLACK);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            addView(textView, i);
            mTextViewList.add(textView);
        }


    }


    private void setBg(TextView editText) {
        editText.setBackground(boxBgNormal);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        if (count > 0) {
            View child = getChildAt(0);
            int cHeight = child.getMeasuredHeight();
            int cWidth = child.getMeasuredWidth();
            int maxH = cHeight + 2 * childVPadding;
            int maxW = (cWidth + childHPadding) * box + childHPadding;
            setMeasuredDimension(resolveSize(maxW, widthMeasureSpec),
                    resolveSize(maxH, heightMeasureSpec));
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//
//            child.setVisibility(View.VISIBLE);
//            int cWidth = child.getMeasuredWidth();
//            int cHeight = child.getMeasuredHeight();
//            int cl = (i) * (cWidth + childHPadding);
//            int cr = cl + cWidth;
//            int ct = childVPadding;
//            int cb = ct + cHeight;
//            child.layout(cl, ct, cr, cb);
//        }


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {//点击控件弹出输入键盘
//            mEditTextList.get(currentPosition).requestFocus();
            Log.e("MLML","---------------->MLMLM!!!!!!!!");
            EventBus.getDefault().post("shabi");

            return true;
        }
        return true;
    }
}