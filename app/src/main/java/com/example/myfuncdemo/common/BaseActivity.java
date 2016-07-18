package com.example.myfuncdemo.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import rx.subscriptions.CompositeSubscription;

/**
 * Activity 基类
 * 管理RxJava的创建和销毁
 * 各个子类Activity的生命周期
 */
public class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription compositeSubscription;
    protected boolean isDestoryed;      //标识此Activity是否已销毁

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity : ", "Start Activity: " + this.getClass().getName());
        compositeSubscription = new CompositeSubscription();
    }

    public void test(){
//        compositeSubscription.add();
    }


    @Override
    protected void onDestroy() {
        Log.v("Activity : ", this.getClass().getName());
        compositeSubscription.unsubscribe();
        isDestoryed = true;
        super.onDestroy();
    }

    private Toast toast; //创建一个对象，防止多次连续调用

    /**
     * 提供一个方便的弹toast的方法
     */
    protected void showToast(String msg) {

        if (TextUtils.isEmpty(msg)) {
            return;
        }

        showToast(msg, Toast.LENGTH_LONG);
    }

    protected void showToast(String msg, int duration) {

        if (toast == null) {
            toast = Toast.makeText(this, msg, duration);
        } else {
            toast.setText(msg);
            toast.setDuration(duration);
        }

        toast.show();
    }

}
