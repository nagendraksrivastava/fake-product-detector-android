package com.authentickart.detector.di.components;

import android.content.Context;

import com.authentickart.detector.application.AuthKartApplication;
import com.authentickart.detector.di.modules.AppNavigatorModule;
import com.authentickart.detector.di.modules.ApplicationModule;
import com.authentickart.detector.di.modules.ContextModule;
import com.authentickart.detector.di.scope.ApplicationScope;
import com.authentickart.detector.network.api.AuthKartApiObservables;
import com.authentickart.detector.utils.AppNavigator;

import dagger.Component;

/**
 * Created by arif on 30/7/17.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, ContextModule.class,
        AppNavigatorModule.class})
public interface ApplicationComponent {

    Context provideApplicationContext();

    AuthKartApplication provideAuthKartApplication();

    AppNavigator provideAppNavigator();
}
