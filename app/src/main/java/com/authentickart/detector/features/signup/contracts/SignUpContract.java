package com.authentickart.detector.features.signup.contracts;

import com.authentickart.detector.base.BasePresenterContract;
import com.authentickart.detector.base.BaseView;
import com.authentickart.detector.network.request.signup.SignUpRequest;

/**
 * Created by arif on 30/7/17.
 */

public interface SignUpContract {

    interface View extends BaseView {
        void showOnSignUpSuccess();

        void showOnSignUpFailure(String message);
    }

    interface Presenter extends BasePresenterContract {
        void onSignUpSuccess();

        void onSignUpFailure(String message);
    }

    interface ApiContract {
        void signUp(SignUpRequest signUpRequest);
    }

}
