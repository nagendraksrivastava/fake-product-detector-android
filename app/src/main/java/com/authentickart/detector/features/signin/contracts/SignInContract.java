package com.authentickart.detector.features.signin.contracts;

import com.authentickart.detector.base.BasePresenterContract;
import com.authentickart.detector.base.BaseView;
import com.authentickart.detector.network.request.signin.SignInRequest;

/**
 * Created by arif on 30/7/17.
 */

public interface SignInContract {
    interface View extends BaseView {
        void showOnSignInSuccess();

        void showOnSignInFailure(String message);
    }

    interface Presenter extends BasePresenterContract {
        void onSignInSuccess();

        void onSignInFailure(String message);
    }

    interface ApiContract {
        void signIn(SignInRequest signInRequest);
    }
}
