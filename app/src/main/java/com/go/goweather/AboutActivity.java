package com.go.goweather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.go.goweather.base.BaseActivity;

public class AboutActivity extends BaseActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_about;
    }
    public static void launch(Context context){
        context.startActivity(new Intent(context,AboutActivity.class));
    }
}
