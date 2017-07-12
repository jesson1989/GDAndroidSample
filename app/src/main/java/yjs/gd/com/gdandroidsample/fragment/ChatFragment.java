package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.adapter.ChatAdapter;
import yjs.gd.com.gdandroidsample.bean.ChatInfo;

/**
 * Created by Jesson_Yi on 2017/7/12.
 */

public class ChatFragment extends Fragment {

    private ListView mListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat,
                container, false);
        mListView=(ListView) rootView.findViewById(R.id.my_list_view);
        List<ChatInfo> datas=new ArrayList<>();
        ChatInfo cf=new ChatInfo();
        cf.setType(0);
        cf.setMessage("傻逼，在吗？");
        cf.setmIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ninja));
        ChatInfo cf1=new ChatInfo();
        cf1.setType(1);
        cf1.setMessage("在你麻痹，干啥？");
        cf1.setmIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
        ChatInfo cf2=new ChatInfo();
        cf2.setType(0);
        cf2.setMessage("王者荣耀，来不来？");
        cf2.setmIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ninja));
        ChatInfo cf3=new ChatInfo();
        cf3.setType(1);
        cf3.setMessage("滚，老子睡觉了");
        cf3.setmIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
        ChatInfo cf4=new ChatInfo();
        cf4.setType(0);
        cf4.setMessage("开好房间等你，呵呵");
        cf4.setmIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ninja));

        ChatInfo cf5=new ChatInfo();
        cf5.setType(0);
        cf5.setMessage("快点进啊！");
        cf5.setmIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ninja));

        ChatInfo cf6=new ChatInfo();
        cf6.setType(1);
        cf6.setMessage("真的困死了，你傻逼啊？天天就知道打游戏！");
        cf6.setmIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));

        datas.add(cf);
        datas.add(cf1);
        datas.add(cf2);
        datas.add(cf3);
        datas.add(cf4);
        datas.add(cf5);
        datas.add(cf6);
        ChatAdapter adapter =new ChatAdapter(getContext(),datas);
        mListView.setAdapter(adapter);
        return rootView;

    }
}
