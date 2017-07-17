package yjs.gd.com.gdandroidsample.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yjs.gd.com.gdandroidsample.R;

import yjs.gd.com.gdandroidsample.fragment.ChatFragment;
import yjs.gd.com.gdandroidsample.fragment.MyScrollViewFragment;
import yjs.gd.com.gdandroidsample.fragment.MySlideMenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

                Fragment fragment = new MySlideMenuFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
                fragmentManager.executePendingTransactions();
    }
}
