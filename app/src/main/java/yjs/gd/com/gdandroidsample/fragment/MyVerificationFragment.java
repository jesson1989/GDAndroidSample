package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.widget.PayPwdView;
import yjs.gd.com.gdandroidsample.widget.VerificationCodeView;

/**
 * Created by jesson_yi on 2017/12/5.
 */

public class MyVerificationFragment extends Fragment{

    VerificationCodeView mVerificationView;
    VericationDialogFragment dialogFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_ver_layout,
                container, false);
        mVerificationView = (VerificationCodeView)rootView.findViewById(R.id.verification_view);

        return rootView;

    }
    @Subscribe(threadMode = ThreadMode.POSTING,priority = 100,sticky = true)
    public void showVerificationView(String event){
        EventBus.getDefault().removeStickyEvent(event);
        if(dialogFragment==null){
            dialogFragment = new VericationDialogFragment();
            dialogFragment.setmVerificationView(mVerificationView);
        }
        dialogFragment.show(getFragmentManager(),"sss");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
