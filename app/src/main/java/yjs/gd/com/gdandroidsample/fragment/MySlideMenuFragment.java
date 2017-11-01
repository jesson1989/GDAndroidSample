package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yjs.gd.com.gdandroidsample.R;

/**
 * Created by Jesson_Yi on 2017/7/17.
 */

public class MySlideMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_slide_layout,
                container, false);
        return rootView;

    }
}