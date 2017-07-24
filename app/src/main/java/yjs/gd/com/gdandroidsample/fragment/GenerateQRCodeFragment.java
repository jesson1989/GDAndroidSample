package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.utils.QRCodeEncoder;

/**
 * Created by Jesson_Yi on 2017/7/24.
 */

public class GenerateQRCodeFragment extends Fragment {


    private ImageView mEnglishIv;
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
        View rootView = inflater.inflate(R.layout.generate_qrcode_layout,
                container, false);
        mEnglishIv = (ImageView) rootView.findViewById(R.id.iv_english);
        createEnglishQRCode();
        return rootView;

    }
    private void createEnglishQRCode() {

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
                    mEnglishIv.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(getContext(), "生成英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


}
