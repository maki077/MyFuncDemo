package com.example.myfuncdemo.view.fragment.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfuncdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Administrator on 2016/7/28.
 */
public class NetRxFragment extends Fragment {

    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.btn_get)
    Button btnGet;
    @Bind(R.id.btn_post)
    Button btnPost;
    @Bind(R.id.btn_put)
    Button btnPut;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.tv_result)
    TextView tvResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rx_net, container, false);
        getActivity().setTitle(R.string.btn_net_request);


        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_put, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                break;
            case R.id.btn_post:
                break;
            case R.id.btn_put:
                break;
            case R.id.btn_delete:
                break;
        }
    }
}
