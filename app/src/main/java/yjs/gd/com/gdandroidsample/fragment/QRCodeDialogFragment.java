package yjs.gd.com.gdandroidsample.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.utils.QRCodeEncoder;

/**
 * Created by jesson_yi on 2017/12/4.
 */

public class QRCodeDialogFragment extends DialogFragment {
    private int failCount = 0;
    TextView textupper;
    ImageView fingerPrint;
    private Context mContext;
    public String mQRCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        Bundle args = getArguments();
        if (args != null) {
            mQRCode = args.getString("QR_CODE");
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        View view = inflater.inflate(R.layout.generate_qrcode_layout, container, false);
        textupper = (TextView) view.findViewById(R.id.qrcode_content);
        fingerPrint = (ImageView)view.findViewById(R.id.qrcode_iv);
        createQRCode();

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }


    private void createQRCode() {

        if((TextUtils.isEmpty(mQRCode))){
            return;
        }
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode(mQRCode, BGAQRCodeUtil.dp2px(getActivity(), 150), Color.parseColor("#ff0000"));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    fingerPrint.setImageBitmap(bitmap);
                    textupper.setText(mQRCode);
                } else {
                    Toast.makeText(getContext(), "生成二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
