package com.example.myfuncdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.bean.ViewDemoBean;
import com.example.myfuncdemo.view.adapter.RecyclerViewDemoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Administrator on 2016/7/18.
 */
public class Page2Fragment extends Fragment {
    @Bind(R.id.fg_recycler_list)
    RecyclerView fgRecyclerList;

    private List<ViewDemoBean> viewDemoList;
    private RecyclerViewDemoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_2, null);

        ButterKnife.bind(this, view);
        init();
        return view;

    }


    public void init(){
        initDemo();
        //设置LayoutManager，线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fgRecyclerList.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerViewDemoAdapter(getContext(),viewDemoList);
        fgRecyclerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
    public void initDemo() {
        viewDemoList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ViewDemoBean viewDemo = new ViewDemoBean();
            viewDemo.title = "第" + i + "个";
            viewDemoList.add(viewDemo);
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
