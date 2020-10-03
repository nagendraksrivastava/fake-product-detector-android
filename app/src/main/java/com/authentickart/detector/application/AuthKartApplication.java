package com.authentickart.detector.application;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.authentickart.detector.db.database.AuthKartDatabase;
import com.authentickart.detector.di.components.ApplicationComponent;
import com.authentickart.detector.di.components.DaggerApplicationComponent;
import com.authentickart.detector.di.modules.ApplicationModule;
import com.authentickart.detector.di.modules.ContextModule;
import com.authentickart.detector.utils.Constants;
import com.authentickart.detector.utils.Logger;
import com.authentickart.detector.utils.UserManager;

/**
 * Created by arif on 23/7/17.
 */

public class AuthKartApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private AuthKartDatabase mAuthKartDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .contextModule(new ContextModule(this))
                .build();
        initDb();
        Logger.init();
    }

    private void initDb(){
        mAuthKartDatabase = Room.databaseBuilder(getApplicationContext(),
                AuthKartDatabase.class, Constants.StringConstants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
        UserManager userManager = UserManager.getInstance();
        userManager.init(mAuthKartDatabase.userDao());
    }

    public AuthKartDatabase getDatabase(){
        return mAuthKartDatabase;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
