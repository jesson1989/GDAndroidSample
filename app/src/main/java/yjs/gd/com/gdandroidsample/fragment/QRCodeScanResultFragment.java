package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yjs.gd.com.gdandroidsample.R;

/**
 * Created by Jesson_Yi on 2017/7/26.
 */

public class QRCodeScanResultFragment extends Fragment {
    public TextView scanResultTv;
    public String mQRCode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mQRCode = args.getString("QR_CODE");
        }
    }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.qrcode_scan_result_layout,
                    container, false);
            scanResultTv=(TextView) rootView.findViewById(R.id.scan_result_tv);
            return rootView;

        }
    @Override
    public void onResume() {
        super.onResume();
        final ProgressDialog pd = ProgressDialog.show(getActivity(), null, getActivity().getText(R.string.processing), true, false);
        Handler handler = new Handler(getActivity().getMainLooper());
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (pd != null && isVisible()) {
                    pd.dismiss();
                    scanResultTv.setText(mQRCode);
                }
            }
        }, 5000);
    }


}
