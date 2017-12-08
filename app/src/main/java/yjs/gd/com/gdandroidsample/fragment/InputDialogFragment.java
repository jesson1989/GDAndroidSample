package yjs.gd.com.gdandroidsample.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import java.util.ArrayList;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.widget.PayPwdView;
import yjs.gd.com.gdandroidsample.widget.PwdInputMethodView;

/**
 * Created by jesson on 2017/12/5.
 */

public class InputDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String EXTRA_CONTENT = "extra_content";    //提示框内容

//    private PayPwdView psw_input;
    private IInputReceiver inputReceiver;
    private PayPwdView.InputCallBack inputCallBack;
    private ArrayList<String> result = new ArrayList<String>();//输入结果保存

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.verification_dialog_layout);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.TOP;
        window.setAttributes(lp);

        initView(dialog);
        return dialog;
    }

    private void initView(Dialog dialog) {
        Bundle bundle = getArguments();
        if (bundle != null) {
//            TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
//            tv_content.setText(bundle.getString(EXTRA_CONTENT));
        }

//        psw_input = (PayPwdView) dialog.findViewById(R.id.payPwdView);
        PwdInputMethodView inputMethodView = (PwdInputMethodView) dialog.findViewById(R.id.inputMethodView);
//        psw_input.setInputMethodView(inputMethodView);
//        psw_input.setInputCallBack(inputCallBack);

        inputMethodView.setInputReceiver(new PwdInputMethodView.InputReceiver() {
            @Override
            public void receive(String num) {
                if (num.equals("-1")) {
                    if (!result.isEmpty()) {
                        result.remove(result.size() - 1);
                        inputReceiver.updateContent(result);
                    }
                } else {
                    if (result.size() < inputReceiver.getMaxCount()) {
                        result.add(num);
                        inputReceiver.updateContent(result);
//                        ensureFinishInput();
                    }
                }


            }
        });






//        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;

        }
    }

    /**
     * 设置输入回调
     *
     * @param inputCallBack
     */
    public void setPaySuccessCallBack(PayPwdView.InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
    }

    public IInputReceiver getInputReceiver() {
        return inputReceiver;
    }

    public void setInputReceiver(IInputReceiver inputReceiver) {
        this.inputReceiver = inputReceiver;
        result = new ArrayList<>();
//        result.addAll(inputReceiver.getContentList());

    }
}
