package com.authentickart.detector.di.modules;

import com.authentickart.detector.di.scope.ApplicationScope;
import com.authentickart.detector.utils.AppNavigator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arif on 30/7/17.
 */

@Module
public class AppNavigatorModule {
    @Provides
    @ApplicationScope
    public AppNavigator provideAppNavigator() {
        return AppNavigator.getInstance();
    }
}
