package com.example.myfuncdemo.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myfuncdemo.R;
import com.example.myfuncdemo.application.MyApplication;
import com.example.myfuncdemo.common.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Administrator on 2016/7/15.
 */
public class ImageCompareActivity extends BaseActivity {

    @Bind(R.id.image_compare_refresh)
    Button imageCompareRefresh;
    @Bind(R.id.image_compare_edit)
    EditText imageCompareEdit;
    @Bind(R.id.image_compare_fresco)
    SimpleDraweeView imageCompareFresco;
    @Bind(R.id.image_compare_glide)
    ImageView imageCompareGlide;
    @Bind(R.id.image_compare_picasso)
    ImageView imageComparePicasso;

    private String imageUrl;
    private List<String> urlList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compare);
        ButterKnife.bind(this);
        //Fresco.initialize(this);  //application内初始化
        setTitle("图片库比较");
        //加载图片
        init();
    }

    private void init() {
        initList();
        setImages();
    }

    private void initList() {
        urlList = new ArrayList<>();
        imageUrl = "http://i2.hdslb.com/u_user/f7a16e4a28fe524ceddfe0860b52d057.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i1.hdslb.com/u_user/b6ed574c94b249d990369d49eebb401b.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i2.hdslb.com/u_user/c44d0e12ef919452e5a13fa3c4f500a8.png";
        urlList.add(imageUrl);

        imageUrl = "http://i2.hdslb.com/u_user/4777b81d798a4ef7db1c4c872a119809.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i1.hdslb.com/u_user/35e040f2aa4288e37390bb1e7592b619.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i2.hdslb.com/u_user/5a23b9fd2e5f0659b8763b413d046ae5.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i1.hdslb.com/u_user/e8da027adc0a02f3f52a9c8ca45fa7cf.png";
        urlList.add(imageUrl);
    }

    public void setImages() {
        imageUrl = getImageUrl();
        Log.d("image", "image Url " + imageUrl);
        imageCompareFresco.setImageURI(Uri.parse(imageUrl));

        Glide.with(MyApplication.getInstance()).load(imageUrl).into(imageCompareGlide);

        Picasso.with(MyApplication.getInstance()).load(imageUrl).into(imageComparePicasso);

    }

    private String getImageUrl() {
        imageUrl = imageCompareEdit.getText().toString();
        if (TextUtils.isEmpty(imageUrl)) {
            int n = (int) (Math.random() * urlList.size());
            return urlList.get(n);
        }else{
            return imageUrl;
        }
    }

    @OnClick(R.id.image_compare_refresh)
    public void onClick() {
        setImages();
    }
}
