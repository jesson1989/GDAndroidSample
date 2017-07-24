package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yjs.gd.com.gdandroidsample.R;
import yjs.gd.com.gdandroidsample.view.XfermodeView;

/**
 * Created by Jesson_Yi on 2017/7/21.
 */

public class XfermodeFragment extends Fragment {




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = new XfermodeView(getActivity());

        return rootView;

    }
}
