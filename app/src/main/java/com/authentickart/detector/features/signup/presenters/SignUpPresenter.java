package com.authentickart.detector.features.signup.presenters;

import com.authentickart.detector.base.BasePresenter;
import com.authentickart.detector.db.dao.UserDao;
import com.authentickart.detector.features.signup.contracts.SignUpContract;
import com.authentickart.detector.features.signup.interactors.SignUpInteractor;
import com.authentickart.detector.network.request.signup.SignUpRequest;
import com.authentickart.detector.utils.AppUtils;

/**
 * Created by arif on 31/7/17.
 */

public class SignUpPresenter extends BasePresenter<SignUpContract.View> implements SignUpContract.Presenter {

    private SignUpInteractor mSignUpInteractor;

    public SignUpPresenter(UserDao userDao) {
        mSignUpInteractor = new SignUpInteractor(userDao);
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(mSignUpInteractor, this);
    }

    public void signUp(String firstName, String lastName, String email, String password) {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFname(firstName);
        signUpRequest.setLname(lastName);
        signUpRequest.setEmail(email);
        signUpRequest.setPassword(password);
        if (getViewContract() != null) {
            getViewContract().showProgress();
            mSignUpInteractor.signUp(signUpRequest);
        }
    }

    @Override
    public void onSignUpSuccess() {
        if (getViewContract() != null) {
            getViewContract().cancelProgress();
            getViewContract().showOnSignUpSuccess();
        }
    }

    @Override
    public void onSignUpFailure(String message) {
        if (getViewContract() != null) {
            getViewContract().cancelProgress();
            getViewContract().showOnSignUpFailure(message);
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
            getViewContract().cancelProgress();
            getViewContract().showOnSignUpFailure(error);
        }
    }

    public boolean isSignUpDataValid(String firstName, String lastName, String email, String password){
        return AppUtils.isTextValid(firstName, 4) && AppUtils.isTextValid(lastName, 4)
                && AppUtils.isEmailValid(email) && AppUtils.isTextValid(password, 5);
    }

}
