package com.authentickart.detector.features.splash.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.authentickart.detector.R;
import com.authentickart.detector.base.BaseActivity;
import com.authentickart.detector.utils.AppNavigator;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_home);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppNavigator.getInstance().startAppropriateActivityPostSplash(SplashActivity.this, null);
                finish();
            }
        }, 3000);
    }
}
