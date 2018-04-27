package com.example.huangshizhe.graduationdesignclient;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.huangshizhe.graduationdesignclient.fragments.IndexFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class IndexActivity extends AppCompatActivity {

    private BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        bottomTabBar=findViewById(R.id.bottom_tab_bar);
        bottomTabBar
                .init(getSupportFragmentManager())//初始化方法，必须第一个调用；传入参数为V4包下的FragmentManager
                .setImgSize(50,50)//设置ICON图片的尺寸
                .setFontSize(8)//设置文字的尺寸
                .setTabPadding(4,6,10)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .setChangeColor(Color.DKGRAY,Color.RED)//设置选中的颜色、未选中的颜色
                .addTabItem("首页", R.mipmap.ic_launcher, IndexFragment.class)//设置文字、一张图片、fragment
                .addTabItem("收藏", R.mipmap.ic_launcher,IndexFragment.class)//设置文字、两张图片、fragment
                .addTabItem("我的",R.mipmap.ic_launcher,IndexFragment.class)
                .isShowDivider(false)//设置是否显示分割线
                .setTabBarBackgroundColor(Color.WHITE)//设置底部导航栏颜色
                .setTabBarBackgroundResource(R.mipmap.timg)//设置底部导航栏的背景图片【与设置底部导航栏颜色方法不能同时使用，否则会覆盖掉前边设置的颜色】
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        //Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
                    }
                })
                .setCurrentTab(0);//设置当前选中的Tab，从0开始
    }
}
