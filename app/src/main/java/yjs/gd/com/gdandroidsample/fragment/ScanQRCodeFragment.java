package yjs.gd.com.gdandroidsample.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.widget.ZXingView;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Jesson_Yi on 2017/7/25.
 */

public class ScanQRCodeFragment extends Fragment implements QRCodeView.Delegate {
    private static final String TAG="ScanQRCodeFragment";
    private static final int REQUEST_SCAN_QRCODE = 1;
    private static String[] PERMISSIONS_SCAN_QRCODE = {
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private QRCodeView mQRCodeView;
    private Handler mHandler =new Handler();

    private Runnable startScanRunnable= new Runnable() {
        @Override
        public void run() {
            mQRCodeView.startCamera();
            mQRCodeView.showScanRect();
            mQRCodeView.startSpot();

        }
    };
    private Runnable stopScanRunnable= new Runnable() {
        @Override
        public void run() {
            mQRCodeView.stopCamera();
            mQRCodeView.onDestroy();

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scan_qrcode_layout,
                container, false);
        verifyScanQRcodePermissions(getActivity());
        mQRCodeView = (ZXingView) rootView.findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        return rootView;

    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(startScanRunnable,5);
    }

    @Override
    public void onStop() {
        mQRCodeView.stopCamera();

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mQRCodeView.onDestroy();

        super.onDestroy();
    }
    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        vibrate();
        showScanResultFragment(result);
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }


    public static void verifyScanQRcodePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_SCAN_QRCODE,
                    REQUEST_SCAN_QRCODE
            );
        }
    }
    public void showScanResultFragment(String result){
        getFragmentManager().popBackStack();
        Fragment fragment = new QRCodeScanResultFragment();
        Bundle args = new Bundle();
        args.putString("QR_CODE",result);
        fragment.setArguments(args);
        FragmentManager fragManager = getActivity().getFragmentManager();
        fragManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();

    }




}
