package com.authentickart.detector.features.signin.presenters;

import com.authentickart.detector.base.BasePresenter;
import com.authentickart.detector.db.dao.UserDao;
import com.authentickart.detector.features.signin.contracts.SignInContract;
import com.authentickart.detector.features.signin.interactors.SignInInteractor;
import com.authentickart.detector.network.request.signin.SignInRequest;
import com.authentickart.detector.utils.AppUtils;

/**
 * Created by arif on 5/8/17.
 */

public class SignInPresenter extends BasePresenter<SignInContract.View> implements SignInContract.Presenter {

    private SignInInteractor mSignInInteractor;

    public SignInPresenter(UserDao userDao) {
        mSignInInteractor = new SignInInteractor(userDao);
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(mSignInInteractor, this);
    }

    public void signIn(String email, String password) {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail(email);
        signInRequest.setPassword(password);
        if (getViewContract() != null) {
            getViewContract().showProgress();
            mSignInInteractor.signIn(signInRequest);
        }
    }

    @Override
    public void onSignInSuccess() {
        if (getViewContract() != null) {
            getViewContract().cancelProgress();
            getViewContract().showOnSignInSuccess();
        }
    }

    @Override
    public void onSignInFailure(String message) {
        if (getViewContract() != null) {
            getViewContract().cancelProgress();
            getViewContract().showOnSignInFailure(message);
        }
    }

    @Override
    public void onError(Throwable error) {
        if (getViewContract() != null) {
            getViewContract().cancelProgress();
        }
    }

    @Override
    public void onNetworkError(String error) {
        if (getViewContract() != null) {
            getViewContract().showOnSignInFailure(error);
        }
    }

    public boolean isSignInDataValid(String email, String password){
        return AppUtils.isEmailValid(email) && AppUtils.isTextValid(password, 5);
    }
}
