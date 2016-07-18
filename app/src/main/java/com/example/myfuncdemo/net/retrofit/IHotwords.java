package com.example.myfuncdemo.net.retrofit;

import com.example.myfuncdemo.bean.TopClassifyBean;
import com.example.myfuncdemo.bean.TopPostFieldBean;
import com.example.myfuncdemo.bean.TopSearchBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 热词接口
 * 命名最好 IxxxService
 * 因为retrofit.create(IxxxService.class) 的api注释 为  by interface service
 * http://www.tngou.net/doc/top
 */
public interface IHotwords {
    String URL = "xxx";
    /**
     * 简单组合 url+classify  返回TopClassify的list对象
     *
     * @return
     */
    @GET("/api/top/classify")
    Call<TopClassifyBean> getTopClassifyList();

    /**
     * 查询 http://www.tngou.net/api/search?keyword=健康&name=topword 后两个可以为空
     */
    @GET("/api/search")
    //Call<TopSearchBean> getTopSearchList(@Query("keyword") String keyword); //返回msg请求错误
    // Call<TopSearchBean> getTopSearchList(@Query("keyword") String keyword, @Query("name") String name, @Query("page") String page, @Query("rows") String rows);
     Call<TopSearchBean> getTopSearchList(@QueryMap Map<String ,String > options);

    /**
     * 简单组合 url+classify  返回TopClassify的list对象
     * Rxjava
     * @return
     */
    @GET("/api/top/classify")
    Observable<TopClassifyBean> getTopClassifyRxjava();


    /**
     * 请求完整url：http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
     */
    @POST()
    Call<TopPostFieldBean> getTopWeatharPostResult();

    @GET("a123/{user}")
    Call<TopPostFieldBean> getTest(@Path("user") String username1);
}
