package com.go.goweather;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;

import com.go.goweather.base.BaseActivity;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer_layout;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton floatingActionButton;
    private NavigationView navigationView;
    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
    /**
     * 初始化控件
     * */
    @Override
    protected void initView() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coord);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        /**
         * 初始化抽屉
         *
         * */
        if (navigationView != null){
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    /**
     * 初始化数据
     * */
    @Override
    protected void initData() {

    }

    /**
     * 闪屏页跳转
     * */
    public static void launch(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }


    /**
     * 抽屉item监听
     * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}
