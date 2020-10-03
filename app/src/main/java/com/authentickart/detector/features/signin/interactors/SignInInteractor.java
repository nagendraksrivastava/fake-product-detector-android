package com.authentickart.detector.features.signin.interactors;

import com.authentickart.detector.base.BaseInteractor;
import com.authentickart.detector.base.RxSingleObserverEvent;
import com.authentickart.detector.db.dao.UserDao;
import com.authentickart.detector.db.entities.User;
import com.authentickart.detector.features.signin.contracts.SignInContract;
import com.authentickart.detector.network.api.AuthKartApiObservables;
import com.authentickart.detector.network.request.signin.SignInRequest;
import com.authentickart.detector.network.response.signin.SignInResponse;
import com.authentickart.detector.utils.Constants;
import com.authentickart.detector.utils.RxUtils;
import com.authentickart.detector.utils.UserManager;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by arif on 5/8/17.
 */

public class SignInInteractor extends BaseInteractor implements SignInContract.ApiContract, RxSingleObserverEvent<SignInResponse> {

    private UserDao mUserDao;

    public SignInInteractor(UserDao userDao) {
        mUserDao = userDao;
    }

    @Override
    public void signIn(SignInRequest signInRequest) {
        if (getPresenter() != null) {
            AuthKartApiObservables.getInstance().signIn(signInRequest).subscribe(getSingleSubscriber(this));
        }
    }

    @Override
    public void onSuccess(SignInResponse signInResponse) {
        if (getPresenter() != null) {
            if (signInResponse != null) {
                if (signInResponse.getStatus().getCode() == Constants.HttpCodes.OK) {
                    queueSubscriptionsForDisposal(saveUser(signInResponse)
                            .compose(RxUtils.<User>applyCommonSchedulers())
                            .subscribe(new Consumer<User>() {
                                @Override
                                public void accept(@NonNull User user) throws Exception {
                                    if (user != null) {
                                        UserManager.getInstance().reinit(mUserDao);
                                        getPresenter().onSignInSuccess();
                                    }
                                }
                            }));
                } else {
                    getPresenter().onSignInFailure(signInResponse.getStatus().getMessage());
                }
            }
        }
    }

    @Override
    public void onError(Throwable error) {
        if (getPresenter() != null) {
            getPresenter().onSignInFailure(error != null ? error.getMessage() : "");
        }
    }

    private SignInContract.Presenter getPresenter() {
        return (SignInContract.Presenter) getPresenterContract();
    }

    private Observable<User> saveUser(final SignInResponse signInResponse) {
        return Observable.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                User user = new User();
                user.setFname(signInResponse.getFirstName());
                user.setLname(signInResponse.getLastName());
                user.setEmail(signInResponse.getEmail());
                user.setToken(signInResponse.getToken());
                mUserDao.save(user);
                return user;
            }
        });
    }
}
