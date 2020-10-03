package com.authentickart.detector.network.api;

import com.authentickart.detector.utils.UserManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by arif on 6/8/17.
 */

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (UserManager.getInstance().getUser() != null)
            builder.addHeader("Authorization", UserManager.getInstance().getAuthToken());
        return chain.proceed(builder.build());
    }

}