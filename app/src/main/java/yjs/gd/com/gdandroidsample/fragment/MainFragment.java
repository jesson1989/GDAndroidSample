package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yjs.gd.com.gdandroidsample.R;

/**
 * Created by Jesson_Yi on 2017/7/25.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    TextView chatTv;
    TextView qrcodeTv;
    TextView pointTv;
    TextView scrollTv;
    TextView slideTv;
    TextView videoTv;
    TextView xfermodeTv;
    TextView timeaxleTv;
    TextView avatarTv;
    TextView slideDelTv;
    TextView payTv;
    TextView aidlTv;
    TextView tabPagerTv;
    TextView verTv;
    TextView mVerTv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_layout,
                container, false);
        chatTv = (TextView) rootView.findViewById(R.id.chat_fragment);
        qrcodeTv = (TextView) rootView.findViewById(R.id.qrcode_fragment);
        pointTv = (TextView) rootView.findViewById(R.id.point_view_fragment);
        scrollTv = (TextView) rootView.findViewById(R.id.scroll_view_fragment);
        slideTv = (TextView) rootView.findViewById(R.id.slide_menu_fragment);
        videoTv = (TextView) rootView.findViewById(R.id.video_fragment);
        xfermodeTv = (TextView) rootView.findViewById(R.id.xfermode_fragment);
        timeaxleTv = (TextView) rootView.findViewById(R.id.timeaxle_fragment);
        avatarTv = (TextView) rootView.findViewById(R.id.avatar_fragment);
        slideDelTv = (TextView) rootView.findViewById(R.id.slide_del_fragment);
        payTv =(TextView) rootView.findViewById(R.id.pay_fragment);
        aidlTv = (TextView) rootView.findViewById(R.id.aidl_fragment);
        tabPagerTv = (TextView)rootView.findViewById(R.id.tab_pager_fragment);
        verTv = (TextView)rootView.findViewById(R.id.verification_fragment);
        mVerTv = (TextView) rootView.findViewById(R.id.my_verification_fragment);





        chatTv.setOnClickListener(this);
        qrcodeTv.setOnClickListener(this);
        pointTv.setOnClickListener(this);
        scrollTv.setOnClickListener(this);
        slideTv.setOnClickListener(this);
        videoTv.setOnClickListener(this);
        xfermodeTv.setOnClickListener(this);
        timeaxleTv.setOnClickListener(this);
        avatarTv.setOnClickListener(this);
        slideDelTv.setOnClickListener(this);
        payTv.setOnClickListener(this);
        aidlTv.setOnClickListener(this);
        tabPagerTv.setOnClickListener(this);
        verTv.setOnClickListener(this);
        mVerTv.setOnClickListener(this);

        return rootView;

    }
    @Override
    public void onClick(View v) {
        int type=0;
        switch (v.getId()) {
            case R.id.chat_fragment:
                type=0;
                break;
            case R.id.qrcode_fragment:
                type=1;
                break;
            case R.id.point_view_fragment:
                type=2;
                break;
            case R.id.scroll_view_fragment:
                type=3;
                break;
            case R.id.slide_menu_fragment:
                type=4;
                break;
            case R.id.video_fragment:
                type=5;
                break;
            case R.id.xfermode_fragment:
                type=6;
                break;
            case R.id.timeaxle_fragment:
                type=7;
                break;
            case R.id.avatar_fragment:
                type=8;
                break;
            case R.id.slide_del_fragment:
                type=9;
                break;
            case R.id.pay_fragment:
                type=10;
                break;
            case R.id.aidl_fragment:
                type=11;
                break;
            case R.id.tab_pager_fragment:
                type=12;
                break;
            case R.id.verification_fragment:
                type=13;
                break;
            case R.id.my_verification_fragment:
                type=14;
                break;
        }
        showFragment(type);
    }

    private void showFragment(int type){
        FragmentManager fragmentManager = getActivity().getFragmentManager();

        Fragment fragment = getFragment(type);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
    private Fragment getFragment(int type){
        Fragment fragment;
        switch(type){
            case 0:
                fragment =new ChatFragment();
                break;
            case 1:
                fragment =new QRCodeFragment();
                break;
            case 2:
                fragment =new MyPointViewFragment();
                break;
            case 3:
                fragment =new MyScrollViewFragment();
                break;
            case 4:
                fragment =new MySlideMenuFragment();
                break;
            case 5:
                fragment =new VideoFragment();
                break;
            case 6:
                fragment =new XfermodeFragment();
                break;
            case 7:
                fragment =new TimeAxleFragment();
                break;
            case 8:
                fragment =new AvatarFragment();
                break;
            case 9:
                fragment =new SlideDelRecyclerViewFragment();
                break;
            case 10:
                fragment =new PayFragment();
                break;
            case 11:
                fragment =new AIDLFragment();
                break;
            case 12:
                fragment =new TabPagerFragment();
                break;
            case 13:
                fragment =new VerificationFragment();
                break;
            case 14:
                fragment = new MyVerificationFragment();
                break;
            default:
                fragment =new MyVerificationFragment();
                break;
        }
        return fragment;
    }
}