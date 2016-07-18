package com.example.myfuncdemo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfuncdemo.application.MyApplication;
import com.example.myfuncdemo.view.activity.HomeActivity;
import com.example.myfuncdemo.common.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 *
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.splash_time)
    TextView splashTime;
    @Bind(R.id.splash_icon)
    ImageView splashIcon;
    @Bind(R.id.splash_debug_info)
    TextView splashDebugInfo;

    private static final int TIME_SECOND = 1;  //标识1s

    private Long showTime;   //闪页显示时间


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        showTime = 3L;   //总等待时间，使用大写L，便于与数字1区分
        init();
        startTiming();
    }

    @OnClick({R.id.splash_time, R.id.splash_icon, R.id.splash_debug_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.splash_time:
                enterApp(showTime);
                break;
            case R.id.splash_icon:
                break;
            case R.id.splash_debug_info:
                break;
        }
    }

    /**
     * 初始化UI
     */
    public void init() {
        initDebugVersion();
        splashTime.setText(getString(R.string.splash_time, showTime));
    }
    /**
     * 进入APP倒计时
     * 使用RxJava进行异步处理   基类CompositeSubscription  处理  这里订阅返回的Subscription
     * 每秒调用一次enterMain
     */
    Subscription subscription;
    public void startTiming() {
        Subscription subscription = Observable.interval(TIME_SECOND, TimeUnit.SECONDS)  //间隔 可观察的（被观察者）每秒发射订阅信息
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())  //订阅者所在线程
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        //aLong代表interval调用次数
                        enterApp(aLong);
                        Log.d("test leak", aLong + "");
                    }
                });
        //将 订阅事件 加入 subscription集合(Set)，用于与Activity生命周期绑定，onDestroy时解除事件注册
        compositeSubscription.add(subscription);
        //subscription.unsubscribe();//取消该注册
    }





    /**
     * 测试环境下，显示编译号
     */
    private void initDebugVersion() {
        try {
            ApplicationInfo appInfo = MyApplication.getInstance().getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);

            String version = appInfo.metaData.getString("DEBUG_VERSION");
            splashDebugInfo.setText(version);
        } catch (Exception e) {
            Log.e("debug info", "获取Debug版本号失败");
        }

    }

    /**
     * 判断时间是否达到，达到则进入APP
     *
     * @param along 事件调用次数，周期1s，即已过时间
     */
    public void enterApp(Long along) {
        if (!along.equals(showTime)) {
            splashTime.setText(getString(R.string.splash_time, showTime - along));
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
