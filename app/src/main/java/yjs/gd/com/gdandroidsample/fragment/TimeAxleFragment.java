package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.adapter.TimeAxleAdapter;
import yjs.gd.com.gdandroidsample.bean.TrackingInfo;

/**
 * Created by Jesson_Yi on 2017/7/27.
 */

public class TimeAxleFragment extends Fragment {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private TimeAxleAdapter adapter;
    List<TrackingInfo> datas = new ArrayList<TrackingInfo>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.time_axle_layout,
                container, false);
        mContext = getActivity();
        mRecyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerView);
        initDatas();
        adapter=new TimeAxleAdapter(datas,mContext);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        return rootView;

    }
    /**
     * 这里用虚拟数据实现，仅供参考
     */
    private void initDatas() {
        // TODO Auto-generated method stub
        TrackingInfo item1 = new TrackingInfo();
        item1.setTitle("提交订单");
        item1.setTime("03-14 08:13");
        item1.setStatus(1);
        TrackingInfo item2 = new TrackingInfo();
        item2.setTitle("已装车");
        item2.setTime("03-14 22:32");
        item2.setStatus(1);
        TrackingInfo item3 = new TrackingInfo();
        item3.setTitle("配送中");
        item3.setTime("03-15 00:33");
        item3.setStatus(0);
        TrackingInfo item4 = new TrackingInfo();
        item4.setTitle("已签收");
        item4.setTime("03-15 15:55");
        item4.setStatus(0);

        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);
    }
}
