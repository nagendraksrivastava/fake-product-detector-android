package com.authentickart.detector.features.signin.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.authentickart.detector.R;
import com.authentickart.detector.application.AuthKartApplication;
import com.authentickart.detector.base.BaseToolbarActivity;
import com.authentickart.detector.custom.AuthKartButton;
import com.authentickart.detector.custom.AuthKartEditText;
import com.authentickart.detector.db.database.AuthKartDatabase;
import com.authentickart.detector.features.signin.contracts.SignInContract;
import com.authentickart.detector.features.signin.presenters.SignInPresenter;
import com.authentickart.detector.utils.AppNavigator;
import com.authentickart.detector.utils.UiUtils;

/**
 * Created by arif on 5/8/17.
 */

public class SignInActivity extends BaseToolbarActivity implements SignInContract.View, View.OnClickListener {

    private SignInPresenter mSignInPresenter;
    private AuthKartDatabase mAuthKartDatabase;
    private AuthKartEditText mEtEmail, mEtPassword;
    private AuthKartButton mBtnSignIn;
    private ProgressBar mPbSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_layout);
        initToolbar();
        initViews();
        setListeners();
        mAuthKartDatabase = ((AuthKartApplication) getApplication()).getDatabase();
        initPresenters();
    }

    @Override
    protected void onDestroy() {
        destroyPresenters();
        super.onDestroy();
    }

    private void initToolbar() {
        hideToolbarIcon();
        setToolbarTitle(getString(R.string.login));
        setHomeAsUpEnabled(true);
    }

    private void initViews() {
        mEtEmail = (AuthKartEditText) findViewById(R.id.et_email);
        mEtPassword = (AuthKartEditText) findViewById(R.id.et_password);
        mBtnSignIn = (AuthKartButton) findViewById(R.id.btn_sign_in);
        mPbSignIn = (ProgressBar) findViewById(R.id.pb_signin);
    }

    private void setListeners() {
        mBtnSignIn.setOnClickListener(this);
    }

    private void initPresenters() {
        mSignInPresenter = new SignInPresenter(mAuthKartDatabase.userDao());
        mSignInPresenter.subscribeView(this);
    }

    private void destroyPresenters() {
        if (isPresenterSubscribed(mSignInPresenter, this)) {
            mSignInPresenter.unsubscribeView(this);
        }
    }

    @Override
    public void showOnSignInSuccess() {
        showShortToast("signin success");
        AppNavigator.getInstance().startCategoriesActivity(this, null);
        finish();
    }

    @Override
    public void showOnSignInFailure(String message) {
        showShortToast(message);
    }

    @Override
    public void showProgress() {
        mBtnSignIn.setEnabled(false);
        mPbSignIn.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelProgress() {
        mBtnSignIn.setEnabled(true);
        mPbSignIn.setVisibility(View.GONE);
    }

    @Override
    public void networkError(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void onError(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                String email = mEtEmail.getText().toString();
                String password = mEtPassword.getText().toString();
                if (isPresenterSubscribed(mSignInPresenter, this)
                        && mSignInPresenter.isSignInDataValid(email, password)) {
                    UiUtils.hideKeyboard(mBtnSignIn, this);
                    mSignInPresenter.signIn(email, password);
                } else {
                    showShortToast(getString(R.string.sign_in_error));
                }
                break;

            default:
                break;
        }
    }
}
