package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yjs.gd.com.gdandroidsample.widget.AvatarView;

/**
 * Created by jesson on 2017/7/28.
 */

public class AvatarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = new AvatarView(getActivity());

        return rootView;

    }
}
