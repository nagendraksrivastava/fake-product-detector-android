package com.authentickart.detector.features.landing.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.authentickart.detector.R;
import com.authentickart.detector.base.BaseActivity;
import com.authentickart.detector.custom.AuthKartButton;
import com.authentickart.detector.utils.AppNavigator;

/**
 * Created by arif on 2/8/17.
 */

public class LandingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mImgLanding;
    private AuthKartButton mBtnSignUp, mBtnSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_layout);
        initViews();
        setListeners();
    }

    private void initViews() {
        mImgLanding = (ImageView) findViewById(R.id.img_landing);
        ViewCompat.setElevation(mImgLanding, getResources().getDimension(R.dimen.dimen_16dp));
        mBtnSignUp = (AuthKartButton) findViewById(R.id.btn_sign_up);
        mBtnSignIn = (AuthKartButton) findViewById(R.id.btn_login);
    }

    private void setListeners() {
        mBtnSignIn.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                AppNavigator.getInstance().startSignInActivity(this, null);
                break;

            case R.id.btn_sign_up:
                AppNavigator.getInstance().startSignUpActivity(this, null);
                break;

            default:
                break;
        }
    }
}
