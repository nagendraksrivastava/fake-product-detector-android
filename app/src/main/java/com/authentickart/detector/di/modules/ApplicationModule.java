package com.authentickart.detector.di.modules;

import com.authentickart.detector.application.AuthKartApplication;
import com.authentickart.detector.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arif on 30/7/17.
 */

@Module
public class ApplicationModule {

    private AuthKartApplication mApp;

    public ApplicationModule(AuthKartApplication app) {
        mApp = app;
    }

    @Provides
    @ApplicationScope
    public AuthKartApplication provideApplication() {
        return mApp;
    }
}
