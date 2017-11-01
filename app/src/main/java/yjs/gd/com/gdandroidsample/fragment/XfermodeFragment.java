package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yjs.gd.com.gdandroidsample.widget.XfermodeView;

/**
 * Created by Jesson_Yi on 2017/7/21.
 */

public class XfermodeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = new XfermodeView(getActivity());

        return rootView;

    }
}
