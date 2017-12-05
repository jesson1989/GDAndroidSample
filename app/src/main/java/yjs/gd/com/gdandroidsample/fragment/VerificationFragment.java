package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.widget.VerificationCodeInput;

/**
 * Created by jesson_yi on 2017/12/4.
 */

public class VerificationFragment extends Fragment {

    private VerificationCodeInput verificationCodeInput1;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.verification_layout,
                container, false);
        verificationCodeInput1 = (VerificationCodeInput)rootView.findViewById(R.id.verificationCodeInput);

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
}
