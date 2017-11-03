package com.go.goweather;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.go.goweather.adapter.HomePagerAdapter;
import com.go.goweather.base.BaseActivity;
import com.go.goweather.common.C;
import com.go.goweather.fragement.MainFragment;
import com.go.goweather.fragement.MultiFragment;
import com.go.goweather.utils.CircularAnimUtil;
import com.go.goweather.utils.SharedPreferenceUtil;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer_layout;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton floatingActionButton;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private MainFragment mainFragment;
    private MultiFragment multiFragment;
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tableLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        /**
         * 初始化抽屉
         *
         * */
        setSupportActionBar(toolbar);
        if (navigationView != null){
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.inflateHeaderView(R.layout.nav_header_main);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
            drawer_layout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
        }
    }

    /**
     * 初始化数据
     * */
    @Override
    protected void initData() {
        mainFragment = new MainFragment();
        multiFragment = new MultiFragment();
       // initIcon();
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        homePagerAdapter.addTab(mainFragment,"Main");
        homePagerAdapter.addTab(multiFragment,"MultiCity");
        final FabVisibilityChangedListener fabVisibilityChangedListener = new FabVisibilityChangedListener();
        tableLayout.setupWithViewPager(viewPager,false);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position) {
                if (floatingActionButton.isShown()) {
                    fabVisibilityChangedListener.position = position;
                    floatingActionButton.hide(fabVisibilityChangedListener);
                } else {
                    changeFabState(position);
                    floatingActionButton.show();
                }
            }
        });
    }
    private void changeFabState(int position) {
        if (position == 1) {
            floatingActionButton.setImageResource(R.drawable.ic_add_24dp);
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ChoiceCityActivity.class);
                    intent.putExtra(C.MULTI_CHECK, true);
                    CircularAnimUtil.startActivity(MainActivity.this, intent, floatingActionButton, R.color.colorPrimary);
                }
            });
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_favorite);
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(MainActivity.this, R.color.colorAccent)));
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showShareDialog();
                }
            });
        }
    }

    private void showShareDialog() {
        //SHARE DIALOG
    }


    private class FabVisibilityChangedListener extends FloatingActionButton.OnVisibilityChangedListener {
        private int position;
        @Override
        public void onHidden(FloatingActionButton fab) {
            changeFabState(position);
            fab.show();
        }
    }

    /**
     * 初始化图标
     * */
    private void initIcon() {
        if (SharedPreferenceUtil.getInstance().getIconType() == 0) {
            SharedPreferenceUtil.getInstance().putInt("未知", R.mipmap.none);
            SharedPreferenceUtil.getInstance().putInt("晴", R.mipmap.type_one_sunny);
            SharedPreferenceUtil.getInstance().putInt("阴", R.mipmap.type_one_cloudy);
            SharedPreferenceUtil.getInstance().putInt("多云", R.mipmap.type_one_cloudy);
            SharedPreferenceUtil.getInstance().putInt("少云", R.mipmap.type_one_cloudy);
            SharedPreferenceUtil.getInstance().putInt("晴间多云", R.mipmap.type_one_cloudytosunny);
            SharedPreferenceUtil.getInstance().putInt("小雨", R.mipmap.type_one_light_rain);
            SharedPreferenceUtil.getInstance().putInt("中雨", R.mipmap.type_one_light_rain);
            SharedPreferenceUtil.getInstance().putInt("大雨", R.mipmap.type_one_heavy_rain);
            SharedPreferenceUtil.getInstance().putInt("阵雨", R.mipmap.type_one_thunderstorm);
            SharedPreferenceUtil.getInstance().putInt("雷阵雨", R.mipmap.type_one_thunder_rain);
            SharedPreferenceUtil.getInstance().putInt("霾", R.mipmap.type_one_fog);
            SharedPreferenceUtil.getInstance().putInt("雾", R.mipmap.type_one_fog);
        } else {
            SharedPreferenceUtil.getInstance().putInt("未知", R.mipmap.none);
            SharedPreferenceUtil.getInstance().putInt("晴", R.mipmap.type_two_sunny);
            SharedPreferenceUtil.getInstance().putInt("阴", R.mipmap.type_two_cloudy);
            SharedPreferenceUtil.getInstance().putInt("多云", R.mipmap.type_two_cloudy);
            SharedPreferenceUtil.getInstance().putInt("少云", R.mipmap.type_two_cloudy);
            SharedPreferenceUtil.getInstance().putInt("晴间多云", R.mipmap.type_two_cloudytosunny);
            SharedPreferenceUtil.getInstance().putInt("小雨", R.mipmap.type_two_light_rain);
            SharedPreferenceUtil.getInstance().putInt("中雨", R.mipmap.type_two_rain);
            SharedPreferenceUtil.getInstance().putInt("大雨", R.mipmap.type_two_rain);
            SharedPreferenceUtil.getInstance().putInt("阵雨", R.mipmap.type_two_rain);
            SharedPreferenceUtil.getInstance().putInt("雷阵雨", R.mipmap.type_two_thunderstorm);
            SharedPreferenceUtil.getInstance().putInt("霾", R.mipmap.type_two_haze);
            SharedPreferenceUtil.getInstance().putInt("雾", R.mipmap.type_two_fog);
            SharedPreferenceUtil.getInstance().putInt("雨夹雪", R.mipmap.type_two_snowrain);
        }
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
        switch (item.getItemId()){
            case R.id.nav_city:
                ChoiceCityActivity.launch(this);
                return  true;
            case R.id.nav_multi_cities:
                viewPager.setCurrentItem(1);
                return  false;
            case R.id.nav_set:
                SettingActivity.launch(this);
                return  true;
            case R.id.nav_about:
                AboutActivity.launch(this);
                return true;
        }
        drawer_layout.closeDrawers();
        return false;
    }


}
