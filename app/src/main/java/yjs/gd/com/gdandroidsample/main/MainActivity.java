package yjs.gd.com.gdandroidsample.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yjs.gd.com.gdandroidsample.R;

import yjs.gd.com.gdandroidsample.fragment.ChatFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

                Fragment fragment = new ChatFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
                fragmentManager.executePendingTransactions();
    }
}
