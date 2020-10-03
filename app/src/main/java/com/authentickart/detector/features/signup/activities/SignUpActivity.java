package com.authentickart.detector.features.signup.activities;

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
import com.authentickart.detector.features.signup.contracts.SignUpContract;
import com.authentickart.detector.features.signup.presenters.SignUpPresenter;
import com.authentickart.detector.utils.AppNavigator;
import com.authentickart.detector.utils.UiUtils;

/**
 * Created by arif on 5/8/17.
 */

public class SignUpActivity extends BaseToolbarActivity implements SignUpContract.View, View.OnClickListener {

    private SignUpPresenter mSignUpPresenter;
    private AuthKartEditText mEtFirstName, mEtLastName, mEtEmail, mEtPassword;
    private AuthKartButton mBtnSignUp;
    private ProgressBar mPbSignUp;
    private AuthKartDatabase mAuthKartDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);
        initToolbar();
        initViews();
        setListeners();
        mAuthKartDatabase = ((AuthKartApplication) getApplication()).getDatabase();
        initPresenters(mAuthKartDatabase);
    }

    @Override
    protected void onDestroy() {
        destroyPresenters();
        super.onDestroy();
    }

    private void initToolbar() {
        hideToolbarIcon();
        setToolbarTitle(getString(R.string.sign_up));
        setHomeAsUpEnabled(true);
    }

    private void initViews() {
        mEtFirstName = (AuthKartEditText) findViewById(R.id.et_first_name);
        mEtLastName = (AuthKartEditText) findViewById(R.id.et_last_name);
        mEtEmail = (AuthKartEditText) findViewById(R.id.et_email);
        mEtPassword = (AuthKartEditText) findViewById(R.id.et_password);
        mBtnSignUp = (AuthKartButton) findViewById(R.id.btn_sign_up);
        mPbSignUp = (ProgressBar) findViewById(R.id.pb_signup);
    }

    private void setListeners() {
        mBtnSignUp.setOnClickListener(this);
    }

    private void initPresenters(AuthKartDatabase authKartDatabase) {
        mSignUpPresenter = new SignUpPresenter(authKartDatabase.userDao());
        mSignUpPresenter.subscribeView(this);
    }

    private void destroyPresenters() {
        if (isPresenterSubscribed(mSignUpPresenter, this)) {
            mSignUpPresenter.unsubscribeView(this);
        }
    }

    @Override
    public void showOnSignUpSuccess() {
        showShortToast("signup success");
        AppNavigator.getInstance().startCategoriesActivity(this, null);
        finish();
    }

    @Override
    public void showOnSignUpFailure(String message) {
        showShortToast(message);
    }

    @Override
    public void showProgress() {
        mBtnSignUp.setEnabled(false);
        mPbSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelProgress() {
        mBtnSignUp.setEnabled(true);
        mPbSignUp.setVisibility(View.GONE);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                String firstName = mEtFirstName.getText().toString();
                String lastName = mEtLastName.getText().toString();
                String email = mEtEmail.getText().toString();
                String password = mEtPassword.getText().toString();
                if (isPresenterSubscribed(mSignUpPresenter, this)
                        && mSignUpPresenter.isSignUpDataValid(firstName, lastName, email, password)) {
                    UiUtils.hideKeyboard(mBtnSignUp, this);
                    mSignUpPresenter.signUp(firstName, lastName, email, password);
                }else{
                    showShortToast(getString(R.string.sign_up_error));
                }
                break;

            default:
                break;
        }
    }
}
