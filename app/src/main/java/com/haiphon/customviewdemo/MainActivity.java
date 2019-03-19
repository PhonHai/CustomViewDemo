package com.haiphon.customviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.haiphon.customviewdemo.plugins.CircleProgressActivity;
import com.haiphon.customviewdemo.plugins.CircleViewActivity;
import com.haiphon.customviewdemo.plugins.ClockViewActivity;
import com.haiphon.customviewdemo.plugins.ZRationImageViewActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickClock(View view) {
        startActivity(new Intent(this, ClockViewActivity.class));
    }

    public void onClickCircle(View view) {
        startActivity(new Intent(this, CircleViewActivity.class));
    }

    public void onClickProgressView(View view) {
        startActivity(new Intent(this, CircleProgressActivity.class));
    }

    public void onClickZRationImageView(View view) {
        startActivity(new Intent(this, ZRationImageViewActivity.class));
    }
}
