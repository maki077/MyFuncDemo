package com.example.myfuncdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.view.fragment.rxjava.NetRxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Administrator on 2016/7/28.
 */
public class RxjavaDemoFragment extends Fragment {

    @Bind(R.id.btn_net)
    Button btnNet;
    @Bind(R.id.btn_net2)
    Button btnNet2;
    @Bind(R.id.btn_not_more_click)
    Button btnNotMoreClick;
    @Bind(R.id.btn_checkbox_state_update)
    Button btnCheckboxStateUpdate;
    @Bind(R.id.btn_text_change)
    Button btnTextChange;
    @Bind(R.id.btn_buffer)
    Button btnBuffer;
    @Bind(R.id.btn_zip)
    Button btnZip;
    @Bind(R.id.btn_merge)
    Button btnMerge;
    @Bind(R.id.btn_loop)
    Button btnLoop;
    @Bind(R.id.btn_timer)
    Button btnTimer;
    @Bind(R.id.btn_publish)
    Button btnPublish;
    @Bind(R.id.btn_rxbus)
    Button btnRxbus;
    @Bind(R.id.btn_reuse_subscriber)
    Button btnReuseSubscriber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxjava_main, container, false);
        getActivity().setTitle("RxJava测试");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_net, R.id.btn_net2, R.id.btn_not_more_click, R.id.btn_checkbox_state_update, R.id.btn_text_change, R.id.btn_buffer, R.id.btn_zip, R.id.btn_merge, R.id.btn_loop, R.id.btn_timer, R.id.btn_publish, R.id.btn_rxbus, R.id.btn_reuse_subscriber})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_net:
                open(new NetRxFragment());
                break;
            case R.id.btn_net2:
                break;
            case R.id.btn_not_more_click:
                break;
            case R.id.btn_checkbox_state_update:
                break;
            case R.id.btn_text_change:
                break;
            case R.id.btn_buffer:
                break;
            case R.id.btn_zip:
                break;
            case R.id.btn_merge:
                break;
            case R.id.btn_loop:
                break;
            case R.id.btn_timer:
                break;
            case R.id.btn_publish:
                break;
            case R.id.btn_rxbus:
                break;
            case R.id.btn_reuse_subscriber:
                break;
        }
    }

    /**
     * 开启新的Fragment
     */
    private void open(Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.fl_content_rxjava, fragment, tag)
                .commit();
    }

}
