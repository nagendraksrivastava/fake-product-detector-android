package com.authentickart.detector.utils;

import com.authentickart.detector.db.dao.UserDao;
import com.authentickart.detector.db.entities.User;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by arif on 6/8/17.
 */

public final class UserManager {

    private static UserDao mUserDao;
    private static User mUser;

    private UserManager() {
    }

    public static UserManager getInstance() {
        return UserManagerIntanceHolder.USER_MANAGER;
    }

    public void init(UserDao userDao) {
        mUserDao = userDao;
        if(mUserDao.getCount() > 0) {
            mUserDao.get()
                    .compose(RxUtils.<User>applyCommonSchedulersSingle())
                    .subscribe(new SingleObserver<User>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull User user) {
                            mUser = user;
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Logger.printStackTrace(e);
                        }
                    });
        }
    }

    public void reinit(UserDao userDao) {
        init(userDao);
    }

    public User getUser() {
        return mUser;
    }

    public String getAuthToken() {
        if (mUser != null) {
            return mUser.getToken();
        }
        return null;
    }

    public String getFirstName() {
        if (mUser != null) {
            return mUser.getFname();
        }
        return null;
    }

    public String getLastName() {
        if (mUser != null) {
            return mUser.getLname();
        }
        return null;
    }

    public String getEmail() {
        if (mUser != null) {
            return mUser.getEmail();
        }
        return null;
    }

    public interface OnGetTokenListener {
        void onGetToken(String token);
    }

    private static final class UserManagerIntanceHolder {
        private static final UserManager USER_MANAGER = new UserManager();
    }
}
