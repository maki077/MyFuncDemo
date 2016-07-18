package com.example.myfuncdemo.net.retrofit;

import com.example.myfuncdemo.bean.ContributorBean;
import com.example.myfuncdemo.bean.ContributorAllBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Administrator on 2016/7/14.
 */
public interface IGithub {
    /**
     * bean 测试只有login contributions
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<ContributorBean>> contributors(@Path("owner") String owner, @Path("repo") String repo);

    /**
     * 所有gson字段
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<ContributorAllBean>> getAllContributors(@Path("owner") String owner, @Path("repo") String repo);

}
