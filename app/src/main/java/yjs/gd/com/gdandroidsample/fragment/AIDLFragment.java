package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.asus.netusage.aidl.INetUsageUpdate;
import com.asus.netusage.aidl.NetUsageInfo;

import java.util.List;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.utils.ViewUtils;
import yjs.gd.com.gdandroidsample.widget.PercentView;

/**
 * Created by Jesson_Yi on 2017/10/31.
 */

public class AIDLFragment extends Fragment implements View.OnClickListener {

    public TextView aidlTv;
    public TextView aidlTv1;
    INetUsageUpdate mClient;
    public View viewGroup;
    public PercentView mPercentView;
    public TextView flowText;
    public TextView setText;
    public int type;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aidl_layout,
                container, false);
        aidlTv=(TextView) rootView.findViewById(R.id.aidl_tv);
        aidlTv.setOnClickListener(this);
        aidlTv1=(TextView) rootView.findViewById(R.id.aidl_tv1);
        aidlTv1.setOnClickListener(this);
        viewGroup = rootView.findViewById(R.id.flow_info_group);
        viewGroup.setOnClickListener(this);
        mPercentView = (PercentView) rootView.findViewById(R.id.percent_usage);
        flowText = (TextView)rootView.findViewById(R.id.flow_info);
        setText = (TextView)rootView.findViewById(R.id.flow_set);
        return rootView;

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aidl_tv:
                getFlowClient();
                break;
            case R.id.aidl_tv1:
                showFlowInfo();
                break;
            case R.id.flow_info_group:
                showMobileManagerPager();
                break;
        }
    }
    public void showMobileManagerPager() {

//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.asus.cnmobilemanager", "com.asus.cnmobilemanager.net.NetUsageUpdateService"));
//        Log.i("TAG", "onServiceConnected: ");
//        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        Intent intent  = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(type==0){
            intent.putExtra("net_usage_fragment_type",3);
        }else{
            intent.putExtra("net_usage_fragment_type",10);
        }
        intent.setComponent(new ComponentName("com.asus.cnmobilemanager", "com.asus.cnmobilemanager.net.DataUsageActivity"));
        getActivity().startActivity(intent);
    }



    public void getFlowClient() {

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.asus.cnmobilemanager", "com.asus.cnmobilemanager.net.NetUsageUpdateService"));
        Log.i("TAG", "onServiceConnected: ");
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    public void showFlowInfo() {
        if(mClient!=null){
            try {
                NetUsageInfo info =mClient.getNetInfo();

                Log.e("NetUsageInfo","---------->"+info.toString());
                float startAngle = 0;
                float sweepAngle = 0;
                if(info.isSetLimit==0){
                    type=0;
                    flowText.setVisibility(View.VISIBLE);
                    flowText.setText("未设置套餐");
                    setText.setVisibility(View.VISIBLE);
                    setText.setText("设置套餐");
                    startAngle = 0;
                    sweepAngle =0;
                }else if(info.isSetLimit==1){
                    type=1;
                    CharSequence content="";
                    if(info.reminder>=0){
                        content=getString(R.string.notification_bar_flow_usage_info, ViewUtils.getHumanReadableSize(info.reminder),ViewUtils.getHumanReadableSize(info.todayUsed));
                        flowText.setVisibility(View.VISIBLE);
                        flowText.setText(content);
                        double percent=(double)(info.usageLimit-info.reminder)/info.usageLimit;
                        startAngle=(float)percent*360;
                        sweepAngle=(float)(360-percent*360);
                    }else{
                        long reminder = Math.abs(info.reminder);
                        content=getString(R.string.notification_bar_flow_usage_info_1,ViewUtils.getHumanReadableSize(reminder),ViewUtils.getHumanReadableSize(info.todayUsed));
                        content=getMiddleRedSpan(getString(R.string.notification_bar_flow_usage_info_2),ViewUtils.getHumanReadableSize(reminder),content.toString());
                        startAngle = 0;
                        sweepAngle =0;
                    }
                    flowText.setVisibility(View.VISIBLE);
                    flowText.setText(content);
                }
                mPercentView.setVisibility(View.VISIBLE);
                mPercentView.setAngle(startAngle,sweepAngle);
            }  catch (RemoteException e) { e.printStackTrace(); }
        }
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mClient = INetUsageUpdate.Stub.asInterface(service);
            Log.i("TAG", "onServiceConnected11111: ");
           }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("TAG", "onServiceDisconnected: " + name);
        }
    };
    private CharSequence getMiddleRedSpan(String pre, String mid, String content){
        int sizePre = pre.length();
        int sizemid = mid.length();
        SpannableString span = new SpannableString(content);
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#ff1d12")), sizePre, sizePre+sizemid, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

}
