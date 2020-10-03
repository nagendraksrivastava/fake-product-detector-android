package com.authentickart.detector.network.api;

import android.support.annotation.NonNull;

import com.authentickart.detector.BuildConfig;
import com.authentickart.detector.Endpoint;
import com.authentickart.detector.di.scope.ApplicationScope;
import com.authentickart.detector.utils.Constants;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arif on 5/8/17.
 */

public final class ApiProvider {

    /**
     * private Constructor
     */
    private ApiProvider(){
      //NO-OP
    }

    private static class ApiProvideInstanceHolder{
        private static final ApiProvider API_PROVIDER = new ApiProvider();
    }

    public static ApiProvider getInstance(){
        return ApiProvideInstanceHolder.API_PROVIDER;
    }

    public AuthKartApi getAuthKartApi() {
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Endpoint.HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpBuilder().build());
        return builder.build().create(AuthKartApi.class);
    }

    @NonNull
    private OkHttpClient.Builder getOkHttpBuilder() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(Constants.IntegerConstants.API_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(Constants.IntegerConstants.API_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new TokenInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(logging);
        }
        return okHttpClient;
    }
}
