package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.widget.MyScroll;

/**
 * Created by Jesson_Yi on 2017/7/14.
 */

public class MyScrollViewFragment extends Fragment {



    private Toolbar toolbar;
    private MyScroll mScroll;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_scroll_layout,
                container, false);
        toolbar=(Toolbar)rootView.findViewById(R.id.toolbar);
        mScroll=(MyScroll) rootView.findViewById(R.id.my_scroll_layout);
        mScroll.setBar(toolbar);

        return rootView;

    }







}
