package com.example.myfuncdemo.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.view.fragment.RxjavaDemoFragment;

/**
 * Administrator on 2016/7/28.
 */
public class RxJavaDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_content_rxjava, new RxjavaDemoFragment(), RxjavaDemoFragment.class.getName())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
