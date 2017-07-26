package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.main.TestScanActivity;

/**
 * Created by Jesson_Yi on 2017/7/24.
 */

public class QRCodeFragment extends Fragment implements View.OnClickListener {
    public TextView scanCode;
    public TextView generateCode;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.qrcode_layout,
                container, false);
        scanCode=(TextView) rootView.findViewById(R.id.test_scan_qrcode);
        generateCode=(TextView) rootView.findViewById(R.id.test_generate_qrcode);
        scanCode.setOnClickListener(this);
        generateCode.setOnClickListener(this);
        return rootView;

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_scan_qrcode:
                showScanQRcodeFragment();
                break;
            case R.id.test_generate_qrcode:
                showGenerateQRcodeFragment();
                break;
        }
    }
    public void showScanQRcodeFragment(){

//        getActivity().startActivity(new Intent(getActivity(), TestScanActivity.class));
        Fragment fragment = new ScanQRCodeFragment();
        FragmentManager fragManager = getActivity().getFragmentManager();
        fragManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
    public void showGenerateQRcodeFragment(){
        Fragment fragment = new GenerateQRCodeFragment();
        Bundle args = new Bundle();
        args.putString("QR_CODE","2017072910309999");
        fragment.setArguments(args);
        FragmentManager fragManager = getActivity().getFragmentManager();
        fragManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }












}
