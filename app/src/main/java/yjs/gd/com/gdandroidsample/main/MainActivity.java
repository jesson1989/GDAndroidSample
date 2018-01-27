package yjs.gd.com.gdandroidsample.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yjs.gd.com.gdandroidsample.R;

import yjs.gd.com.gdandroidsample.fragment.ChatFragment;
import yjs.gd.com.gdandroidsample.fragment.MainFragment;
import yjs.gd.com.gdandroidsample.fragment.MyPointViewFragment;
import yjs.gd.com.gdandroidsample.fragment.MyScrollViewFragment;
import yjs.gd.com.gdandroidsample.fragment.MySlideMenuFragment;
import yjs.gd.com.gdandroidsample.fragment.QRCodeFragment;
import yjs.gd.com.gdandroidsample.fragment.VideoFragment;
import yjs.gd.com.gdandroidsample.fragment.XfermodeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            FragmentManager fragmentManager = getFragmentManager();
            Log.e("haha","---------->haha1111111");

            Fragment fragment = new MainFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();

        }


    }
}
