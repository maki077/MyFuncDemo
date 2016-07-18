package com.example.myfuncdemo.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.bean.ContributorAllBean;
import com.example.myfuncdemo.bean.ContributorBean;
import com.example.myfuncdemo.bean.TopClassifyBean;
import com.example.myfuncdemo.bean.TopPostFieldBean;
import com.example.myfuncdemo.bean.TopSearchBean;
import com.example.myfuncdemo.net.retrofit.IGithub;
import com.example.myfuncdemo.net.retrofit.IHotwords;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Administrator on 2016/7/13.
 */
public class NetCompareActivity extends AppCompatActivity {

    @Bind(R.id.tv_retrofit_response)
    TextView tvRetrofitResponse;
    @Bind(R.id.tv_retrofit_time)
    TextView tvRetrofitTime;
    @Bind(R.id.btn_retrofit_send)
    Button btnRetrofitSend;
    @Bind(R.id.tv_okhttp_response)
    TextView tvOkhttpResponse;
    @Bind(R.id.tv_okhttp_time)
    TextView tvOkhttpTime;
    @Bind(R.id.btn_okhttp_send)
    Button btnOkhttpSend;

    private OkHttpClient client;
    private Date start;
    private Date end;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_compare);
        ButterKnife.bind(this);
        initHttpLogging();
    }
    //TODO 点击测试触发
    @OnClick({R.id.btn_retrofit_send, R.id.btn_okhttp_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retrofit_send:
//                sendRetrofitMsg_Hotwords_classify();
//                sendRetrofitMsg_Hotwords_classify_Log_Rxjava();
//                sendRetrofitMsg_Github_Contributor();
//                sendRetrofitMsg_Github_Contributor_All();
//                sendRetrofitMsg_Hotwords_query();
                sendRetrofitMsg_Post();
                break;
            case R.id.btn_okhttp_send:
                //okHttp_synchronousGet();
//                okHttp_asynchronousGet();
//                okhttp_addHeader();
                okHttp_postFromParameters();
//                okHttp_stopRequest();
                break;
        }
    }

    /**
     * 初始化httplogging
     * @return
     */
    public void initHttpLogging(){
        // Log信息
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        // OkHttp3.0的使用方式
        client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }






    public static final String HOT_WORDS_URL = "http://www.tngou.net";
    public static final String API_URL = "https://api.github.com";

    /**
     * http://www.tngou.net/api/top/classify
     *  @GET("/api/top/classify")
     * 用Retrofit进行请求
     * 第一种 简单组合 写死了路径
     */
    public void sendRetrofitMsg_Hotwords_classify(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOT_WORDS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IHotwords hotwords =retrofit.create(IHotwords.class);
        Call<TopClassifyBean> call=hotwords.getTopClassifyList();
        call.enqueue(new Callback<TopClassifyBean>() {
            @Override
            public void onResponse(Call<TopClassifyBean> call, Response<TopClassifyBean> response) {
                System.out.println("response:"+response.body().getTngou().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<TopClassifyBean> call, Throwable t) {
                System.out.println("error call:"+t  );
                System.out.println("error Throwable:"+t  );
            }
        });

    }
    /**
     * http://www.tngou.net/api/top/classify
     *  @GET("/api/top/classify")
     * 用Retrofit进行请求
     * 第一种 简单组合 写死了路径
     * 添加打印请求日志
     * 结合Rxjava
     * 测试请求时间
     */
    public void sendRetrofitMsg_Hotwords_classify_Log_Rxjava(){
        start = new Date(System.currentTimeMillis());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOT_WORDS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //Rxjava
                .client(client) //log
                .build();


        IHotwords hotwords =retrofit.create(IHotwords.class);
        Observable<TopClassifyBean> observable = hotwords.getTopClassifyRxjava();
        /**
         * Rxjava写法3
         * 简化Subscriber，假设并不关心OnComplete和OnError，
         * 只需要在onNext的时候做一些处理，这时候就可以使用Action1类
         */
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<TopClassifyBean>() {
//                    @Override
//                    public void call(TopClassifyBean topClassifyBean) {
//                        System.out.println("topClassifyBean:"+topClassifyBean.getTngou().get(0).getTitle());
//                    }
//                });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TopClassifyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Rxjava-error","sendRetrofitMsg_Hotwords_classify_Log_Rxjava() onError()");
                    }

                    @Override
                    public void onNext(TopClassifyBean topClassifyBean) {
                        end = new Date(System.currentTimeMillis());
                        long diff = end.getTime() - start.getTime();
                        Log.i("Rxjava+Retrofit：","耗时："+String.valueOf(diff) + "ms");
                    }
                });
    }


    /**
     *
     * https://api.github.com/repos/square/retrofit/contributors
     * @GET("/repos/{owner}/{repo}/contributors")
     *  Call<List<Contributor>> getAllContributors
     *   (@Path("owner") String owner, @Path("repo") String repo);
     *第二种 动态的url访问 @PATH
     * call.execute() 同步请求  所以另起线程
     */
    public void sendRetrofitMsg_Github_Contributor(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    IGithub github = retrofit.create(IGithub.class);
                    Call<List<ContributorBean>> call = github.contributors("square", "retrofit");
                    List<ContributorBean> contributors = call.execute().body();
                    for (ContributorBean contributor : contributors) {
                        System.out.println(contributor.login + " (" + contributor.contributions + ")");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * https://api.github.com/repos/square/retrofit/contributors
     *   @GET("/repos/{owner}/{repo}/contributors")
     *   Call<List<ContributorAll>> getAllContributors
     *   (@Path("owner") String owner, @Path("repo") String repo);
     *第二种 动态的url访问 @PATH
     * call.enqueue(xxx) 异步请求
     */
    public void sendRetrofitMsg_Github_Contributor_All(){
                try {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    IGithub github = retrofit.create(IGithub.class);
                    Call<List<ContributorAllBean>> call = github.getAllContributors("square", "retrofit");
                    call.enqueue(new Callback<List<ContributorAllBean>>() {
                        @Override
                        public void onResponse(Call<List<ContributorAllBean>> call, Response<List<ContributorAllBean>> response) {

                            System.out.println("call:"+call);
                            System.out.println("body:"+response.body()); //List
                            System.out.println("message:"+response.message());//ok
                            System.out.println("code:"+response.code());//200
                            System.out.println("errorBody:"+response.errorBody());//null
                            System.out.println("headers:"+response.headers());//Server: GitHub.com xxxxx
                            System.out.println("isSuccessful:"+response.isSuccessful());//true
                        }

                        @Override
                        public void onFailure(Call<List<ContributorAllBean>> call, Throwable t) {
                            System.out.println("error-call:"+call);
                            System.out.println("Throwable:"+t);
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
    }

    /**
     * http://www.tngou.net/api/search?keyword=健康&name=topword
     * 第三种
     * 对应接口的单个查询，多个查询  map查询  @Query  @QueryMap
     */
    public void sendRetrofitMsg_Hotwords_query() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOT_WORDS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IHotwords hotwords = retrofit.create(IHotwords.class);

        Map<String, String> options = new HashMap<>();
        options.put("keyword", "健康");
        options.put("name", "topword");
        options.put("page", "1");
        options.put("rows", "20");
        // Call<TopSearchBean> call = hotwords.getTopSearchList("健康"); //返回msg 请求错误
        // Call<TopSearchBean> call = hotwords.getTopSearchList("健康","topword","1","20");//多个@Query
        Call<TopSearchBean> call = hotwords.getTopSearchList(options);//@QueryMap
        call.enqueue(new Callback<TopSearchBean>() {
            @Override
            public void onResponse(Call<TopSearchBean> call, Response<TopSearchBean> response) {
                System.out.println("msg:" + response.body().getMsg());
                if (response.body().getTngou() != null && response.body().getTngou().size() > 0) {
                    System.out.println("getTitle" + response.body().getTngou().get(0).getTitle());
                }
            }
            @Override
            public void onFailure(Call<TopSearchBean> call, Throwable t) {

            }
        });
    }
    //TODO ITestService
    public interface  ITestService{
        String url = "http://api.k780.com:88/app/abc/dca/";
        @GET("a123/{user}")
        Call<TopPostFieldBean> getTest(@Path("user") String username1);
        @POST("/")
        Call<TopPostFieldBean> getPostTest(@QueryMap Map<String,String> options);
    }

    /**
     * @Field 表单提交失败  还是QueryMap
     * http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
     *
     */
    public void sendRetrofitMsg_Post(){
        Map<String, String> options = new HashMap<>();
        options.put("app", "weather.future");
        options.put("weaid", "1");
        options.put("appkey", "10003");
        options.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        options.put("format", "json");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ITestService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ITestService hotwords =retrofit.create(ITestService.class);
        Call<TopPostFieldBean> call=hotwords.getPostTest(options);
        call.enqueue(new Callback<TopPostFieldBean>() {
            @Override
            public void onResponse(Call<TopPostFieldBean> call, Response<TopPostFieldBean> response) {
                System.out.println("response:"+response);
            }

            @Override
            public void onFailure(Call<TopPostFieldBean> call, Throwable t) {
                System.out.println("error call:"+t  );
                System.out.println("error Throwable:"+t  );
            }
        });
    }





    //TODO   OKHttp Demo

    /**
     * 同步Get方法 execute  设置超时时间
     */
    private void okHttp_synchronousGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "https://api.github.com/";
                    //OkHttpClient client = new OkHttpClient();
                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .build();


                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    okhttp3.Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("OKHttp同步Get success", response.body().string());
                    } else {
                        Log.i("OKHttp同步Get error", "okHttp is request error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 异步 Get方法 enqueue
     */
    private void okHttp_asynchronousGet(){
        try {
            Log.i("OKHttp异步Get","main thread id is "+Thread.currentThread().getId());
            String url = "https://api.github.com/";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {

                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    // 注：该回调是子线程，非主线程
                    Log.i("OKHttp异步Get","callback thread id is "+Thread.currentThread().getId());
                    Log.i("OKHttp异步Get",response.body().string());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加okhttp请求头
     */
    public void okhttp_addHeader()  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://api.github.com/repos/square/okhttp/issues")
                            .header("User-Agent", "OkHttp Headers.java")
                            .addHeader("Accept", "application/json; q=0.5")
                            .addHeader("Accept", "application/vnd.github.v3+json")
                            .build();

                    okhttp3.Response response = client.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println("Server: " + response.header("Server"));
                    System.out.println("Date: " + response.header("Date"));
                    System.out.println("Vary: " + response.headers("Vary"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * okhttp_post 提交表单
     * 其他 Post String Stram File 未测试
     * http://www.2cto.com/kf/201606/514939.html
     */
    private void okHttp_postFromParameters() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 请求完整url：http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
                    String url = "http://api.k780.com:88/";
                    OkHttpClient okHttpClient = new OkHttpClient();
                    String json = "{'app':'weather.future','weaid':'1','appkey':'10003'," +
                            "'sign':'b59bc3ef6191eb9f747dd4e83c99f2a4','format':'json'}";
                    RequestBody formBody = new FormBody.Builder()
                            .add("app", "weather.future")
                            .add("weaid", "1")
                            .add("appkey", "10003")
                            .add("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4")
                            .add("format", "json")
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();
                    okhttp3.Response response = okHttpClient.newCall(request).execute();
                    Log.i("OKHttp Post KV", response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    /**
     * OKHttp 请求中断
     * call.cancel();
     * 如果一个线程正在写一个请求或是读取返回的response，它将会接收到一个IOException
     *  System.out: 0.00 Executing call.
        System.out: 1.00 Canceling call.
        System.out: 1.01 Canceled call.
        System.out: 1.01 Call failed as expected: java.net.SocketException: Socket closed
     */
    public void okHttp_stopRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
                            .build();
                    final long startNanos = System.nanoTime();
                    final okhttp3.Call call = client.newCall(request);

                    // Schedule a job to cancel the call in 1 second.
                    executor.schedule(new Runnable() {
                        @Override public void run() {
                            System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
                            call.cancel(); //1秒后停止
                            System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f);
                        }
                    }, 1, TimeUnit.SECONDS);
                    try {
                        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
                        okhttp3.Response response = call.execute();
                        System.out.printf("%.2f Call was expected to fail, but completed: %s%n",
                                (System.nanoTime() - startNanos) / 1e9f, response);
                    } catch (IOException e) {
                        System.out.printf("%.2f Call failed as expected: %s%n",
                                (System.nanoTime() - startNanos) / 1e9f, e);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 设置缓存  未测试
     */
    /**
     * okHttp 验证处理  未测试
     */
}
