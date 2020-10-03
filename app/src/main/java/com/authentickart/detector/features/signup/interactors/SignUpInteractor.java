package com.authentickart.detector.features.signup.interactors;

import com.authentickart.detector.base.BaseInteractor;
import com.authentickart.detector.base.RxSingleObserverEvent;
import com.authentickart.detector.db.dao.UserDao;
import com.authentickart.detector.db.entities.User;
import com.authentickart.detector.features.signup.contracts.SignUpContract;
import com.authentickart.detector.network.api.AuthKartApiObservables;
import com.authentickart.detector.network.request.signup.SignUpRequest;
import com.authentickart.detector.network.response.signup.SignUpResponse;
import com.authentickart.detector.utils.Constants;
import com.authentickart.detector.utils.RxUtils;
import com.authentickart.detector.utils.UserManager;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by arif on 31/7/17.
 */

public class SignUpInteractor extends BaseInteractor implements SignUpContract.ApiContract, RxSingleObserverEvent<SignUpResponse> {

    private UserDao mUserDao;

    public SignUpInteractor(UserDao userDao) {
        mUserDao = userDao;
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if (getPresenter() != null) {
            AuthKartApiObservables.getInstance().signUp(signUpRequest).subscribe(getSingleSubscriber(this));
        }
    }

    @Override
    public void onSuccess(SignUpResponse signUpResponse) {
        if (getPresenter() != null) {
            if (signUpResponse.getStatus().getCode() == Constants.HttpCodes.OK) {
                queueSubscriptionsForDisposal(saveUser(signUpResponse)
                        .compose(RxUtils.<User>applyCommonSchedulers())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(@NonNull User user) throws Exception {
                                if (user != null) {
                                    UserManager.getInstance().reinit(mUserDao);
                                    getPresenter().onSignUpSuccess();
                                }
                            }
                        }));
            } else {
                getPresenter().onSignUpFailure(signUpResponse.getStatus().getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable error) {
        if (getPresenter() != null) {
            getPresenter().onSignUpFailure(error.getMessage() != null ? error.getMessage() : "");
        }
    }

    private SignUpContract.Presenter getPresenter() {
        return (SignUpContract.Presenter) getPresenterContract();
    }

    private Observable<User> saveUser(final SignUpResponse signUpResponse) {
        return Observable.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                User user = new User();
                user.setFname(signUpResponse.getFirstName());
                user.setLname(signUpResponse.getLastName());
                user.setEmail(signUpResponse.getEmail());
                user.setToken(signUpResponse.getToken());
                mUserDao.save(user);
                return user;
            }
        });
    }

}
