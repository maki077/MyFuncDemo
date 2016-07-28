package com.example.myfuncdemo.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.bean.FuncBean;
import com.example.myfuncdemo.common.BaseActivity;
import com.example.myfuncdemo.view.adapter.HomeFuncAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Administrator on 2016/7/13.
 */
public class HomeActivity extends BaseActivity {

    @Bind(R.id.home_recycle_list)
    RecyclerView homeRecycleList;

    private List<FuncBean> funcList;    //功能列表
    private HomeFuncAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setTitle("功能列表");
        initFuncList();
        init();
    }

    public void initFuncList() {
        funcList = new ArrayList<>();
        FuncBean func = new FuncBean();
        func.funcName = "网络请求对比";
        func.itemClass = NetCompareActivity.class;
        funcList.add(func);


        func = new FuncBean();
        func.funcName = "三种图片对比";
        func.itemClass = ImageCompareActivity.class;
        funcList.add(func);

        func = new FuncBean();
        func.funcName = "伸缩toolbar CoordinatorLayout";
        func.itemClass = CollapsingActivity.class;
        funcList.add(func);

        func = new FuncBean();
        func.funcName = "Rxjava的方法使用";
        func.itemClass = RxJavaDemoActivity.class;
        funcList.add(func);
    }

    public void init() {

//设置LayoutManager，线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecycleList.setLayoutManager(linearLayoutManager);

        adapter = new HomeFuncAdapter(funcList, this);
        homeRecycleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
