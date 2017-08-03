package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.view.PayPwdView;

/**
 * Created by Jesson_Yi on 2017/8/2.
 */

public class PayFragment extends Fragment implements PayPwdView.InputCallBack ,View.OnClickListener {

    public TextView payTv;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pay_layout,
                container, false);
        payTv=(TextView) rootView.findViewById(R.id.pay_tv);
        payTv.setOnClickListener(this);
        return rootView;

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_tv:
               showScanQRcodeFragment();
                break;
        }
    }
    @Override
    public void onInputFinish(String result) {
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
    }
    public void showScanQRcodeFragment(){

        Bundle bundle = new Bundle();
        bundle.putString(PayDialogFragment.EXTRA_CONTENT, "提现：¥ " + 100.00);

        PayDialogFragment fragment = new PayDialogFragment();
        fragment.setArguments(bundle);
        fragment.setPaySuccessCallBack(this);
        fragment.show(getActivity().getFragmentManager(),"Pay");
    }

}
