package com.example.myfuncdemo.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.myfuncdemo.R;
import com.example.myfuncdemo.common.BaseActivity;
import com.example.myfuncdemo.view.fragment.Page1Fragment;
import com.example.myfuncdemo.view.fragment.Page2Fragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Administrator on 2016/7/15.
 * 伸缩toolbar
 */
public class CollapsingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.header)
    ImageView header;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapseToolbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;


    private ArrayList<Fragment> fragmentsList;
    Fragment fg_page1;
    Fragment fg_page2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse_toolbar);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        String[] titles = new String[]{"page1", "page2-recycler"};
        fragmentsList = new ArrayList<Fragment>();

        fg_page1 = new Page1Fragment();
        fg_page2 = new Page2Fragment();

        fragmentsList.add(fg_page1);
        fragmentsList.add(fg_page2);

        /*try {
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            Object ob = field.get(tabs);
            Class<?> c = Class.forName("android.support.design.widget.TabLayout$SlidingTabStrip");
            Method method = c.getDeclaredMethod("setSelectedIndicatorColor", int.class);
            method.setAccessible(true);
            method.invoke(ob, Color.RED);
        } catch (Exception e) {

        }*/

        viewpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentsList,titles));
        tabs.setupWithViewPager(viewpager); //tab 和 viewpager同步
        viewpager.setCurrentItem(0);
    }



    /**
     * viewpageadapter
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentsList;
        private String[] titles;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments,String[] titles) {
            super(fm);
            this.fragmentsList = fragments;
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentsList.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        /**
         * 和pagerview同步
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }


}