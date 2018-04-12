package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.widget.VerificationCodeInput;
import yjs.gd.com.gdandroidsample.widget.VerificationCodeView;

/**
 * Created by jesson_yi on 2017/12/4.
 */

public class VerificationFragment extends Fragment {


    VerificationCodeView mVerificationView;
    InputDialogFragment dialogFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.verification_layout,
                container, false);
        mVerificationView = (VerificationCodeView)rootView.findViewById(R.id.verificationCodeInput);

//        verificationCodeInput.setOnCompleteListener(new VerificationCodeInput.Listener() {
//            @Override
//            public void onComplete(String content) {
////                btn_confirm.setEnabled(true);
////                btn_confirm.setBackgroundResource(R.drawable.btn_bg_shape_enable);
////                btn_confirm.setTextColor(Color.parseColor("#e4c16a"));
////                codeNum = content;
//                Log.e("TTTBBB","-------------->content"+content);
//            }
//        });

        return rootView;

    }


    @Subscribe(threadMode = ThreadMode.POSTING,priority = 100,sticky = true)
    public void showVerificationView(String event){
        EventBus.getDefault().removeStickyEvent(event);
        if(dialogFragment==null){
            dialogFragment = new InputDialogFragment();
            dialogFragment.setInputReceiver(mVerificationView);
        }
        dialogFragment.setInputReceiver(mVerificationView);
        dialogFragment.show(getFragmentManager(),"sss");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
