package com.authentickart.detector.di.modules;

import android.content.Context;

import com.authentickart.detector.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arif on 30/7/17.
 */

@Module
public class ContextModule {

    private final Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return mContext;
    }
}
